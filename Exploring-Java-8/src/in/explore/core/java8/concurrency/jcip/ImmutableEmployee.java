/**
 * @author prashantasingh
 */
package in.explore.core.java8.concurrency.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.Immutable;

/**
 * @author hp pc
 *
 */

public class ImmutableEmployee {

	private long employeeId;

	/**
	 * @return the employeeId
	 */
	
	public long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

}
