package com.learnjava.Annotation.service;


// 用户实体类，用于表示一个用户的基本信息（id、姓名、邮箱、密码）
public class User {
    // 用户唯一标识，使用 long 类型
    long id;
    // 用户姓名
    String name;
    // 用户邮箱地址
    String email;
    // 用户密码
    String password;

    public User(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // 获取用户 id 的 getter 方法
    public long getId() {
        // 返回 id 字段的值
        return id;
    }

    // 设置用户 id 的 setter 方法
    public void setId(long id) {
        // 将传入的 id 值赋给当前对象的 id 字段
        this.id = id;
    }

    // 获取用户姓名的 getter 方法
    public String getName() {
        // 返回 name 字段的值
        return name;
    }

    // 设置用户姓名的 setter 方法
    public void setName(String name) {
        // 将传入的 name 值赋给当前对象的 name 字段
        this.name = name;
    }

    // 获取用户邮箱的 getter 方法
    public String getEmail() {
        // 返回 email 字段的值
        return email;
    }

    // 设置用户邮箱的 setter 方法
    public void setEmail(String email) {
        // 将传入的 email 值赋给当前对象的 email 字段
        this.email = email;
    }

    // 获取用户密码的 getter 方法
    public String getPassword() {
        // 返回 password 字段的值
        return password;
    }

    // 设置用户密码的 setter 方法
    public void setPassword(String password) {
        // 将传入的 password 值赋给当前对象的 password 字段
        this.password = password;
    }

}
