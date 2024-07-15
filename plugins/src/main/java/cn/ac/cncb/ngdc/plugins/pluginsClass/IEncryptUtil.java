package cn.ac.cncb.ngdc.plugins.pluginsClass;

import java.lang.reflect.Field;

/**
 * @author zhousx
 * @create 2023/10/01-22:45
 **/
public interface IEncryptUtil {
    /**
     * 加密
     *
     * @param declaredFields 加密字段
     * @param paramsObject   对象
     * @param <T>            入参类型
     * @return 返回加密
     * @throws IllegalAccessException 不可访问
     */
    <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException;
}
