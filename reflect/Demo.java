package reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)   
/*
 * @Retention 修饰注解的注解
 * RetentionPolicy.SOURCE:默认值(编译完了只在源代码中有)
 * RetentionPolicy.CLASS 保留到class中
 * RetentionPolicy.RUNTIME:保留到运行时(保留到方法区)
 * 反射在运行时动态加载类
 */
public @interface Demo {

}
