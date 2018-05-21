package in.explore.core.java8.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * This class provides a notification system which enables user to check the
 * deadlocked thread or get notification when the total number of threads
 * exceeds the threshold count.
 * 
 * 
 * This class uses Timer to run a thread in the background as a daemon thread.
 * This thread at specified intervals keep on the check if we are running in to
 * dead locks or going beyond threshold count and shoots a notification
 * accordingly.
 * 
 * It manages a list of Listeners for providing interface for notification
 * mechanism.
 * 
 * 
 * @author prashantsingh
 * @since 05/21/2018
 *
 */
public class ThreadWarningSystem {

	// Background Thread
	private final Timer threadCheck = new Timer("Thread Monitor", true);
	// The Managed Bean to get the reference to underlying Thread Support.

	private final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
	

	// Collection holder to hold all the registered listeners
	private final Collection<Listener> listeners = new ArrayList<>();

	// Time Interval in ms to run the dead lock checking thread
	private static final int DEADLOCK_CHECK_PERIOD = 500;

	// Time Interval in ms to run for thread number check
	private static final int THREAD_NUMBER_CHECK_PERIOD = 20;
	// Maximum number of stack depth
	private static final int MAX_STACK_DEPTH = 30;
	private boolean threadThresholdNotified = false;

	private Set deadLockedThreads = new HashSet();

	// Monitor only Deadlocks

	public ThreadWarningSystem() {
		threadCheck.schedule(new TimerTask() {

			@Override
			public void run() {

				// Get the dead locked threads waiting on monitor
				// Before Java 1.6 we could dead locked threads which
				// are locked while waiting on a monitor only
				// Support for new Lock and related synchronizers
				// added in 1.6
				long[] ids = null;

				// in 1.6 we can directly check all deadlocked threads
				// using findDeadlockedThreads to get all such threads
				// Also we can do it only if the underlying thread managed bean
				// supports
				// such functionality

				if (mbean.isSynchronizerUsageSupported()) {
					ids = mbean.findDeadlockedThreads();
				} else {
					ids = mbean.findMonitorDeadlockedThreads();
				}

				if (ids != null && ids.length > 0) {

					for (Long threadId : ids) {
						if (!deadLockedThreads.contains(threadId)) {
							deadLockedThreads.add(threadId);
							ThreadInfo ti = mbean.getThreadInfo(threadId, MAX_STACK_DEPTH);
							fireDeadlockDetected(ti);
						}
					}

				}

			}
		}, 10, DEADLOCK_CHECK_PERIOD);

	}

	/**
	 * Monitor Deadlocks and the number of threads
	 */

	public ThreadWarningSystem(final int threadThreshold) {
		this();
		threadCheck.schedule(new TimerTask() {

			@Override
			public void run() {
				if (mbean.getThreadCount() > threadThreshold) {
					if (!threadThresholdNotified) {
						fireThreadHoldExceed();
						threadThresholdNotified = true;
					}

				} else {
					threadThresholdNotified = false;
				}

			}
		}, 10, THREAD_NUMBER_CHECK_PERIOD);

	}

	private void fireDeadlockDetected(ThreadInfo threadInfo) {

		synchronized (listeners) {
			for (Listener l : listeners) {
				l.deadlockDetected(threadInfo);
			}
		}
	}

	private void fireThreadHoldExceed() {
		ThreadInfo[] allThreads = mbean.getThreadInfo(mbean.getAllThreadIds());
		synchronized (listeners) {
			for (Listener l : listeners) {
				l.thresholdExceeded(allThreads);
			}
		}

	}

	// Methods to support registration and de registration of listeners

	public void addListener(Listener l) {
		synchronized (listeners) {
			listeners.add(l);
		}
	}

	public void removeListener(Listener l) {
		synchronized (listeners) {
			listeners.remove(l);
		}
	}

	interface Listener {
		void deadlockDetected(ThreadInfo deadLockedThreadInfo);

		void thresholdExceeded(ThreadInfo[] allThreads);

	}

}
