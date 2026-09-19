/**
 * 19.3.5 输出集合
 */

//我们介绍了Stream的几个常见操作：map()、filter()、reduce()。这些操作对Stream来说可以分为两类，
// 一类是转换操作，即把一个Stream转换为另一个Stream，例如map()和filter()，
// 另一类是聚合操作，即对Stream的每个元素进行计算，得到一个确定的结果，例如reduce()。

//区分这两种操作是非常重要的，因为对于Stream来说，对其进行转换操作并不会触发任何计算！我们可以做个实验：
//import java.util.function.Supplier;
//import java.util.stream.Stream;
//
//public class Main {
//    public static void main(String[] args)     {
//        Stream<Long> s1 = Stream.generate(new NatualSupplier());
//        Stream<Long> s2 = s1.map(n -> n * n);
//        Stream<Long> s3 = s2.map(n -> n - 1);
//        System.out.println(s3); // java.util.stream.ReferencePipeline$3@49476842
//    }
//}
//
//class NatualSupplier implements Supplier<Long> {
//    long n = 0;
//    public Long get() {
//        n++;
//        return n;
//    }
//}
//因为s1是一个Long类型的序列，它的元素高达922亿亿个，但执行上述代码，既不会有任何内存增长，也不会有任何计算，
// 因为转换操作只是保存了转换规则，无论我们对一个Stream转换多少次，都不会有任何实际计算发生。

//而聚合操作则不一样，聚合操作会立刻促使Stream输出它的每一个元素，并依次纳入计算，以获得最终结果。
// 所以，对一个Stream进行聚合操作，会触发一系列连锁反应：
//Stream<Long> s1 = Stream.generate(new NatualSupplier());
//Stream<Long> s2 = s1.map(n -> n * n);
//Stream<Long> s3 = s2.map(n -> n - 1);
//Stream<Long> s4 = s3.limit(10);
//s4.reduce(0, (acc, n) -> acc + n);

//我们对s4进行reduce()聚合计算，会不断请求s4输出它的每一个元素。
// 因为s4的上游是s3，它又会向s3请求元素，导致s3向s2请求元素，s2向s1请求元素，最终，s1从Supplier实例中请求到真正的元素，并经过一系列转换，最终被reduce()聚合出结果。

//可见，聚合操作是真正需要从Stream请求数据的，对一个Stream做聚合计算后，结果就不是一个Stream，而是一个其他的Java对象。




















// ================================================= 输出为List =================================================
//reduce()只是一种聚合操作，如果我们希望把Stream的元素保存到集合，
// 例如List，因为List的元素是确定的Java对象，因此，把Stream变为List不是一个转换操作，而是一个聚合操作，它会强制Stream输出每个元素。

//下面的代码演示了如何将一组String先过滤掉空字符串，然后把非空字符串保存到List中：
//import java.util.List;
//import java.util.stream.Stream;
//import java.util.stream.Collectors;
//public class Main {
//    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("Apple", "", null, "Pear", "  ", "Orange");
//        List<String> list = stream.filter(s -> s != null && !s.isBlank()).collect(Collectors.toList());
//        System.out.println(list); // [Apple, Pear, Orange]
//    }
//}
//$ java Main.java
//[Apple, Pear, Orange]

//把Stream的每个元素收集到List的方法是调用collect()并传入Collectors.toList()对象，它实际上是一个Collector实例，通过类似reduce()的操作，把每个元素添加到一个收集器中（实际上是ArrayList）。

//类似的，collect(Collectors.toSet())可以把Stream的每个元素收集到Set中。




















// ================================================= 输出为数组 =================================================
//把Stream的元素输出为数组和输出为List类似，我们只需要调用toArray()方法，并传入数组的“构造方法”：
//import java.util.List;
//import java.util.stream.Stream;
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        List<String> list = List.of("Apple", "Pear", "Orange");
//        String[] array = list.stream().toArray(String[]::new);
//        System.out.println(Arrays.toString(array)); // [Apple, Pear, Orange]
//    }
//}
//$ java Main.java
//[Apple, Pear, Orange]





















// ================================================= 输出为Map =================================================
//如果我们要把Stream的元素收集到Map中，就稍微麻烦一点。因为对于每个元素，添加到Map时需要key和value，因此，我们要指定两个映射函数，分别把元素映射为key和value：




//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//public class Main {
//    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("APPL:Apple", "MSFT:Microsoft");
//        Map<String, String> map = stream.collect(Collectors.toMap(
//                s -> s.split(":")[0],
//                s -> s.split(":")[1]
//        ));
//        System.out.println(map); // {APPL=Apple, MSFT=Microsoft}
//    }
//}
//$ java Main.java
//{MSFT=Microsoft, APPL=Apple}


//亦或者是：
//import java.util.*;
//import java.util.stream.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("APPL:Apple", "MSFT:Microsoft");
//        Map<String, String> map = stream
//                .collect(Collectors.toMap(
//                        // 把元素s映射为key:
//                        s -> s.substring(0, s.indexOf(':')),
//                        // 把元素s映射为value:
//                        s -> s.substring(s.indexOf(':') + 1)));
//        System.out.println(map);
//    }
//}
//$ java Main.java
//{MSFT=Microsoft, APPL=Apple}
























// ================================================= 分组输出 =================================================
//Stream还有一个强大的分组功能，可以按组输出。我们看下面的例子：
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//import java.util.stream.Collectors;
//public class Main {
//    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
//        Map<String, List<String>> map = stream.collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
//        System.out.println(map);
//    }
//}
//$ java Main.java
//{A=[Apple, Avocado, Apricots], B=[Banana, Blackberry], C=[Coconut, Cherry]}


//import java.util.*;
//import java.util.stream.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> list = List.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
//        Map<String, List<String>> groups = list.stream()
//                .collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
//        System.out.println(groups);
//    }
//}
//$ java Main.java
//{A=[Apple, Avocado, Apricots], B=[Banana, Blackberry], C=[Coconut, Cherry]}

//可见，结果一共有3组，按"A"，"B"，"C"分组，每一组都是一个List。



//假设有这样一个Student类，包含学生姓名、班级和成绩：
//class Student {
//    int gradeId; // 年级
//    int classId; // 班级
//    String name; // 名字
//    int score; // 分数
//}
//如果我们有一个Stream<Student>，利用分组输出，可以非常简单地按年级或班级把Student归类。












//小结
//Stream可以输出为集合：
//
//Stream通过collect()方法可以方便地输出为List、Set、Map，还可以分组输出。