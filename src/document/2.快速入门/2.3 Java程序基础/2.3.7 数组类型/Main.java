/**
 * 2.3.7 数组类型
 */


//可以使用数组来表示“一组”int类型。代码如下：
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = new int[5];
//        ns[0] = 1;
//        ns[1] = 2;
//        ns[2] = 3;
//        ns[3] = 4;
//        ns[4] = 5;
//        System.out.println(Arrays.toString(ns)); // [1, 2, 3, 4, 5]
//    }
//}

//定义一个数组类型的变量，使用数组类型“类型[]”，例如，int[]。
// 和单个基本类型变量不同，数组变量初始化必须使用new int[5]表示创建一个可容纳5个int元素的数组。

//Java的数组有几个特点：
// 1. 数组所有元素初始化为默认值。各个类型的数组默认值如下：
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        byte[] arr1 = new byte[5];
//        System.out.println(Arrays.toString(arr1)); // [0, 0, 0, 0, 0]
//
//        byte[] arr2 = new byte[5];
//        System.out.println(Arrays.toString(arr2)); // [0, 0, 0, 0, 0]
//
//        int[] arr3 = new int[5];
//        System.out.println(Arrays.toString(arr3)); // [0, 0, 0, 0, 0]
//
//        long[] arr4 = new long[5];
//        System.out.println(Arrays.toString(arr4)); // [0, 0, 0, 0, 0]
//
//        float[] arr5 = new float[5];
//        System.out.println(Arrays.toString(arr5)); // [0.0, 0.0, 0.0, 0.0, 0.0]
//
//        double[] arr6 = new double[5];
//        System.out.println(Arrays.toString(arr6)); // [0.0, 0.0, 0.0, 0.0, 0.0]
//
//        char[] arr7 = new char[5];
//        System.out.println(Arrays.toString(arr7)); // [ ,  ,  ,  ,  ]
//
//        boolean[] arr8 = new boolean[5];
//        System.out.println(Arrays.toString(arr8)); // [false, false, false, false, false]
//
//        String[] arr9 = new String[5];
//        System.out.println(Arrays.toString(arr9)); // [null, null, null, null, null]
//    }
//}


//2. 数组一旦创建后，大小就不可改变。





//要访问数组中的某一个元素，需要使用索引。数组索引从0开始，例如，5个元素的数组，索引范围是0~4。

//可以修改数组中的某一个元素，使用赋值语句，例如，ns[1] = 79;。


//可以用数组变量.length获取数组大小：
// 数组
//public class Main {
//    public static void main(String[] args) {
//        // 5位同学的成绩:
//        int[] ns = new int[5];
//        System.out.println(ns.length); // 5
//    }
//}





//数组是引用类型，在使用索引访问数组元素时，如果索引超出范围，运行时将报错：
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = new int[3];
//        System.out.println(ns[3]); // 报错：xception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3 at Main.main(Main.java:84)
//    }
//}








//也可以在定义数组时直接指定初始化的元素，这样就不必写出数组大小，而是由编译器自动推算数组大小。例如：
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = new int[] { 1, 2, 3, 4, 5 };
//        System.out.println(Arrays.toString(ns)); // [1, 2, 3, 4, 5]
//    }
//}




//还可以进一步简写为：
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        int[] ns = { 1, 2, 3, 4, 5 };
//        System.out.println(Arrays.toString(ns)); // [1, 2, 3, 4, 5]
//    }
//}












//注意数组是引用类型，并且数组大小不可变。我们观察下面的代码：
// 数组
//public class Main {
//    public static void main(String[] args) {
//        // 5位同学的成绩:
//        int[] ns;
//        ns = new int[] { 68, 79, 91, 85, 62 };
//        System.out.println(ns.length); // 5
//        ns = new int[] { 1, 2, 3 };
//        System.out.println(ns.length); // 3
//    }
//}
// 上面结果，看上去好像是变了，但其实根本没变。
//对于数组ns来说，执行ns = new int[] { 68, 79, 91, 85, 62 };时，它指向一个5个元素的数组：
//        ns
//        │
//        ▼
//  ┌───┬───┬───┬───┬───┬───┬───┐
//  │   │68 │79 │91 │85 │62 │   │
//  └───┴───┴───┴───┴───┴───┴───┘

//执行ns = new int[] { 1, 2, 3 };时，它指向一个新的3个元素的数组：
//         ns ──────────────────────┐
//                                  │
//                                  ▼
//    ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
//    │   │68 │79 │91 │85 │62 │   │ 1 │ 2 │ 3 │   │
//    └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘

//但是，原有的5个元素的数组并没有改变，只是无法通过变量ns引用到它们而已。



// ========================================================= 字符串数组 =========================================================
//如果数组元素不是基本类型，而是一个引用类型，那么，修改数组元素会有哪些不同？

//字符串是引用类型，因此我们先定义一个字符串数组：
//String[] names = { "ABC", "XYZ", "zoo" };

//对于String[]类型的数组变量names，它实际上包含3个元素，但每个元素都指向某个字符串对象：
//              ┌─────────────────────────┐
//        names │   ┌─────────────────────┼───────────┐
//          │   │   │                     │           │
//          ▼   │   │                     ▼           ▼
//    ┌───┬───┬─┴─┬─┴─┬───┬───────┬───┬───────┬───┬───────┬───┐
//    │   │░░░│░░░│░░░│   │ "ABC" │   │ "XYZ" │   │ "zoo" │   │
//    └───┴─┬─┴───┴───┴───┴───────┴───┴───────┴───┴───────┴───┘
//          │                 ▲
//          └─────────────────┘

//对names[1]进行赋值，例如names[1] = "cat";，效果如下：
//              ┌─────────────────────────────────────────────────┐
//        names │   ┌─────────────────────────────────┐           │
//          │   │   │                                 │           │
//          ▼   │   │                                 ▼           ▼
//    ┌───┬───┬─┴─┬─┴─┬───┬───────┬───┬───────┬───┬───────┬───┬───────┬───┐
//    │   │░░░│░░░│░░░│   │ "ABC" │   │ "XYZ" │   │ "zoo" │   │ "cat" │   │
//    └───┴─┬─┴───┴───┴───┴───────┴───┴───────┴───┴───────┴───┴───────┴───┘
//         │                 ▲
//         └─────────────────┘

//这里注意到原来names[1]指向的字符串"XYZ"并没有改变，仅仅是将names[1]的引用从指向"XYZ"改成了指向"cat"，其结果是字符串"XYZ"再也无法通过names[1]访问到了。




//// 数组
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        String[] names = {"ABC", "XYZ", "zoo"};
//        String s = names[1];
//        names[1] = "cat";
//        System.out.println(s); // XYZ
//        System.out.println(Arrays.toString(names)); // ["ABC", "cat", "zoo"]
//    }
//}









































































































































































































































































































