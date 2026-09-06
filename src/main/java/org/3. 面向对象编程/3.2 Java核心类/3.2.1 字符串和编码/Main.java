/**
 * 3.2.1 字符串和编码
 */

// ================================================================== String ==================================================================
//在Java中，String是一个引用类型，它本身也是一个class。但是，Java编译器对String有特殊处理，即可以直接用"..."来表示一个字符串：
//String s1 = "Hello!";

//实际上字符串在String内部是通过一个char[]数组表示的，因此，按下面的写法也是可以的：
//String s2 = new String(new char[] {'H', 'e', 'l', 'l', 'o', '!'});

//因为String太常用了，所以Java提供了"..."这种字符串字面量表示方法。

//Java字符串的一个重要特点就是字符串不可变。这种不可变性是通过内部的private final char[]字段，以及没有任何修改char[]的方法实现的。

//我们来看一个例子：

// String
//public class Main {
//    public static void main(String[] args) {
//        String s = "Hello";
//        System.out.println(s);
//        s = s.toUpperCase();
//        System.out.println(s);
//    }
//}
//$ java Main.java
//Hello
//HELLO
//根据上面代码的输出，试解释字符串内容是否改变。

//1. 字符串对象本身不可变："Hello" 一旦创建，其内部字符数组永远不会改变。
//2. 改变的是引用：s 这个变量从指向 "Hello"，变成了指向 "HELLO"。
//3. "修改"字符串的方法都返回新对象：toUpperCase()、substring()、replace() 等都不会修改原字符串，而是创建并返回新的字符串对象。


//public class Main {
//    public static void main(String[] args) {
//        String s = "Hello";
//        String s1 = s;
//        s = s.toUpperCase();
//        System.out.println(s); // HELLO
//        System.out.println(s1);// hello
//    }
//}
//$ java Main.java
//HELLO
//Hello






















// ================================================================== 字符串比较 ==================================================================
//当我们想要比较两个字符串是否相同时，要特别注意，我们实际上是想比较字符串的内容是否相同。必须使用equals()方法而不能用==。
//
//我们看下面的例子：
// String
//public class Main {
//    public static void main(String[] args) {
//        String s1 = "hello";
//        String s2 = "hello";
//        System.out.println(s1 == s2); // true
//        System.out.println(s1.equals(s2)); // true
//    }
//}
//$ java Main.java
//true
//true
//从表面上看，两个字符串用==和equals()比较都为true，但实际上那只是Java编译器在编译期，会自动把所有相同的字符串当作一个对象放入常量池，自然s1和s2的引用就是相同的。




// String
//public class Main {
//    public static void main(String[] args) {
//        String s1 = "hello";
//        String s2 = "HELLO".toLowerCase();
//        System.out.println(s1 == s2); // false
//        System.out.println(s1.equals(s2)); // true
//    }
//}
//$ java Main.java
//false
//true


// 结论：
// 两个字符串比较，必须总是使用equals()方法。
// 要忽略大小写比较，使用equalsIgnoreCase()方法。



//String类还提供了多种方法来搜索子串、提取子串。常用的方法有：
// 是否包含子串:
//"Hello".contains("ll"); // true
//注意到contains()方法的参数是CharSequence而不是String，因为CharSequence是String实现的一个接口。

//搜索子串的更多的例子：
//"Hello".indexOf("l"); // 2
//"Hello".lastIndexOf("l"); // 3
//"Hello".startsWith("He"); // true
//"Hello".endsWith("lo"); // true


//提取子串的例子：
//"Hello".substring(2); // "llo"
//"Hello".substring(2, 4); "ll"
//注意索引号是从0开始的。





















// ================================================================== 去除首尾空白字符 ==================================================================
//使用trim()方法可以移除字符串首尾空白字符。空白字符包括空格，\t，\r，\n：
//"  \tHello\r\n ".trim(); // "Hello"
//注意：trim()并没有改变字符串的内容，而是返回了一个新字符串。

//另一个strip()方法也可以移除字符串首尾空白字符。它和trim()不同的是，类似中文的空格字符\u3000也会被移除：
//"\u3000Hello\u3000".strip(); // "Hello"
//" Hello ".stripLeading(); // "Hello "
//" Hello ".stripTrailing(); // " Hello"

//方法	                引入版本	    作用位置	    移除范围	                说明
//trim()	            Java 1.0	首尾两端	    码点值 ≤ U+0020 的字符	传统方法，范围较窄
//strip()	            Java 11	    首尾两端	    所有 Unicode 空白字符	    现代推荐替代 trim()
//stripLeading()	    Java 11	    仅开头	    所有 Unicode 空白字符	    —
//stripTrailing()	    Java 11	    仅末尾	    所有 Unicode 空白字符	    —



