/**
 * @author prashantasingh
 */
package in.explore.core.java8.annotations;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @author prashantsingh
 *
 */
@SupportedAnnotationTypes(value = {"in.explore.core.java8.annotations.Overloaded"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CustomOverloadAnnotationProcessor extends AbstractProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 * javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
