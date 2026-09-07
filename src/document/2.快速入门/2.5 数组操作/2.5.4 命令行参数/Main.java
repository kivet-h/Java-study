/**
 * 2.5.4 命令行参数
 */

//Java程序的入口是main方法，而main方法可以接受一个命令行参数，它是一个String[]数组。

//这个命令行参数由JVM接收用户输入并传给main方法：
//public class Main {
//    public static void main(String[] args) {
//        for (String arg : args) {
//            System.out.println(arg);
//        }
//    }
//}

//我们可以利用接收到的命令行参数，根据不同的参数执行不同的代码。例如，实现一个-version参数，打印程序版本号：
//public class Main {
//    public static void main(String[] args) {
//        for (String arg : args) {
//            if ("--version".equals(arg)) {
//                System.out.println("version 1.0");
//            } else if ("--help".equals(arg)) {
//                System.out.println("help");
//            } else {
//                System.out.println("other");
//            }
//        }
//    }
//}
//$ java Main.java --version --help 123
//version 1.0
//help
//other


//直接执行
//$ java Main.java
// 无任何打印，因为args数组为空，循环体一次都不会执行。








































































































































































































































