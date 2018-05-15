package cn.gdou.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("cn.gdou")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootApp {
    public static void main(String[] args){
        SpringApplication.run(SpringBootApp.class,args);
    }
}
