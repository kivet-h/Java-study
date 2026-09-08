/**
 * 10.5 DateTimeFormatter
 */


//使用旧的Date对象时，我们用SimpleDateFormat进行格式化显示。
// 使用新的LocalDateTime或ZonedDateTime时，我们要进行格式化显示，就要使用DateTimeFormatter。

//和SimpleDateFormat不同的是，DateTimeFormatter不但是不变对象，它还是线程安全的。
// 线程的概念我们会在后面涉及到。
// 现在我们只需要记住：因为SimpleDateFormat不是线程安全的，使用的时候，只能在方法内部创建新的局部变量。而DateTimeFormatter可以只创建一个实例，到处引用。




//创建DateTimeFormatter时，我们仍然通过传入格式化字符串实现：
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



//这种方式可以按照Locale默认习惯格式化。我们来看实际效果：
//import java.time.*;
//import java.time.format.*;
//import java.util.Locale;
//
//public class Main {
//    public static void main(String[] args) {
//        ZonedDateTime zdt = ZonedDateTime.now();
//
//        var formatterformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm ZZZZ");
//        System.out.println(formatterformatter.format(zdt)); // 2026-09-08T22:20 GMT+08:00
//
//        var zhFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd EE HH:mm", Locale.CHINA);
//        System.out.println(zhFormatter.format(zdt)); // 2026 9月 08 周二 22:20
//
//        var usFormatter = DateTimeFormatter.ofPattern("E, MMMM/dd/yyyy HH:mm", Locale.US);
//        System.out.println(usFormatter.format(zdt)); // Tue, September/08/2026 22:20
//    }
//}
//$ java Main.java
//2026-09-08T22:20 GMT+08:00
//2026 9月 08 周二 22:20
//Tue, September/08/2026 22:20


//在格式化字符串中，如果需要输出固定字符，可以用'xxx'表示。

//运行上述代码，分别以默认方式、中国地区和美国地区对当前时间进行显示，结果如下：
//2026-09-08T22:20 GMT+08:00
//2026 9月 08 周二 22:20
//Tue, September/08/2026 22:20

//当我们直接调用System.out.println()对一个ZonedDateTime或者LocalDateTime实例进行打印的时候，
// 实际上，调用的是它们的toString()方法，默认的toString()方法显示的字符串就是按照ISO 8601格式显示的，

// 我们可以通过DateTimeFormatter预定义的几个静态变量来引用：
//import java.time.*;
//import java.time.format.*;
//public class Main {
//    public static void main(String[] args) {
//        var ldt = LocalDateTime.now();
//        System.out.println(DateTimeFormatter.ISO_DATE.format(ldt)); // 2026-09-08
//        System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(ldt)); // 2026-09-08T22:24:36.8175778
//    }
//}
//$ java Main.java
//2026-09-08
//2026-09-08T22:24:36.8175778

//得到的输出和toString()类似：
//2019-09-15
//2019-09-15T23:16:51.56217











//小结
//对ZonedDateTime或LocalDateTime进行格式化，需要使用DateTimeFormatter类；
//
//DateTimeFormatter可以通过格式化字符串和Locale对日期和时间进行定制输出。




















