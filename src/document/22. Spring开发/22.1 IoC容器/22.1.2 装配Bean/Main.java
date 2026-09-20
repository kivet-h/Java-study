/**
 * 22.1.2 装配Bean
 */

// 我们前面讨论了为什么要使用Spring的IoC容器，因为让容器来为我们创建并装配Bean能获得很大的好处，那么到底如何使用IoC容器？装配好的Bean又如何使用？

// 我们来看一个具体的用户注册登录的例子。整个工程的结构如下：
//spring-ioc-appcontext
//├── pom.xml
//└── src
//    └── main
//        ├── java
//        │   └── com
//        │       └── itranswarp
//        │           └── learnjava
//        │               ├── Main.java
//        │               └── service
//        │                   ├── MailService.java
//        │                   ├── User.java
//        │                   └── UserService.java
//        └── resources
//            └── application.xml

// 首先，我们用Maven创建工程并引入 spring-context 依赖：
// org.springframework:spring-context:6.0.0


// 我们先编写一个 MailService ，用于在用户登录和注册成功后发送邮件通知：
// 声明本类所在的包路径为 com.itranswarp.learnjava.service
// package com.itranswarp.learnjava.service;
//
// // 导入 ZoneId 类，用于表示时区
// import java.time.ZoneId;
// // 导入 ZonedDateTime 类，用于表示带时区的日期和时间
// import java.time.ZonedDateTime;
// // 导入 DateTimeFormatter 类，用于格式化日期时间
// import java.time.format.DateTimeFormatter;
//
// // 邮件服务类，提供发送登录通知邮件和注册欢迎邮件的功能
// public class MailService {
//     // 时区字段，初始值为系统默认时区，可通过 setter 方法注入
//     private ZoneId zoneId = ZoneId.systemDefault();
//
//     // 时区的 setter 方法，供 Spring 容器通过 XML 配置进行依赖注入
//     public void setZoneId(ZoneId zoneId) {
//         // 将传入的 zoneId 值赋给当前对象的 zoneId 字段
//         this.zoneId = zoneId;
//     }
//
//     // 获取当前时间字符串的方法
//     // 返回：按照 ISO 8601 格式（带时区）格式化的当前时间字符串
//     public String getTime() {
//         // 获取指定时区的当前日期时间，并使用 ISO_ZONED_DATE_TIME 格式器进行格式化后返回
//         return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
//     }
//
//     // 发送登录通知邮件的方法
//     // 参数 user：登录成功的用户对象
//     public void sendLoginMail(User user) {
//         // 使用 String.format 格式化登录通知内容，并输出到标准错误流（模拟发送邮件）
//         // 内容格式为："Hi, {用户名}! You are logged in at {登录时间}"
//         System.err.println(String.format("Hi, %s! You are logged in at %s", user.getName(), getTime()));
//     }
//
//     // 发送注册欢迎邮件的方法
//     // 参数 user：新注册的用户对象
//     public void sendRegistrationMail(User user) {
//         // 使用 String.format 格式化注册欢迎内容，并输出到标准错误流（模拟发送邮件）
//         // 内容格式为："Welcome, {用户名}!"
//         System.err.println(String.format("Welcome, %s!", user.getName()));
//     }
// }


