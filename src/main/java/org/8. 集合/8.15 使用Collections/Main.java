/**
 * 8.15 使用Collections
 */

//Collections是JDK提供的工具类，同样位于java.util包中。它提供了一系列静态方法，能更方便地操作各种集合。

//注意
//Collections结尾多了一个s，不是Collection！

//我们一般看方法名和参数就可以确认Collections提供的该方法的功能。例如，对于以下静态方法：
//public static boolean addAll(Collection<? super T> c, T... elements) { ... }

//addAll()方法可以给一个Collection类型的集合添加若干元素。因为方法签名是Collection，所以我们可以传入List，Set等各种集合类型。














// ====================================================== 创建空集合 ======================================================
//对于旧版的JDK，可以使用Collections提供的一系列方法来创建空集合：
// - 创建空List：List<T> emptyList()
// - 创建空Map：Map<K, V> emptyMap()
// - 创建空Set：Set<T> emptySet()

//要注意到返回的空集合是不可变集合，无法向其中添加或删除元素。

//新版的JDK≥9可以直接使用List.of()、Map.of()、Set.of()来创建空集合。






















// ====================================================== 创建单元素集合 ======================================================
//对于旧版的JDK，Collections提供了一系列方法来创建一个单元素集合：
// - 创建一个元素的List：List<T> singletonList(T o)
// - 创建一个元素的Map：Map<K, V> singletonMap(K key, V value)
// - 创建一个元素的Set：Set<T> singleton(T o)

//要注意到返回的单元素集合也是不可变集合，无法向其中添加或删除元素。

//新版的JDK≥9可以直接使用List.of(T...)、Map.of(T...)、Set.of(T...)来创建任意个元素的集合。























// ====================================================== 排序 ======================================================
//Collections可以对List进行排序。因为排序会直接修改List元素的位置，因此必须传入可变List：
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("apple");
//        list.add("pear");
//        list.add("orange");
//        // 排序前:
//        System.out.println(list); // [apple, pear, orange]
//        Collections.sort(list);
//        // 排序后:
//        System.out.println(list); // [apple, orange, pear]
//    }
//}
//$ java Main.java
//[apple, pear, orange]
//[apple, orange, pear]





















// ====================================================== 洗牌 ======================================================
//Collections提供了洗牌算法，即传入一个有序的List，可以随机打乱List内部元素的顺序，效果相当于让计算机洗牌：
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//            list.add(i);
//        }
//        // 洗牌前:
//        System.out.println(list); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//        // 洗牌:
//        Collections.shuffle(list);
//        // 洗牌后:
//        System.out.println(list); // [7, 9, 3, 5, 6, 0, 8, 2, 1, 4]
//    }
//}
//$ java Main.java
//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//[7, 9, 3, 5, 6, 0, 8, 2, 1, 4]



















// ====================================================== 不可变集合 ======================================================
//Collections还提供了一组方法把可变集合封装成不可变集合：
// - 封装成不可变List：List<T> unmodifiableList(List<? extends T> list)
// - 封装成不可变Set：Set<T> unmodifiableSet(Set<? extends T> set)
// - 封装成不可变Map：Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m)

//这种封装实际上是通过创建一个代理对象，拦截掉所有修改方法实现的。我们来看看效果：
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> mutable = new ArrayList<>();
//        mutable.add("apple");
//        mutable.add("pear");
//        // 变为不可变集合:
//        List<String> immutable = Collections.unmodifiableList(mutable);
//        immutable.add("orange"); // UnsupportedOperationException!
//    }
//}
//$ java Main.java
//Exception in thread "main" java.lang.UnsupportedOperationException
//    at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1095)
//    at Main.main(Main.java:188)



//然而，继续对原始的可变List进行增删是可以的，并且，会直接影响到封装后的“不可变”List：
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> mutable = new ArrayList<>();
//        mutable.add("apple");
//        mutable.add("pear");
//        // 变为不可变集合:
//        List<String> immutable = Collections.unmodifiableList(mutable);
//        mutable.add("orange");
//        System.out.println(mutable); // [apple, pear, orange]
//        System.out.println(immutable); // [apple, pear, orange]
//    }
//}
//$ java Main.java
//[apple, pear, orange]
//[apple, pear, orange]



//因此，如果我们希望把一个可变List封装成不可变List，那么，返回不可变List后，最好立刻扔掉可变List的引用，这样可以保证后续操作不会意外改变原始对象，从而造成“不可变”List变化了：
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        List<String> mutable = new ArrayList<>();
//        mutable.add("apple");
//        mutable.add("pear");
//        // 变为不可变集合:
//        List<String> immutable = Collections.unmodifiableList(mutable);
//        // 立刻扔掉mutable的引用:
//        mutable = null;
//        System.out.println(immutable); // [apple, pear]
//    }
//}
//$ java Main.java
//[apple, pear]


















// ====================================================== 线程安全集合 ======================================================
//Collections还提供了一组方法，可以把线程不安全的集合变为线程安全的集合：
// - 变为线程安全的List：List<T> synchronizedList(List<T> list)
// - 变为线程安全的Set：Set<T> synchronizedSet(Set<T> s)
// - 变为线程安全的Map：Map<K,V> synchronizedMap(Map<K,V> m)

//多线程的概念我们会在后面讲。因为从Java 5开始，引入了更高效的并发集合类，所以上述这几个同步方法已经没有什么用了。















//小结
//Collections类提供了一组工具方法来方便使用集合类：
// - 创建空集合；
// - 创建单元素集合；
// - 创建不可变集合；
// - 排序／洗牌等操作。





