package com.btyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class MyyfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyyfApplication.class, args);
        System.out.println("启动成功！");
    }

}
