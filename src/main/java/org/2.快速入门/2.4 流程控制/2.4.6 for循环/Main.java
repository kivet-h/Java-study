/**
 * 2.4.6 for 循环
 */

//for循环的功能非常强大，它使用计数器实现循环。for循环会先初始化计数器，然后，在每次循环前检测循环条件，在每次循环后更新计数器。计数器变量通常命名为i。

//for循环的用法是：
//for (初始条件; 循环检测条件; 循环后更新计数器) {
//    // 执行语句
//}



//我们把1到100求和用for循环改写一下：
//public class Main {
//    public static void main(String[] args) {
//        int sum = 0;
//        for (int i = 1; i <= 100; i++) {
//            sum += i;
//        }
//        System.out.println(sum); // ==> 5050
//    }
//}

//在for循环执行前，会先执行初始化语句int i=1，它定义了计数器变量i并赋初始值为1，
// 然后，循环前先检查循环条件i<=100，循环后自动执行i++，
// 因此，和while循环相比，for循环把更新计数器的代码统一放到了一起。
// 在for循环的循环体内部，不需要去更新变量i。




//如果我们要对一个整型数组的所有元素求和，可以用for循环实现：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        int sum = 0;
//        for (int i=0; i<ns.length; i++) {
//            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
//            sum = sum + ns[i];
//        }
//        System.out.println("sum = " + sum);
//    }
//}
// ==>
//i = 0, ns[i] = 1
//i = 1, ns[i] = 4
//i = 2, ns[i] = 9
//i = 3, ns[i] = 16
//i = 4, ns[i] = 25
//sum = 55


//上面代码的循环条件是i<ns.length。因为ns数组的长度是5，因此，当循环5次后，i的值被更新为5，就不满足循环条件，因此for循环结束。


//如果把循环条件改为i<=ns.length，会出现什么问题？
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        int sum = 0;
//        for (int i = 0; i <= ns.length; i++) {
//            System.out.println("i = " + i + ", ns[i] = " + ns[i]);
//            sum = sum + ns[i];
//        }
//        System.out.println("sum = " + sum); //  报错：Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5 at Main.main(Main.java:60)
//    }
//}




//注意for循环的初始化计数器总是会被执行，并且for循环也可能循环0次。



//使用for循环时，千万不要在循环体内修改计数器！在循环体中修改计数器常常导致莫名其妙的逻辑错误。对于下面的代码：
// for
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        for (int i=0; i<ns.length; i++) {
//            System.out.println(ns[i]);
//            i = i + 1;
//        }
//    }
//}
// ==>
//1
//9
//25

//虽然不会报错，但是，数组元素只打印了一半，原因是循环内部的i = i + 1导致了计数器变量每次循环实际上加了2（因为for循环还会自动执行i++）。
// 因此，在for循环中，不要修改计数器的值。计数器的初始化、判断条件、每次循环后的更新条件统一放到for()语句中可以一目了然。


//如果希望只访问索引号为偶数的数组元素，应该把for循环改写为：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        for (int i=0; i<ns.length; i=i+2) {
//            System.out.println(ns[i]);
//        }
//    }
//}
// ==>
//1
//9
//25

//通过更新计数器的语句i=i+2就达到了这个效果，从而避免了在循环体内去修改变量i。









//使用for循环时，计数器变量i要尽量定义在for循环中：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        for (int i=0; i<ns.length; i++) {
//            System.out.println(ns[i]);
//        }
//        // 无法访问i
//        int n = i; // compile error!
//    }
//}









//如果变量i定义在for循环外：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        int i;
//        for (i=0; i<ns.length; i++) {
//            System.out.println(ns[i]);
//        }
//        // 仍然可以使用i
//        int n = i;
//
//    }
//}
// ==>
//1
//4
//9
//16
//25

//但是这样的话，退出for循环后，变量i仍然可以被访问，这就破坏了变量应该把访问范围缩到最小的原则。




















// ================================================================== 灵活使用for循环 ==================================================================
//for循环还可以缺少初始化语句、循环条件和每次循环更新语句，例如：
// 不设置结束条件:
//for (int i=0; ; i++) {
//    ...
//}

// 不设置结束条件和更新语句:
//for (int i=0; ;) {
//    ...
//}

// 什么都不设置:
//for (;;) {
//    ...
//}

//通常不推荐这样写，但是，某些情况下，是可以省略for循环的某些语句的。




















// ================================================================== for each循环 ==================================================================
//for循环经常用来遍历数组，因为通过计数器可以根据索引来访问数组的每个元素：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 4, 9, 16, 25 };
//        for (int i=0; i<ns.length; i++) {
//            System.out.println(ns[i]);
//        }
//    }
//}


//但是，很多时候，我们实际上真正想要访问的是数组每个元素的值。Java还提供了另一种for each循环，它可以更简单地遍历数组：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 2, 3, 4 };
//        for (int n : ns) {
//            System.out.println(n);
//        }
//    }
//}
// ==>
//1
//2
//3
//4


//和for循环相比，for each循环的变量n不再是计数器，而是直接对应到数组的每个元素。for each循环的写法也更简洁。但是，for each循环无法指定遍历顺序，也无法获取数组的索引。

//除了数组外，for each循环能够遍历所有“可迭代”的数据类型，包括后面会介绍的List、Map等。










































































