// 再编写一个 UserService ，实现用户注册和登录：
// 声明本类所在的包路径为 com.itranswarp.learnjava.service
// package com.itranswarp.learnjava.service;
//
// // 导入 ArrayList 类，用于创建可变列表
// import java.util.ArrayList;
// // 导入 List 接口，用于定义用户集合的类型
// import java.util.List;
//
// // 用户服务类，封装了用户登录、注册、查询等核心业务逻辑
// public class UserService {
//     // 邮件服务依赖，用于在登录/注册时发送通知邮件（通过 Spring 注入）
//     private MailService mailService;
//
//     // 邮件服务的 setter 方法，供 Spring 容器通过 XML 配置进行依赖注入
//     public void setMailService(MailService mailService) {
//         // 将传入的 mailService 实例赋给当前对象的 mailService 字段
//         this.mailService = mailService;
//     }
//
//     // 用户列表，使用 ArrayList 包装 List.of() 创建的不可变列表，使其变为可变列表
//     // 初始化了三个测试用户：Bob、Alice、Tom
//     private List<User> users = new ArrayList<>(List.of(
//             // 创建第一个用户：id=1，姓名=Bob，邮箱=bob@example.com，密码=password
//             new User(1, "Bob", "bob@example.com", "password1"), // bob
//             // 创建第二个用户：id=2，姓名=Alice，邮箱=alice@example.com，密码=password
//             new User(2, "Alice", "alice@example.com", "password"), // alice
//             // 创建第三个用户：id=3，姓名=Tom，邮箱=tom@example.com，密码=password
//             new User(3, "Tom", "tom@example.com", "password") // tom
//     ));
//
//     // 用户登录方法，根据邮箱和密码验证用户身份
//     // 参数 email：用户输入的邮箱地址
//     // 参数 password：用户输入的密码
//     // 返回：登录成功的 User 对象
//     public User login(String email, String password) {
//         // 遍历用户列表，逐一比对邮箱和密码
//         for (User user : users) {
//             // 判断当前用户的邮箱（忽略大小写）和密码是否与输入一致
//             if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
//                 // 登录成功，调用邮件服务发送登录通知邮件
//                 mailService.sendLoginMail(user);
//                 // 返回登录成功的用户对象
//                 return user;
//             }
//         }
//
//         // 遍历完所有用户仍未匹配，抛出运行时异常表示登录失败
//         throw new RuntimeException("login failed.");
//     }
//
//     // 根据用户 id 查询用户的方法
//     // 参数 id：要查询的用户 id
//     // 返回：匹配到的 User 对象，若不存在则抛出异常
//     public User getUser(long id) {
//         // 使用 Stream API 过滤出 id 匹配的用户，取第一个结果，若不存在则抛出 NoSuchElementException
//         return this.users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("用户 ID " + id + " 不存在"));
//     }
//
//     // 用户注册方法，创建新用户并添加到用户列表
//     // 参数 email：新用户的邮箱地址
//     // 参数 password：新用户的密码
//     // 参数 name：新用户的姓名
//     // 返回：新创建的 User 对象
//     public User register(String email, String password, String name) {
//         // 遍历现有用户列表，检查是否已存在相同邮箱（忽略大小写）的用户
//         users.forEach(user -> {
//             // 如果存在相同邮箱，抛出运行时异常提示邮箱已存在
//             if(user.getEmail().equalsIgnoreCase(email)) {
//                 throw new RuntimeException("email exists.");
//             }
//         });
//
//         // 创建新用户：id 为当前最大 id + 1，并设置姓名、邮箱和密码
//         User user = new User(users.stream().mapToLong(u -> u.getId()).max().getAsLong() + 1, name, email, password);
//         // 将新用户添加到用户列表中
//         users.add(user);
//         // 调用邮件服务发送注册欢迎邮件
//         mailService.sendRegistrationMail(user);
//         // 返回新创建的用户对象
//         return user;
//     }
// }


