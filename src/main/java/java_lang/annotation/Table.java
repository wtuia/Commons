package java_lang.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 保留策略 运行时保留
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String value() default "";
	String[] arg() default "";
}
