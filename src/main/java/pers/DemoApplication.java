package pers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author guohezuzi
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@tk.mybatis.spring.annotation.MapperScan(basePackages = "pers.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