//String还提供了isEmpty()和isBlank()来判断字符串是否为空和空白字符串：
//"".isEmpty();         // true， 因为字符串长度为0
//"  ".isEmpty();       // false，因为字符串长度不为0
//"  \n".isBlank();     // true， 因为只包含空白字符
//" Hello ".isBlank();  // false，因为包含非空白字符





















// ================================================================== 替换子串 ==================================================================
//要在字符串中替换子串，有两种方法。

// 一种是根据字符或字符串替换：
//public class Main {
//    public static void main(String[] args) {
//        String s = "hello";
//
//        String s1 = s.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
//        System.out.println(s1);
//
//        String s2 = s.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"
//        System.out.println(s2);
//
//        System.out.println(s); // hello
//    }
//}
//replace不会改变原字符串，而是返回一个新的字符串


//另一种是通过正则表达式替换：
//String s = "A,,B;C ,D";
//s.replaceAll("[\\,\\;\\s]+", ","); // "A,B,C,D"
//上面的代码通过正则表达式，把匹配的子串统一替换为","。

//解释上面正则：
// [...]	字符类，匹配方括号内的任意一个字符
// \,	    匹配逗号 ,（反斜杠转义，虽然在字符类里逗号不转义也能工作，但转义更规范）
// \;	    匹配分号 ;（同上，转义写法）
// \s	    匹配任何空白字符：空格、\t、\n、\r 等
// +	    量词，表示前面的字符类连续出现 1 次或多次


// ",,"	 两个逗号   都属于字符类，且连续，"+"把它们整体匹配，替换为一个","
// " ,"  空格+逗号  空格属于"\s"，逗号属于"\,"，连续出现，"+"整体匹配，替换为一个","






















// ================================================================== 分割字符串 ==================================================================
//要分割字符串，使用split()方法，并且传入的也是正则表达式：
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        String s = "A,B,C,D";
//        String[] ss1 = s.split("\\,");
//        System.out.println(Arrays.toString(ss1)); // [A, B, C, D]
//
////        直接传字符串也可以
//        String[] ss = s.split(",");
//        System.out.println(Arrays.toString(ss)); // [A, B, C, D]
//    }
//}

























// ================================================================== 拼接字符串 ==================================================================
//拼接字符串使用静态方法join()，它用指定的字符串连接字符串数组：
//public class Main {
//    public static void main(String[] args) {
//        String[] arr = {"A", "B", "C", "D"};
//        String s = String.join("***", arr);
//        System.out.println(s); // A***B***C***D
//    }
//}























// ================================================================== 格式化字符串 ==================================================================
//字符串提供了formatted()方法和format()静态方法，可以传入其他参数，替换占位符，然后生成新的字符串：

//formatted() 和 format() 的区别：调用方式不同，功能完全相同。

//为什么引入 formatted()？
//主要目的是：formatted() 的设计初衷是让代码更流畅、更易读，尤其是格式字符串较长时。
//// 以前：格式字符串和参数混在一起，可读性稍差
//String msg = String.format("User %s logged in at %s from IP %s", user, time, ip);
//// 现在：格式字符串在前，参数在后，结构更清晰
//String msg = "User %s logged in at %s from IP %s".formatted(user, time, ip);

//两者底层完全一致，性能、格式化规则、占位符语法（%s、%d、%.2f 等）没有任何区别。




// String
//public class Main {
//    public static void main(String[] args) {
//        String s = "Hi %s, your score is %d!";
//        System.out.println(s.formatted("Alice", 80)); // Hi Alice, your score is 80!
//        System.out.println(String.format(s, "Bob", 59)); // Hi Bob, your score is 59!
//    }
//}

//有几个占位符，后面就传入几个参数。

//我们经常用这个方法来格式化信息。常用的占位符有：
//%s：显示字符串；
//%d：显示整数；
//%x：显示十六进制整数；
//%f：显示浮点数。

// 参数类型要和占位符一致。
//System.out.println(String.format("Hi %s, your score is %d!", "Bob", 59.5)); // 报错，因为59.5对应的是%d，%d是整数，但是59.5不是，所以报错



//占位符还可以带格式，例如%.2f表示显示两位小数。
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5555)); // Hi Bob, your score is 59.56!
//    }
//}


