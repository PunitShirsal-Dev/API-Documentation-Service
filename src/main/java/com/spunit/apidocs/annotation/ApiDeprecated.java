package com.spunit.apidocs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark API endpoints as deprecated
 * Provides metadata about deprecation timeline and replacement
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDeprecated {

    /**
     * The version in which this API was deprecated
     */
    String since() default "";

    /**
     * The date when this API will be removed (sunset date)
     */
    String sunsetDate() default "";

    /**
     * Replacement API endpoint or method
     */
    String replacement() default "";

    /**
     * URL to migration guide
     */
    String migrationGuide() default "";

    /**
     * Reason for deprecation
     */
    String reason() default "";
}

