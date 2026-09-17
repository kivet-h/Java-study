/**
 * 19.3.1 创建Stream
 */

// 要使用 Stream ，就必须先创建它。创建 Stream 有很多种方法，我们来一一介绍。











// ================================================= Stream.of() =================================================
// 创建 Stream 最简单的方式是直接用 Stream.of() 静态方法，传入可变参数即创建了一个能输出确定元素的 Stream ：
// import java.util.stream.Stream;
// public class Main {
//     public static void main (String[] args) {
//         Stream<String> stream = Stream.of("A", "B", "C", "D");
//         stream.forEach(System.out::println);
//     }
// }
// A
// B
// C
// D

// 虽然这种方式基本上没啥实质性用途，但测试的时候很方便。


















// ================================================= 基于数组或Collection =================================================
// 第二种创建 Stream 的方法是基于一个数组或者 Collection ，这样该 Stream 输出的元素就是数组或者 Collection 持有的元素：

// Collection 是接口，但实际开发中我们主要使用它的三个子接口实现：List(列表)、Set(集合)、Queue (队列)
// import java.util.Arrays;
// import java.util.HashSet;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Queue;
// import java.util.Set;
// import java.util.stream.Stream;
// public class Main {
//     public static void main(String[] args) {
//         // 基于数组创建 Stream：
//         Stream<String> stream1 = Arrays.stream(new String[]{"A", "B", "C"});
//         stream1.forEach(System.out::println);
//         // A
//         // B
//         // C
//
//
//         // 基于集合创建 Stream：
//         Stream<String> stream2 = List.of("D", "E", "F").stream();
//         stream2.forEach(System.out::println);
//         // D
//         // E
//         // F
//
//
//         Set<String> set = new HashSet<>();
//         set.add("G");
//         set.add("H");
//         set.add("I");
//         Stream<String> stream3 = set.stream();
//         stream3.forEach(System.out::println);
//         // G
//         // H
//         // I
//
//
//         Queue<String> queue = new LinkedList<>();
//         queue.add("J");
//         queue.add("K");
//         queue.add("L");
//         Stream<String> stream4 = queue.stream();
//         stream4.forEach(System.out::println);
//         // J
//         // K
//         // L
//     }
// }

// 把数组变成 Stream 使用 Arrays.stream() 方法。
// 对于 Collection （ List 、 Set 、Queue 等），直接调用 stream() 方法就可以获得 Stream 。
// 上述创建 Stream 的方法都是把一个现有的序列变为 Stream ，它的元素是固定的。




















// ================================================= 基于Supplier =================================================
// 创建 Stream 还可以通过 Stream.generate() 方法，它需要传入一个 Supplier 对象：
// Stream<String> s = Stream.generate(Supplier<String> sp);

// 基于 Supplier 创建的 Stream 会不断调用 Supplier.get() 方法来不断产生下一个元素，这种Stream 保存的不是元素，而是算法，它可以用来表示无限序列。

// 例如，我们编写一个能不断生成自然数的 Supplier ，它的代码非常简单，每次调用 get() 方法，就生成下一个自然数：
// import java.util.function.Supplier;
// import java.util.stream.Stream;
//
// public class Main {
//     public static void main(String[] args) {
//         Stream<Integer> stream = Stream.generate(new NatualSupplier());
//         stream.limit(10).forEach(System.out::println); // 依次换行打印出1-10的整数
//     }
// }
//
// class NatualSupplier implements Supplier<Integer> {
//     private int n = 0;
//
//     public Integer get() {
//         n++;
//         return n;
//     }
// }



// 上述代码我们用一个 Supplier<Integer> 模拟了一个无限序列（当然受 int 范围限制不是真的无限大）。
// 如果用 List 表示，即便在 int 范围内，也会占用巨大的内存，而 Stream 几乎不占用空间，因为每个元素都是实时计算出来的，用的时候再算。

// 对于无限序列，如果直接调用 forEach() 或者 count() 这些最终求值操作，会进入死循环，因为永远无法计算完这个序列，
// 所以正确的方法是先把无限序列变成有限序列，例如，用 limit() 方法可以截取前面若干个元素，这样就变成了一个有限序列，
// 对这个有限序列调用 forEach() 或者count() 操作就没有问题。



















// ================================================= 其他方法 =================================================
// 创建 Stream 的第三种方法是通过一些API提供的接口，直接获得 Stream 。
// 例如， Files 类的 lines() 方法可以把一个文件变成一个 Stream ，每个元素代表文件的一行内容：

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.stream.Stream;
//
// public class Main {
//     public static void main(String[] args) throws IOException {
//         try (Stream<String> stream = Files.lines(Paths.get("D:\\zhaoshang\\java-lxf\\src\\main\\java\\text.txt"))) {
//             stream.forEach(System.out::println);
//         }
//     }
// }
// 假设text.txt文件下内容如下：
// line 1
// line 2
//
// line 4
// 输出结果为：
// line 1
// line 2
//
// line 4


// 此方法对于按行遍历文本文件十分有用。




// 另外，正则表达式的 Pattern 对象有一个 splitAsStream() 方法，可以直接把一个长字符串分割成 Stream 序列而不是数组：
// import java.util.regex.Pattern;
// import java.util.stream.Stream;
//
// public class Main {
//     public static void main(String[] args) {
//         Pattern p = Pattern.compile("\\s+");
//         Stream<String> s = p.splitAsStream("The quick brown fox jumps over the lazy dog");
//         s.forEach(System.out::println);
//     }
// }
// The
// quick
// brown
// fox
// jumps
// over
// the
// lazy
// dog

















// ================================================= 基本类型 =================================================
// 因为Java的泛型不支持基本类型，所以我们无法用 Stream<int> 这样的类型，会发生编译错误。
// 为了保存 int ，只能使用 Stream<Integer> ，但这样会产生频繁的装箱、拆箱操作。
// 为了提高效率，Java标准库提供了 IntStream 、 LongStream 和 DoubleStream 这三种使用基本类型的Stream ，
// 它们的使用方法和泛型 Stream 没有大的区别，设计这三个 Stream 的目的是提高运行效率：
// import java.util.stream.IntStream;
// import java.util.stream.LongStream;
// import java.util.Arrays;
// import java.util.List;
//
// public class Main {
//     public static void main(String[] args) {
//         // 将int[]数组变为IntStream:
//         IntStream is = Arrays.stream(new int[] { 1, 2, 3 });
//         is.forEach(System.out::println);
//         // 1
//         // 2
//         // 3
//
//         // 将Stream<String>转换为LongStream:
//         LongStream ls = List.of("1", "2", "3").stream().mapToLong(Long::parseLong);
//         ls.forEach(System.out::println);
//         // 1
//         // 2
//         // 3
//     }
// }













// 小结
//  - 创建 Stream 的方法有 ：
//  - 通过指定元素、指定数组、指定 Collection 创建 Stream ；通过 Supplier 创建 Stream ，可以是无限序列；
//  - 通过其他类的相关方法创建。
// 基本类型的 Stream 有 IntStream 、 LongStream 和 DoubleStream 。


