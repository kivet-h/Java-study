/**
 * 10.7 最佳实践
 */

//由于Java提供了新旧两套日期和时间的API，除非涉及到遗留代码，否则我们应该坚持使用新的API。

//如果需要与遗留代码打交道，如何在新旧API之间互相转换呢？



















// ==================================================== 旧API转新API ====================================================
//如果要把旧式的Date或Calendar转换为新API对象，可以通过toInstant()方法转换为Instant对象，再继续转换为ZonedDateTime：
//import java.util.*;
//import java.time.*;
//public class Main {
//    public static void main(String[] args) {
////        // Date -> Instant:
////        Instant ins1 = new Date().toInstant();
//
//        // Calendar -> Instant -> ZonedDateTime:
//        Calendar calendar = Calendar.getInstance();
//        Instant ins2 =  calendar.toInstant();
//        System.out.println(ins2); // 2026-09-08T14:43:30.628Z
//        ZonedDateTime zonedDateTime = ins2.atZone(calendar.getTimeZone().toZoneId());
//        System.out.println(zonedDateTime); // 2026-09-08T22:43:30.628+08:00[Asia/Shanghai]
//    }
//}
//$ java Main.java
//2026-09-08T14:43:30.628Z
//2026-09-08T22:43:30.628+08:00[Asia/Shanghai]


//从上面的代码还可以看到，旧的TimeZone提供了一个toZoneId()，可以把自己变成新的ZoneId。



















// ==================================================== 新API转旧API ====================================================
//如果要把新的ZonedDateTime转换为旧的API对象，只能借助long型时间戳做一个“中转”：
//// ZonedDateTime -> long:
//ZonedDateTime zdt = ZonedDateTime.now();
//long ts = zdt.toEpochSecond() * 1000;
//
//// long -> Date:
//Date date = new Date(ts);
//
//// long -> Calendar:
//Calendar calendar = Calendar.getInstance();
//calendar.clear();
//calendar.setTimeZone(TimeZone.getTimeZone(zdt.getZone().getId()));
//calendar.setTimeInMillis(zdt.toEpochSecond() * 1000);


//从上面的代码还可以看到，新的ZoneId转换为旧的TimeZone，需要借助ZoneId.getId()返回的String完成。























// ==================================================== 在数据库中存储日期和时间 ====================================================
//除了旧式的java.util.Date，我们还可以找到另一个java.sql.Date，它继承自java.util.Date，但会自动忽略所有时间相关信息。这个奇葩的设计原因要追溯到数据库的日期与时间类型。

//在数据库中，也存在几种日期和时间类型：
// - DATETIME：表示日期和时间；
// - DATE：仅表示日期；
// - TIME：仅表示时间；
// - TIMESTAMP：和DATETIME类似，但是数据库会在创建或者更新记录的时候同时修改TIMESTAMP。



//在使用Java程序操作数据库时，我们需要把数据库类型与Java类型映射起来。下表是数据库类型与Java新旧API的映射关系：
//数据库	        对应Java类（旧）	        对应Java类（新）
//DATETIME	    java.util.Date	        LocalDateTime
//DATE	        java.sql.Date	        LocalDate
//TIME	        java.sql.Time	        LocalTime
//TIMESTAMP	    java.sql.Timestamp	    LocalDateTime



//实际上，在数据库中，我们需要存储的最常用的是时刻（Instant），因为有了时刻信息，就可以根据用户自己选择的时区，显示出正确的本地时间。
// 所以，最好的方法是直接用长整数long表示，在数据库中存储为BIGINT类型。



//通过存储一个long型时间戳，我们可以编写一个timestampToString()的方法，非常简单地为不同用户以不同的偏好来显示不同的本地时间：
//import java.time.*;
//import java.time.format.*;
//import java.util.Locale;
//
//public class Main {
//    public static void main(String[] args) {
//        long ts = 1574208900000L;
//        System.out.println(timestampToString(ts, Locale.CHINA, "Asia/Shanghai")); // 2019年11月20日 08:15
//        System.out.println(timestampToString(ts, Locale.US, "America/New_York")); // Nov 19, 2019, 7:15?PM
//    }
//
//    static String timestampToString(long epochMilli, Locale lo, String zoneId) {
//        Instant ins = Instant.ofEpochMilli(epochMilli);
//        DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
//        return f.withLocale(lo).format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
//    }
//}
//$ java Main.java
//2019年11月20日 08:15
//Nov 19, 2019, 7:15?PM








//小结
//处理日期和时间时，尽量使用新的java.time包；
//
//在数据库中存储时间戳时，尽量使用long型时间戳，它具有省空间，效率高，不依赖数据库的优点。