// User.java文件：
// 声明本类所在的包路径为 com.itranswarp.learnjava.service
// package com.itranswarp.learnjava.service;
//
// // 用户实体类，用于表示一个用户的基本信息（id、姓名、邮箱、密码）
// public class User {
//     // 用户唯一标识，使用 long 类型
//     long id;
//     // 用户姓名
//     String name;
//     // 用户邮箱地址
//     String email;
//     // 用户密码
//     String password;
//
//     public User (long id, String name, String email, String password) {
//         this.id = id;
//         this.name = name;
//         this.email = email;
//         this.password = password;
//     }
//
//     // 获取用户 id 的 getter 方法
//     public long getId() {
//         // 返回 id 字段的值
//         return id;
//     }
//     // 设置用户 id 的 setter 方法
//     public void setId(long id) {
//         // 将传入的 id 值赋给当前对象的 id 字段
//         this.id = id;
//     }
//
//     // 获取用户姓名的 getter 方法
//     public String getName() {
//         // 返回 name 字段的值
//         return name;
//     }
//     // 设置用户姓名的 setter 方法
//     public void setName(String name) {
//         // 将传入的 name 值赋给当前对象的 name 字段
//         this.name = name;
//     }
//
//     // 获取用户邮箱的 getter 方法
//     public String getEmail() {
//         // 返回 email 字段的值
//         return email;
//     }
//     // 设置用户邮箱的 setter 方法
//     public void setEmail(String email) {
//         // 将传入的 email 值赋给当前对象的 email 字段
//         this.email = email;
//     }
//
//     // 获取用户密码的 getter 方法
//     public String getPassword() {
//         // 返回 password 字段的值
//         return password;
//     }
//     // 设置用户密码的 setter 方法
//     public void setPassword(String password) {
//         // 将传入的 password 值赋给当前对象的 password 字段
//         this.password = password;
//     }
//
// }


// 注意到 UserService 通过 setMailService() 注入了一个 MailService 。
// 然后，我们需要编写一个特定的 application.xml 配置文件，告诉Spring的IoC容器应该如何创建并组装Bean：
//<?xml version="1.0" encoding="UTF-8"?>
//<!-- Spring Bean 配置文件，定义应用中各组件（Bean）的创建方式和依赖关系 -->
//<!-- 声明 XML Schema 实例命名空间，用于 XSD 校验 -->
//<!-- 指定 XSD 模式文件的位置，用于校验本配置文件的合法性 -->
//<beans xmlns="http://www.springframework.org/schema/beans"
//xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//xsi:schemaLocation="http://www.springframework.org/schema/beans
//https://www.springframework.org/schema/beans/spring-beans.xsd">
//
//    <!-- 定义 userService Bean：实例化 UserService 类，并注入 mailService 依赖 -->
//    <bean id="userService" class="com.learnjava.bean.service.UserService">
//        <!-- 通过 property 标签将 mailService Bean 注入到 UserService 的 mailService 属性中（setter 注入） -->
//        <property name="mailService" ref="mailService"/>
//    </bean>
//
//    <!-- 定义 mailService Bean：实例化 MailService 类，无额外依赖注入 -->
//    <bean id="mailService" class="com.learnjava.bean.service.MailService"/>
//</beans>

// 注意观察上述配置文件，其中与XML Schema相关的部分格式是固定的，我们只关注两个 <bean...> 的配置：
// 每个 <bean ...> 都有一个 id 标识，相当于Bean的唯一ID；
// 在 userService Bean中，通过 <property name="..." ref="..." /> 注入了另一个Bean；Bean的顺序不重要，Spring根据依赖关系会自动正确初始化。


// 把上述XML配置文件用Java代码写出来，就像这样：
// UserService userService = new UserService();
// MailService mailService = new MailService();
// userService.setMailService(mailService);


// 只不过Spring容器是通过读取XML文件后使用反射完成的。
// 如果注入的不是Bean，而是 boolean 、 int 、 String 这样的数据类型，则通过 value 注入，例如，创建一个 HikariDataSource ：
// <bean id="dataSource" class="com.zaxxer.hikari.HikariDataSource">
//     <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test" />
//     <property name="username" value="root" />
//     <property name="password" value="password" />
//     <property name="maximumPoolSize" value="10" />
//     <property name="autoCommit" value="true" />
// </bean>


// 最后一步，我们需要创建一个Spring的IoC容器实例，然后加载配置文件，让Spring容器为我们创建并装配好配置文件中指定的所有Bean，这只需要一行代码：
// ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

// 接下来，我们就可以从Spring容器中“取出”装配好的Bean然后使用它：
// 获取Bean:
// UserService userService = context.getBean(UserService.class);// 正常调用:
// User user = userService.login("bob@example.com", "password");