// 如果你不确定用啥占位符，那就始终用%s，因为%s可以显示任何数据类型。
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(String.format("Hi %s, your score is %s!", "Bob", 59.5555)); // Hi Bob, your score is 59.5555!
//    }
//}


// 要查看完整的格式化语法，请参考JDK文档(https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Formatter.html#syntax)。

























// ================================================================== 类型转换 ==================================================================
//要把任意基本类型或引用类型转换为字符串，可以使用静态方法valueOf()。
// 这是一个重载方法，编译器会根据参数自动选择合适的方法：
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(String.valueOf(123));             // "123"
//        System.out.println(String.valueOf(45.67));           // "45.67"
//        System.out.println(String.valueOf(true));            // "true"
//        System.out.println(String.valueOf(new Object()));    // 类似java.lang.Object@636be97c
//    }
//}



//要把字符串转换为其他类型，就需要根据情况。例如，把字符串转换为int类型：
//public class Main {
//    public static void main(String[] args) {
//        int n1 = Integer.parseInt("123");
//        System.out.println(n1); // 123
//
//        int n2 = Integer.parseInt("ff", 16);
//        System.out.println(n2); // 255
//
//        int n3 = Integer.parseInt("123aa");
//        System.out.println(n3); // 报错
//    }
//}




//把字符串转换为boolean类型：
//public class Main {
//    public static void main(String[] args) {
//        boolean b1 = Boolean.parseBoolean("true");
//        System.out.println(b1); // true
//
//        boolean b2 = Boolean.parseBoolean("FALSE");
//        System.out.println(b2); // false
//    }
//}





//要特别注意，Integer有个getInteger(String)方法，它不是将字符串转换为int，而是把该字符串对应的系统变量转换为Integer：
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(Integer.getInteger("java.version")); // 输出为null。但是教程文档上写的是：// 版本号，11
//    }
//}


























// ================================================================== 转换为char[] ==================================================================
//String和char[]类型可以互相转换，方法是：
//public class Main {
//    public static void main(String[] args) {
//        // String -> char[]
//        char[] cs = "Hello".toCharArray();
//        System.out.println(cs); // Hello
//
//        // 为什么 System.out.println 打印char[]类型，结果看着像是字符串的Hello?
//        // println 对 char[] 有特殊照顾，所以输出看起来像字符串。如直接打印char[]类型的值
//        char[] csq1 = {'H', 'e', 'l', 'l', 'o'};
//        System.out.println(csq1); // Hello
//
//
//        // char[] -> String
//        String s = new String(cs);
//        System.out.println(s); // Hello
//    }
//}



//如果修改了char[]数组，String并不会改变：
// String <-> char[]
//public class Main {
//    public static void main(String[] args) {
//        char[] cs = "Hello".toCharArray();
//        String s = new String(cs);
//        System.out.println(s); // Hello
//        cs[0] = 'X';
//        System.out.println(s); // Hello   如果修改了char[]数组，String并不会改变：
//        System.out.println(cs);// Xello
//    }
//}
//这是因为通过new String(char[])创建新的String实例时，它并不会直接引用传入的char[]数组，而是会复制一份，
// 所以，修改外部的char[]数组不会影响String实例内部的char[]数组，因为这是两个不同的数组。


//从String的不变性设计可以看出，如果传入的对象有可能改变，我们需要复制而不是直接引用。

//例如，下面的代码设计了一个Score类保存一组学生的成绩：
// int[]
//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) {
//        int[] scores = new int[] { 88, 77, 51, 66 };
//        Score s = new Score(scores);
//        s.printScores();
//        scores[2] = 99;
//        s.printScores();
//    }
//}
//
//class Score {
//    private int[] scores;
//    public Score(int[] scores) {
//        this.scores = scores;
//    }
//
//    public void printScores() {
//        System.out.println(Arrays.toString(scores));
//    }
//}
//$ java Main.java
//[88, 77, 51, 66]
//[88, 77, 99, 66]

//观察两次输出，由于Score内部直接引用了外部传入的int[]数组，这会造成外部代码对int[]数组的修改，影响到Score类的字段。如果外部代码不可信，这就会造成安全隐患。



//请修复Score的构造方法，使得外部代码对数组的修改不影响Score实例的int[]字段。
//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) {
//        int[] scores = new int[] { 88, 77, 51, 66 };
//        Score s = new Score(scores);
//        s.printScores();
//        scores[2] = 99;
//        s.printScores();
//    }
//}
//
//class Score {
//    private int[] scores;
//    public Score(int[] scores) {
////         方法一
////        this.scores = scores.clone();
//
////        方法二
////        this.scores = Arrays.copyOf(scores, scores.length);
//
////         方法三
//        this.scores = new int[scores.length];
//        System.arraycopy(scores, 0, this.scores, 0, scores.length);
//    }
//
//    public void printScores() {
//        System.out.println(Arrays.toString(scores));
//    }
//}
//$ java Main.java
//[88, 77, 51, 66]
//[88, 77, 51, 66]





























