package com.learnjava.Annotation.service;


// 导入 ZoneId 类，用于表示时区

import org.springframework.stereotype.Component;

import java.time.ZoneId;
// 导入 ZonedDateTime 类，用于表示带时区的日期和时间
import java.time.ZonedDateTime;
// 导入 DateTimeFormatter 类，用于格式化日期时间
import java.time.format.DateTimeFormatter;

// 邮件服务类，提供发送登录通知邮件和注册欢迎邮件的功能
@Component
public class MailService {
    // 时区字段，初始值为系统默认时区，可通过 setter 方法注入
    private ZoneId zoneId = ZoneId.systemDefault();

    // 时区的 setter 方法，供 Spring 容器通过 XML 配置进行依赖注入
    public void setZoneId(ZoneId zoneId) {
        // 将传入的 zoneId 值赋给当前对象的 zoneId 字段
        this.zoneId = zoneId;
    }

    // 获取当前时间字符串的方法
    // 返回：按照 ISO 8601 格式（带时区）格式化的当前时间字符串
    public String getTime() {
        // 获取指定时区的当前日期时间，并使用 ISO_ZONED_DATE_TIME 格式器进行格式化后返回
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    // 发送登录通知邮件的方法
    // 参数 user：登录成功的用户对象
    public void sendLoginMail(User user) {
        // 使用 String.format 格式化登录通知内容，并输出到标准错误流（模拟发送邮件）
        // 内容格式为："Hi, {用户名}! You are logged in at {登录时间}"
        System.err.println(String.format("Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    // 发送注册欢迎邮件的方法
    // 参数 user：新注册的用户对象
    public void sendRegistrationMail(User user) {
        // 使用 String.format 格式化注册欢迎内容，并输出到标准错误流（模拟发送邮件）
        // 内容格式为："Welcome, {用户名}!"
        System.err.println(String.format("Welcome, %s!", user.getName()));
    }
}
