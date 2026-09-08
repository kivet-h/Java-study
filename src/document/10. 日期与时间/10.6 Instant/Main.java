/**
 * 10.6 Instant
 */


//我们已经讲过，计算机存储的当前时间，本质上只是一个不断递增的整数。Java提供的System.currentTimeMillis()返回的就是以毫秒表示的当前时间戳。

//这个当前时间戳在java.time中以Instant类型表示，我们用Instant.now()获取当前时间戳，效果和System.currentTimeMillis()类似：
//import java.time.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Instant now = Instant.now();
//        System.out.println(now.getEpochSecond()); // 1788877733（秒）
//        System.out.println(now.toEpochMilli()); // 1788877733530（毫秒）
//        System.out.println(System.currentTimeMillis()); // 1788877733530（毫秒）
//    }
//}
//$ java Main.java
//1788877733
//1788877733530
//1788877733530






//实际上，Instant内部只有两个核心字段：
//public final class Instant implements ... {
//    private final long seconds;
//    private final int nanos;
//}





//一个是以秒为单位的时间戳，一个是更精确的纳秒精度。它和System.currentTimeMillis()返回的long相比，只是多了更高精度的纳秒。

//既然Instant就是时间戳，那么，给它附加上一个时区，就可以创建出ZonedDateTime：
//import java.time.*;
//public class Main {
//    public static void main(String[] args) {
//        // 以指定时间戳创建Instant:
//        Instant ins = Instant.ofEpochSecond(1568568760);
//        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
//        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]
//    }
//}
//$ java Main.java
//2019-09-16T01:32:40+08:00[Asia/Shanghai]




//可见，对于某一个时间戳，给它关联上指定的ZoneId，就得到了ZonedDateTime，继而可以获得了对应时区的LocalDateTime。
//所以，LocalDateTime，ZoneId，Instant，ZonedDateTime和long都可以互相转换：
//┌─────────────┐
//│LocalDateTime│────┐
//└─────────────┘    │    ┌─────────────┐
//                   ├───▶│ZonedDateTime│
//┌─────────────┐    │    └─────────────┘
//│   ZoneId    │────┘           ▲
//└─────────────┘      ┌─────────┴─────────┐
//                     │                   │
//                     ▼                   ▼
//              ┌─────────────┐     ┌─────────────┐
//              │   Instant   │◀───▶│    long     │
//              └─────────────┘     └─────────────┘

//转换的时候，只需要留意long类型以毫秒还是秒为单位即可。













//小结
//Instant表示高精度时间戳，它可以和ZonedDateTime以及long互相转换。




