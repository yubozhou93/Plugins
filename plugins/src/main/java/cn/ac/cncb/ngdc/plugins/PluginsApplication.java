package cn.ac.cncb.ngdc.plugins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ac.cncb.ngdc.plugins.mapper")
public class PluginsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PluginsApplication.class, args);
    }

}
