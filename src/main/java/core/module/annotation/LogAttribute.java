package core.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liuxh
 * @version 创建时间：2013-1-21 下午02:55:28
 *
 * 类说明：操作属性注解
 *
 */
@Retention(RetentionPolicy.RUNTIME)   
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
public @interface LogAttribute {
	
	/**
	 * 表示字段是主键
	 * @return
	 */
	boolean primarykey() default false;
	/**
	 * 中文名称
	 * @return
	 */
	String name() default "";
	/**
	 * 对应数据库字段名，默认跟属性名一样
	 * @return
	 */
	String key() default "";
	/**
	 * 标志该属性为状态
	 * @return
	 */
	String state() default "";
	/**
	 * 关联实体类名
	 * @return
	 */
	String rentity() default "";
	/**
	 * 显示关联实体属性名
	 * @return
	 */
	String rname() default "";
}
