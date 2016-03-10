package org.cbioportal.cgds_java.framework.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.lang.model.type.NullType;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {
	
	public String value();
	
	public boolean redundant() default false;
	
	public Class<?> collectionType() default NullType.class;
	
}
