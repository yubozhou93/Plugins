package cn.ac.cncb.ngdc.plugins.dao;

import cn.ac.cncb.ngdc.plugins.pojo.User;
import cn.ac.cncb.ngdc.plugins.config.SensitiveField;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserDao {

    /**
     * 测试查询 普通参数加密
     * @param phone
     * @return
     */
    List<User> getUserByPhone(@SensitiveField@Param("phone") String phone);

    /**
     * 测试插入 普通参数加密，多个需要加密的字段
     * @param name
     * @param email
     * @param phone
     * @return
     */
    int insertUserByParam(@Param("name") String name, @SensitiveField@Param("email") String email, @SensitiveField@Param("phone") String phone);
}