// 完整的 main() 方法如下：
// 声明本类所在的包路径为 com.itranswarp.learnjava
// package com.itranswarp.learnjava;
//
/// / 导入 User 实体类，用于表示用户对象
//import com.learnjava.bean.service.User;
//// 导入 UserService 服务类，用于调用用户相关业务逻辑
//import com.learnjava.bean.service.UserService;
//// 导入 Spring 的 ApplicationContext 接口，用于获取 Spring 容器中的 Bean
//import org.springframework.context.ApplicationContext;
//// 导入 ClassPathXmlApplicationContext，用于从 classpath 下加载 XML 配置文件创建 Spring 容器
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
// // 主入口类（类名拼写为 Mian，应为 Main），作为 Spring 应用的启动入口
// public class Main {
//     // 程序主方法，JVM 启动时从此方法开始执行
//     public static void main(String[] args) {
//         // 通过 ClassPathXmlApplicationContext 加载 classpath 下的 application.xml 配置文件，创建 Spring 应用上下文（IoC 容器）
//         ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//         // 从 Spring 容器中获取 UserService 类型的 Bean 实例
//         UserService userService = context.getBean(UserService.class);
//         // // 调用 UserService 的 login 方法，使用邮箱 "bob@example.com" 和密码 "password" 进行登录，返回登录成功的 User 对象
//         // User user = userService.login("bob@example.com", "password1");
//         // // 打印登录成功用户的姓名
//         // System.out.println("登录成功，用户名为：" + user.getName());
//
//
//
//         // User registerUser = userService.register("xiaowang@gmail.com", "111", "小王");
//         // System.out.println("注册成功，用户名为：" + registerUser.getName());
//         // // 调用 UserService 的 login 方法，使用邮箱 "bob@example.com" 和密码 "password" 进行登录，返回登录成功的 User 对象
//         // User user = userService.login("xiaowang@gmail.com", "111");
//         // // 打印登录成功用户的姓名
//         // System.out.println("登录成功，用户名为：" + user.getName());
//
//
//         try {
//             User user2 = userService.getUser(10);
//             System.out.println("查询成功，用户名为：" + user2.getName());
//         } catch (Exception e) {
//             System.out.println("查询失败，用户不存在");
//         }
//     }
// }


// ===================================================== ApplicationContext =====================================================
// 我们从创建Spring容器的代码：
// ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

// 可以看到，Spring容器就是 ApplicationContext ，它是一个接口，有很多实现类，
// 这里我们选择ClassPathXmlApplicationContext ，表示它会自动从classpath中查找指定的XML配置文件。


// 获得了 ApplicationContext 的实例，就获得了IoC容器的引用。
// 从 ApplicationContext 中我们可以根据Bean的ID获取Bean，但更多的时候我们根据Bean的类型获取Bean的引用：
// UserService userService = context.getBean(UserService.class);


// Spring还提供另一种IoC容器叫 BeanFactory ，使用方式和 ApplicationContext 类似：
// BeanFactory factory = new XmlBeanFactory(newClassPathResource("application.xml"));
// MailService mailService = factory.getBean(MailService.class);


// BeanFactory 和 ApplicationContext 的区别在于，
// BeanFactory 的实现是按需创建，即第一次获取Bean时才创建这个Bean，
// 而 ApplicationContext 会一次性创建所有的Bean。
// 实际上，ApplicationContext 接口是从 BeanFactory 接口继承而来的，并且， ApplicationContext 提供了一些额外的功能，包括国际化支持、事件和通知机制等。
// 通常情况下，我们总是使用ApplicationContext ，很少会考虑使用 BeanFactory 。


// 小结：
// Spring的IoC容器接口是 ApplicationContext ，并提供了多种实现类；
// 通过XML配置文件创建IoC容器时，使用 ClassPathXmlApplicationContext ；
// 持有IoC容器后，通过 getBean() 方法获取Bean的引用。

