package com.learnjava.dingZhiBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 1. 创建 Spring 容器
        // 参数：配置类（这里我们直接扫描当前包，或者指定具体的配置类）
        // 假设所有类都在 com.dingZhiBean 包下
        ApplicationContext context = new AnnotationConfigApplicationContext("com.learnjava.dingZhiBean");

        // 2. 从容器中获取 Validators Bean
        Validators validators = context.getBean(Validators.class);

        // 3. 测试数据
        String email = "testexample.com";
        String password = "123456";
        String name = "User";

        // 4. 执行验证
        validators.validate(email, password, name);

        // 5. 关闭容器
        ((AnnotationConfigApplicationContext) context).close();
    }
}
