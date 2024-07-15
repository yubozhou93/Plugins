package cn.ac.cncb.ngdc.plugins.config;

import java.lang.annotation.*;
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveData {

}
