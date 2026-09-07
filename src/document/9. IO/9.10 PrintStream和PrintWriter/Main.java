/**
 * 9.10 PrintStream和PrintWriter
 */

//PrintStream是一种FilterOutputStream，它在OutputStream的接口上，额外提供了一些写入各种数据类型的方法：
// - 写入int：print(int)
// - 写入boolean：print(boolean)
// - 写入String：print(String)
// - 写入Object：print(Object)，实际上相当于print(object.toString())
// - ...

//以及对应的一组println()方法，它会自动加上换行符。

//我们经常使用的System.out.println()实际上就是使用PrintStream打印各种数据。其中，System.out是系统默认提供的PrintStream，表示标准输出：
//System.out.print(12345); // 输出12345
//System.out.print(new Object()); // 输出类似java.lang.Object@3c7a835a
//System.out.println("Hello"); // 输出Hello并换行
//System.err是系统默认提供的标准错误输出。

//PrintStream和OutputStream相比，除了添加了一组print()/println()方法，可以打印各种数据类型，比较方便外，
// 它还有一个额外的优点，就是不会抛出IOException，这样我们在编写代码的时候，就不必捕获IOException。















// ==================================================== PrintWriter ====================================================
//PrintStream最终输出的总是byte数据，而PrintWriter则是扩展了Writer接口，它的print()/println()方法最终输出的是char数据。两者的使用方法几乎是一模一样的：
//import java.io.*;
//
//public class Main {
//    public static void main(String[] args)     {
//        StringWriter buffer = new StringWriter();
//        try (PrintWriter pw = new PrintWriter(buffer)) {
//            pw.println("Hello");
//            pw.println(12345);
//            pw.println(true);
//        }
//        System.out.println(buffer.toString());
//    }
//}


















//小结
//PrintStream是一种能接收各种数据类型的输出，打印数据时比较方便：
// - System.out是标准输出；
// - System.err是标准错误输出。
//PrintWriter是基于Writer的输出。