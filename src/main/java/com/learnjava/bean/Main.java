/**
 * 22.1.2 装配Bean 示例代码
 */

package com.learnjava.bean;

// 导入 User 实体类，用于表示用户对象

import com.learnjava.bean.service.User;
// 导入 UserService 服务类，用于调用用户相关业务逻辑
import com.learnjava.bean.service.UserService;
// 导入 Spring 的 ApplicationContext 接口，用于获取 Spring 容器中的 Bean
import org.springframework.context.ApplicationContext;
// 导入 ClassPathXmlApplicationContext，用于从 classpath 下加载 XML 配置文件创建 Spring 容器
import org.springframework.context.support.ClassPathXmlApplicationContext;


// 主入口类（类名拼写为 Mian，应为 Main），作为 Spring 应用的启动入口
public class Main {
    // 程序主方法，JVM 启动时从此方法开始执行
    public static void main(String[] args) {
        // 通过 ClassPathXmlApplicationContext 加载 classpath 下的 application.xml 配置文件，创建 Spring 应用上下文（IoC 容器）
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        // 从 Spring 容器中获取 UserService 类型的 Bean 实例
        UserService userService = context.getBean(UserService.class);
        // // 调用 UserService 的 login 方法，使用邮箱 "bob@example.com" 和密码 "password" 进行登录，返回登录成功的 User 对象
        // User user = userService.login("bob@example.com", "password1");
        // // 打印登录成功用户的姓名
        // System.out.println("登录成功，用户名为：" + user.getName());


        User registerUser = userService.register("xiaowang@gmail.com", "111", "小王");
        System.out.println("注册成功，用户名为：" + registerUser.getName());
        // 调用 UserService 的 login 方法，使用邮箱 "bob@example.com" 和密码 "password" 进行登录，返回登录成功的 User 对象
        User user = userService.login("xiaowang@gmail.com", "111");
        // 打印登录成功用户的姓名
        System.out.println("登录成功，用户名为：" + user.getName());


//        try {
//            User user2 = userService.getUser(10);
//            System.out.println("查询成功，用户名为：" + user2.getName());
//        } catch (Exception e) {
//            System.out.println("查询失败，用户不存在");
//        }
    }
}
