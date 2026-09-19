/**
 * 19.3.6 其他操作
 */


//我们把Stream提供的操作分为两类：转换操作和聚合操作。除了前面介绍的常用操作外，Stream还提供了一系列非常有用的方法。




















// ============================================================= 排序 =============================================================
//对Stream的元素进行排序十分简单，只需调用sorted()方法：
//import java.util.*;
//import java.util.stream.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> list = List.of("Orange", "apple", "Banana")
//                .stream()
//                .sorted()
//                .collect(Collectors.toList());
//        System.out.println(list);
//    }
//}

//此方法要求Stream的每个元素必须实现Comparable接口。如果要自定义排序，传入指定的Comparator即可：
//List<String> list = List.of("Orange", "apple", "Banana")
//        .stream()
//        .sorted(String::compareToIgnoreCase)
//        .collect(Collectors.toList());

//注意sorted()只是一个转换操作，它会返回一个新的Stream。




















// ============================================================= 去重 =============================================================
//对一个Stream的元素进行去重，没必要先转换为Set，可以直接用distinct()：
//List.of("A", "B", "A", "C", "B", "D")
//    .stream()
//    .distinct()
//    .collect(Collectors.toList()); // [A, B, C, D]





















// ============================================================= 截取 =============================================================
//截取操作常用于把一个无限的Stream转换成有限的Stream，skip()用于跳过当前Stream的前N个元素，limit()用于截取当前Stream最多前N个元素：
//List.of("A", "B", "C", "D", "E", "F")
//    .stream()
//    .skip(2) // 跳过A, B
//    .limit(3) // 截取C, D, E
//    .collect(Collectors.toList()); // [C, D, E]
//截取操作也是一个转换操作，将返回新的Stream。




















// ============================================================= 合并 =============================================================
//将两个Stream合并为一个Stream可以使用Stream的静态方法concat()：
//Stream<String> s1 = List.of("A", "B", "C").stream();
//Stream<String> s2 = List.of("D", "E").stream();
//// 合并:
//Stream<String> s = Stream.concat(s1, s2);
//System.out.println(s.collect(Collectors.toList())); // [A, B, C, D, E]





















// ============================================================= flatMap =============================================================
//如果Stream的元素是集合：
//Stream<List<Integer>> s = Stream.of(
//        Arrays.asList(1, 2, 3),
//        Arrays.asList(4, 5, 6),
//        Arrays.asList(7, 8, 9));

//而我们希望把上述Stream转换为Stream<Integer>，就可以使用flatMap()：
//Stream<Integer> i = s.flatMap(list -> list.stream());

//因此，所谓flatMap()，是指把Stream的每个元素（这里是List）映射为Stream，然后合并成一个新的Stream：
//┌─────────────┬─────────────┬─────────────┐
//│┌───┬───┬───┐│┌───┬───┬───┐│┌───┬───┬───┐│
//││ 1 │ 2 │ 3 │││ 4 │ 5 │ 6 │││ 7 │ 8 │ 9 ││
//│└───┴───┴───┘│└───┴───┴───┘│└───┴───┴───┘│
//└─────────────┴─────────────┴─────────────┘
//                │
//                │flatMap(List -> Stream)
//                │
//                │
//                ▼
//┌───┬───┬───┬───┬───┬───┬───┬───┬───┐
//│ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │
//└───┴───┴───┴───┴───┴───┴───┴───┴───┘





















// ============================================================= 并行 =============================================================
//通常情况下，对Stream的元素进行处理是单线程的，即一个一个元素进行处理。但是很多时候，我们希望可以并行处理Stream的元素，因为在元素数量非常大的情况，并行处理可以大大加快处理速度。

//把一个普通Stream转换为可以并行处理的Stream非常简单，只需要用parallel()进行转换：
//Stream<String> s = ...
//String[] result = s.parallel() // 变成一个可以并行处理的Stream
//        .sorted() // 可以进行并行排序
//        .toArray(String[]::new);
//经过parallel()转换后的Stream只要可能，就会对后续操作进行并行处理。我们不需要编写任何多线程代码就可以享受到并行处理带来的执行效率的提升。





















// ============================================================= 其他聚合方法 =============================================================
//除了reduce()和collect()外，Stream还有一些常用的聚合方法：
//count()：用于返回元素个数；
//max(Comparator<? super T> cp)：找出最大元素；
//min(Comparator<? super T> cp)：找出最小元素。


//针对IntStream、LongStream和DoubleStream，还额外提供了以下聚合方法：
//sum()：对所有元素求和；
//average()：对所有元素求平均数。


//还有一些方法，用来测试Stream的元素是否满足以下条件：
//boolean allMatch(Predicate<? super T>)：测试是否所有元素均满足测试条件；
//boolean anyMatch(Predicate<? super T>)：测试是否至少有一个元素满足测试条件。


//最后一个常用的方法是forEach()，它可以循环处理Stream的每个元素，我们经常传入System.out::println来打印Stream的元素：
//Stream<String> s = ...
//        s.forEach(str -> {
//        System.out.println("Hello, " + str);
//});












//小结
//Stream提供的常用操作有：
//转换操作：map()，filter()，sorted()，distinct()；
//合并操作：concat()，flatMap()；
//并行处理：parallel()；
//聚合操作：reduce()，collect()，count()，max()，min()，sum()，average()；
//其他操作：allMatch(), anyMatch(), forEach()。


































