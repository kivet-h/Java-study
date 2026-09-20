package com.learnjava.bean.service;

// 导入 ArrayList 类，用于创建可变列表

import java.util.ArrayList;
// 导入 List 接口，用于定义用户集合的类型
import java.util.List;

// 用户服务类，封装了用户登录、注册、查询等核心业务逻辑
public class UserService {
    // 邮件服务依赖，用于在登录/注册时发送通知邮件（通过 Spring 注入）
    private MailService mailService;

    // 邮件服务的 setter 方法，供 Spring 容器通过 XML 配置进行依赖注入
    public void setMailService(MailService mailService) {
        // 将传入的 mailService 实例赋给当前对象的 mailService 字段
        this.mailService = mailService;
    }

    // 用户列表，使用 ArrayList 包装 List.of() 创建的不可变列表，使其变为可变列表
    // 初始化了三个测试用户：Bob、Alice、Tom
    private final List<User> users = new ArrayList<>(List.of(
            // 创建第一个用户：id=1，姓名=Bob，邮箱=bob@example.com，密码=password
            new User(1, "Bob", "bob@example.com", "password1"), // bob
            // 创建第二个用户：id=2，姓名=Alice，邮箱=alice@example.com，密码=password
            new User(2, "Alice", "alice@example.com", "password"), // alice
            // 创建第三个用户：id=3，姓名=Tom，邮箱=tom@example.com，密码=password
            new User(3, "Tom", "tom@example.com", "password") // tom
    ));

    // 用户登录方法，根据邮箱和密码验证用户身份
    // 参数 email：用户输入的邮箱地址
    // 参数 password：用户输入的密码
    // 返回：登录成功的 User 对象
    public User login(String email, String password) {
        // 遍历用户列表，逐一比对邮箱和密码
        for (User user : users) {
            // 判断当前用户的邮箱（忽略大小写）和密码是否与输入一致
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                // 登录成功，调用邮件服务发送登录通知邮件
                mailService.sendLoginMail(user);
                // 返回登录成功的用户对象
                return user;
            }
        }

        // 遍历完所有用户仍未匹配，抛出运行时异常表示登录失败
        throw new RuntimeException("login failed.");
    }

    // 根据用户 id 查询用户的方法
    // 参数 id：要查询的用户 id
    // 返回：匹配到的 User 对象，若不存在则抛出异常
    public User getUser(long id) {
        // 使用 Stream API 过滤出 id 匹配的用户，取第一个结果，若不存在则抛出 NoSuchElementException
        return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("用户 ID " + id + " 不存在"));
    }

    // 用户注册方法，创建新用户并添加到用户列表
    // 参数 email：新用户的邮箱地址
    // 参数 password：新用户的密码
    // 参数 name：新用户的姓名
    // 返回：新创建的 User 对象
    public User register(String email, String password, String name) {
        // 遍历现有用户列表，检查是否已存在相同邮箱（忽略大小写）的用户
        users.forEach(user -> {
            // 如果存在相同邮箱，抛出运行时异常提示邮箱已存在
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exists.");
            }
        });

        // 创建新用户：id 为当前最大 id + 1，并设置姓名、邮箱和密码
        User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong() + 1, name, email, password);
        // 将新用户添加到用户列表中
        users.add(user);
        // 调用邮件服务发送注册欢迎邮件
        mailService.sendRegistrationMail(user);
        // 返回新创建的用户对象
        return user;
    }
}