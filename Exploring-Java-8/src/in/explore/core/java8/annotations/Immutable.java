
package in.explore.core.java8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author prashantsingh
 *
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Immutable {

}
