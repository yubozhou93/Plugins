package cn.ac.cncb.ngdc.plugins.pojo;

//import com.zsx.annotation.SensitiveField;
//import com.zsx.annotation.SensitiveData;
import cn.ac.cncb.ngdc.plugins.config.SensitiveData;
import cn.ac.cncb.ngdc.plugins.config.SensitiveField;
import lombok.*;
import java.io.Serializable;


/**
 * @author zhousx
 */
//@With
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SensitiveData // 插件只对加了该注解的类进行扫描，只有加了这个注解的类才会生效
public class User implements Serializable {
    private Integer id;
    private String name;

    // 表明对该字段进行加密
    @SensitiveField
    private String email;
    // 表明对该字段进行加密
    @SensitiveField
    private String phone;

}

