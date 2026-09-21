/**
 * 22.1.3 使用Annotation配置 示例代码
 */

package com.learnjava.Annotation;

// 导入 User 实体类，用于表示用户对象

import com.learnjava.Annotation.service.User;
import com.learnjava.Annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// 主入口类（类名拼写为 Mian，应为 Main），作为 Spring 应用的启动入口
@Configuration
@ComponentScan
public class AppConfig {
    // 程序主方法，JVM 启动时从此方法开始执行
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password1");
        System.out.println(user.getName());
    }
}
