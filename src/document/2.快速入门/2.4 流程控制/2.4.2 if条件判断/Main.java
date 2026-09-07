/**
 * 2.4.2 if条件判断
 */

//在Java程序中，如果要根据条件来决定是否执行某一段代码，就需要if语句。在Java程序中，如果要根据条件来决定是否执行某一段代码，就需要if语句。

//if语句的基本语法是：
//if (条件) {
//        // 条件满足时执行
//}


//根据if的计算结果（true还是false），JVM决定是否执行if语句块（即花括号{}包含的所有语句）。

//让我们来看一个例子：
//public class Main {
//    public static void main(String[] args) {
//        int n = 60;
//        if (n >= 60) {
//            System.out.println("及格了"); // 及格了
//        }
//        System.out.println("END"); // END
//    }
//}

//当条件n >= 60计算结果为true时，if语句块被执行，将打印"及格了"，否则，if语句块将被跳过。修改n的值可以看到执行效果。





////当if语句块只有一行语句时，可以省略花括号{}：
//public class Main {
//    public static void main(String[] args) {
//        int n = 60;
//        if (n >= 60)
//            System.out.println("及格了"); // 及格了
//        System.out.println("END"); // END
//    }
//}



//但是不推荐忽略花括号的写法。
//假设某个时候，突然想给if语句块增加一条语句时：
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        int n = 50;
//        if (n >= 60)
//            System.out.println("及格了");
//        System.out.println("恭喜你"); // 注意这条语句不是if语句块的一部分
//        System.out.println("END");
//    }
//}
//由于使用缩进格式，很容易把两行语句都看成if语句的执行块，但实际上只有第一行语句是if的执行块。
// 在使用git这些版本控制系统自动合并时更容易出问题


















// ================================================================== else ==================================================================
//if语句还可以编写一个else { ... }，当条件判断为false时，将执行else的语句块：
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        int n = 70;
//        if (n >= 60) {
//            System.out.println("及格了");
//        } else {
//            System.out.println("挂科了");
//        }
//        System.out.println("END");
//    }
//}

//注意，else不是必须的。
//还可以用多个if ... else if ...串联。例如：
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        int n = 70;
//        if (n >= 90) {
//            System.out.println("优秀");
//        } else if (n >= 60) {
//            System.out.println("及格了");
//        } else {
//            System.out.println("挂科了");
//        }
//        System.out.println("END");
//    }
//}




//串联的效果其实相当于：
//if (n >= 90) {
//        // n >= 90为true:
//        System.out.println("优秀");
//} else {
//        // n >= 90为false:
//        if (n >= 60) {
//        // n >= 60为true:
//        System.out.println("及格了");
//    } else {
//            // n >= 60为false:
//            System.out.println("挂科了");
//    }
//}





//在串联使用多个if时，要特别注意判断顺序。观察下面的代码：
//// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        int n = 100;
//        if (n >= 60) {
//            System.out.println("及格了");
//        } else if (n >= 90) {
//            System.out.println("优秀");
//        } else {
//            System.out.println("挂科了");
//        }
//    }
//}
//执行发现，n = 100时，满足条件n >= 90，但输出的不是"优秀"，而是"及格了"，原因是if语句从上到下执行时，先判断n >= 60成功后，后续else不再执行，因此，if (n >= 90)没有机会执行了。




//正确的方式是按照判断范围从大到小依次判断：
//// 从大到小依次判断：
//if (n >= 90) {
//    // ...
//} else if (n >= 60) {
//    // ...
//} else {
//    // ...
//}


//或者改写成从小到大依次判断：
//// 从小到大依次判断：
//if (n < 60) {
//    // ...
//} else if (n < 90) {
//    // ...
//} else {
//    // ...
//}


//浮点数在计算机中常常无法精确表示，并且计算可能出现误差，因此，判断浮点数相等用==判断不靠谱：
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        double x = 1 - 9.0 / 10;
//        if (x == 0.1) {
//            System.out.println("x is 0.1");
//        } else {
//            System.out.println("x is NOT 0.1");
//        }
//    }
//}
/// / ==> x is NOT 0.1


//正确的方法是利用差值小于某个临界值来判断：
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        double x = 1 - 9.0 / 10;
//        if (Math.abs(x - 0.1) < 0.00001) {
//            System.out.println("x is 0.1");
//        } else {
//            System.out.println("x is NOT 0.1");
//        }
//    }
//}
//// ==> x is 0.1

























// ================================================================== 判断引用类型相等 ==================================================================
//在Java中，判断值类型的变量是否相等，可以使用==运算符。
// 但是，判断引用类型的变量是否相等，==表示“引用是否相等”，或者说，是否指向同一个对象。

// 例如，下面的两个String类型，它们的内容是相同的，但是，分别指向不同的对象，用==判断，结果为false：
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        String s1 = "hello";
//        String s2 = "HELLO".toLowerCase();
//        System.out.println(s1); // ==> hello
//        System.out.println(s2); // ==> hello
//        if (s1 == s2) {
//            System.out.println("s1 == s2");
//        } else {
//            System.out.println("s1 != s2"); // ==> s1 != s2
//        }
//    }
//}


//要判断引用类型的变量内容是否相等，必须使用equals()方法：
//public class Main {
//    public static void main(String[] args) {
//        String s1 = "hello";
//        String s2 = "HELLO".toLowerCase();
//        System.out.println(s1); // ==> hello
//        System.out.println(s2); // ==> hello
//        if (s1.equals(s2)) {
//            System.out.println("s1 == s2"); // ==> s1 == s2
//        } else {
//            System.out.println("s1 != s2");
//        }
//    }
//}


//注意：执行语句s1.equals(s2)时，如果变量s1为null，会报NullPointerException
// 条件判断
//public class Main {
//    public static void main(String[] args) {
//        String s1 = null;
//        if (s1.equals("hello")) {
//            System.out.println("hello");
//        }
//    }
//}
// ==> 报错：Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.equals(Object)" because "<local1>" is null at Main.main(Main.java:266)


//要避免NullPointerException错误，可以利用短路运算符&&：
//public class Main {
//    public static void main(String[] args) {
//        String s1 = null;
//        if (s1 != null && s1.equals("hello")) {
//            System.out.println("hello");
//        } else {
//            System.out.println("s1为null；或者s1和s2内容不相等"); // ==> s1为null；或者s1和s2内容不相等
//        }
//    }
//}




































































































































































































































































