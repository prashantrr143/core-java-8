package in.explore.core.java8.annotations;

import java.lang.annotation.Documented;

@Documented
public @interface ClassPreamble {

	String author();

	String date();

	int currentRevision() default 1;

	String lastModified() default "N/A";

	String lastModifiedBy() default "N/A";

	String[] reviewers();
}

@ClassPreamble(author = "Prashant Kumar Singh", currentRevision = 1, date = "10/05/2018", lastModified = "10/5/2018", lastModifiedBy = "Prashant Kumar Singh", reviewers = {
		"Prashant", "Kumar", "Singh" })
class UseClassPreamble {

}