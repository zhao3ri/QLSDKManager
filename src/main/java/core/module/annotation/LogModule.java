package core.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuxh
 * @version 创建时间：2013-1-21 下午02:48:18
 *
 * 类说明：操作模块注解
 *
 */
@Retention(RetentionPolicy.RUNTIME)   
@Target({ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface LogModule {
	String name() default "";
	String key() default "";
}
