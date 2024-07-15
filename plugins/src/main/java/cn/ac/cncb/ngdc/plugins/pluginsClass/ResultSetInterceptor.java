package cn.ac.cncb.ngdc.plugins.pluginsClass;

//import com.zsx.annotation.SensitiveData;
import cn.ac.cncb.ngdc.plugins.config.SensitiveData;
import lombok.extern.slf4j.Slf4j;
//import org.aopalliance.intercept.Interceptor;
//import org.aopalliance.intercept.Invocation;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * @author zhousx
 * @create 2023/10/01-22:45
 **/
@Slf4j
@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class ResultSetInterceptor implements Interceptor {

    @Autowired
    private cn.ac.cncb.ngdc.plugins.pluginsClass.IDecryptUtil IDecryptUtil;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //取出查询的结果
        Object resultObject = invocation.proceed();
        if (Objects.isNull(resultObject)) {
            return null;
        }
        //基于selectList
        if (resultObject instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            ArrayList<Objects> resultList = (ArrayList<Objects>) resultObject;
            if (!CollectionUtils.isEmpty(resultList) && needToDecrypt(resultList.get(0))) {
                for (Object result : resultList) {
                    //逐一解密
                    IDecryptUtil.decrypt(result);
                }
            }
            //基于selectOne
        } else {
            if (needToDecrypt(resultObject)) {
                IDecryptUtil.decrypt(resultObject);
            }
        }
        return resultObject;
    }


    private boolean needToDecrypt(Object object) {
        Class<?> objectClass = object.getClass();
        SensitiveData sensitiveData = AnnotationUtils.findAnnotation(objectClass, SensitiveData.class);
        return Objects.nonNull(sensitiveData);
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, (org.apache.ibatis.plugin.Interceptor) this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}


