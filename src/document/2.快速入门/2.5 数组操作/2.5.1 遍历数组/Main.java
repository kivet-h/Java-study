/**
 * 2.5.1 遍历数组
 */

//通过for循环就可以遍历数组。因为数组的每个元素都可以通过索引来访问，因此，使用标准的for循环可以完成一个数组的遍历：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 2, 3, 4, 5 };
//        for (int i = 0; i < ns.length; i++) {
//            int n = ns[i];
//            System.out.println(n);
//        }
//    }
//}
// ==>
//1
//2
//3
//4
//5


//第二种方式是使用for each循环，直接迭代数组的每个元素
// 遍历数组
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 2, 3, 4, 5 };
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
//5


//注意：在for (int n : ns)循环中，变量n直接拿到ns数组的元素，而不是索引。

//显然for each循环更加简洁。但是，for each循环无法拿到数组的索引，因此，到底用哪一种for循环，取决于我们的需要。













// ================================================================== 打印数组内容 ==================================================================
//直接打印数组变量，得到的是数组在JVM中的引用地址：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 2, 3, 4, 5 };
//        System.out.println(ns); // 类似：[I@5f71c76a
//    }
//}

//Java标准库提供了Arrays.toString()，可以快速打印数组内容：
// 遍历数组
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 1, 2, 3, 5, 8 };
//        System.out.println(Arrays.toString(ns)); // [1, 1, 2, 3, 5, 8]
//    }
//}
























