// ================================================================== 字符编码 ==================================================================
//在早期的计算机系统中，为了给字符编码，美国国家标准学会（American National Standard Institute：ANSI）制定了一套英文字母、数字和常用符号的编码，它占用一个字节，编码范围从0到127，最高位始终为0，称为ASCII编码。例如，字符'A'的编码是0x41，字符'1'的编码是0x31。

//如果要把汉字也纳入计算机编码，很显然一个字节是不够的。GB2312标准使用两个字节表示一个汉字，其中第一个字节的最高位始终为1，以便和ASCII编码区分开。例如，汉字'中'的GB2312编码是0xd6d0。

//类似的，日文有Shift_JIS编码，韩文有EUC-KR编码，这些编码因为标准不统一，同时使用，就会产生冲突。

//为了统一全球所有语言的编码，全球统一码联盟发布了Unicode编码，它把世界上主要语言都纳入同一个编码，这样，中文、日文、韩文和其他语言就不会冲突。

//Unicode编码需要两个或者更多字节表示，我们可以比较中英文字符在ASCII、GB2312和Unicode的编码：

//英文字符'A'的ASCII编码和Unicode编码：
//         ┌────┐
//ASCII:   │ 41 │
//         └────┘
//         ┌────┬────┐
//Unicode: │ 00 │ 41 │
//         └────┴────┘
//英文字符的Unicode编码就是简单地在前面添加一个00字节。

//中文字符'中'的GB2312编码和Unicode编码：
//         ┌────┬────┐
//GB2312:  │ d6 │ d0 │
//         └────┴────┘
//         ┌────┬────┐
//Unicode: │ 4e │ 2d │
//         └────┴────┘

//那我们经常使用的UTF-8又是什么编码呢？
// 因为英文字符的Unicode编码高字节总是00，包含大量英文的文本会浪费空间，所以，出现了UTF-8编码，它是一种变长编码，用来把固定长度的Unicode编码变成1～4字节的变长编码。
// 通过UTF-8编码，英文字符'A'的UTF-8编码变为0x41，正好和ASCII码一致，而中文'中'的UTF-8编码为3字节0xe4b8ad。

//UTF-8编码的另一个好处是容错能力强。如果传输过程中某些字符出错，不会影响后续字符，因为UTF-8编码依靠高字节位来确定一个字符究竟是几个字节，它经常用来作为传输编码。

//在Java中，char类型实际上就是两个字节的Unicode编码。如果我们要手动把字符串转换成其他编码，可以这样做：
//byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
//byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
//byte[] b2 = "Hello".getBytes("GBK"); // 按GBK编码转换
//byte[] b3 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换

//注意：转换编码后，就不再是char类型，而是byte类型表示的数组。

//如果要把已知编码的byte[]转换为String，可以这样做：
//byte[] b = ...
//String s1 = new String(b, "GBK"); // 按GBK转换
//String s2 = new String(b, StandardCharsets.UTF_8); // 按UTF-8转换


//始终牢记：Java的String和char在内存中总是以Unicode编码表示。






























// ================================================================== 延伸阅读 ==================================================================
//对于不同版本的JDK，String类在内存中有不同的优化方式。具体来说，早期JDK版本的String总是以char[]存储，它的定义如下：
//public final class String {
//    private final char[] value;
//    private final int offset;
//    private final int count;
//}

//而较新的JDK版本的String则以byte[]存储：如果String仅包含ASCII字符，则每个byte存储一个字符，否则，每两个byte存储一个字符，这样做的目的是为了节省内存，因为大量的长度较短的String通常仅包含ASCII字符：
//public final class String {
//private final byte[] value;
//private final byte coder; // 0 = LATIN1, 1 = UTF16

//对于使用者来说，String内部的优化不影响任何已有代码，因为它的public方法签名是不变的。












//小结:
//Java字符串String是不可变对象；
//字符串操作不改变原字符串内容，而是返回新字符串；
//常用的字符串操作：提取子串、查找、替换、大小写转换等；
//Java使用Unicode编码表示String和char；
//转换编码就是将String和byte[]转换，需要指定编码；
//转换为byte[]时，始终优先考虑UTF-8编码。

















