package java_lang.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 保留策略 运行时保留
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String value() default "";
}
