/**
 * 8.9 使用Set
 */

// 我们知道， Map 用于存储key-value的映射，对于充当key的对象，是不能重复的，并且，不但需要正确覆写 equals() 方法，还要正确覆写 hashCode() 方法。

// 如果我们只需要存储不重复的key，并不需要存储映射的value，那么就可以使用 Set 。

// Set 用于存储不重复的元素集合，它主要提供以下几个方法：
//  - 将元素添加进 Set<E> ： boolean add(E e)
//  - 将元素从 Set<E> 删除： boolean remove(Object e)
//  - 判断是否包含元素： boolean contains(Object e)

// 我们来看几个简单的例子：
// import java.util.Set;
// import java.util.HashSet;
//
// public class Main {
//     public static void main(String[] args) {
//         Set<String> set = new HashSet<>();
//         System.out.println(set.add("abc")); // true System.out.println(set.add("xyz")); // true
//         System.out.println(set.add("xyz")); // false，添加失败，因为元素已存在
//         System.out.println(set.contains("xyz")); // true，元素存在
//         System.out.println(set.contains("XYZ")); // false，元素不存在
//         System.out.println(set.remove("hello")); // false，删除失败，因为元素不存在
//         System.out.println(set.size()); // 2，一共两个元素
//     }
// }
// $ java Main.java
// true
// true
// true
// false
// false
// 2




// Set 实际上相当于只存储key、不存储value的 Map 。我们经常用 Set 用于去除重复元素。

// 因为放入 Set 的元素和 Map 的key类似，都要正确实现 equals() 和 hashCode() 方法，否则该元素无法正确地放入 Set 。

// 最常用的 Set 实现类是 HashSet ，实际上， HashSet 仅仅是对 HashMap 的一个简单封装，它的核心代码如下：
// public class HashSet<E> implements Set<E> {
//     // HashSet 内部持有一个 HashMap 实例。
//     // 这个 HashMap 的键（Key）是集合中存储的元素（E 类型），值（Value）是一个固定对象 PRESENT。
//     // 这是 HashSet 的实现原理：利用 HashMap 的键不允许重复的特性来实现元素不重复的集合。
//     private HashMap<E, Object> map = new HashMap<>();
//
//     // 定义一个静态常量对象 PRESENT，作为所有键对应的值。
//     // 这个对象没有实际意义，只是为了满足 HashMap 的键值对结构要求。
//     // 所有添加进 HashSet 的元素都会映射到这个对象。
//     private static final Object PRESENT = new Object();
//
//     // 调用 map.put(e, PRESENT) 向 HashMap 中添加键值对。
//     // 如果 e 之前不存在于 HashMap 中，put 方法返回 null，说明添加成功。
//     // 如果 e 已经存在，put 返回旧值（即 PRESENT），说明添加失败（重复元素）。
//     // 所以这个方法返回 true 表示成功添加新元素，false 表示元素已存在。
//     public boolean add(E e) {
//         return map.put(e, PRESENT) == null;
//     }
//
//     // 判断 HashSet 是否包含某个元素 o。
//     // 实际上调用的是 HashMap 的 containsKey 方法。
//     // 因为 HashSet 的元素就是 HashMap 的键。
//     public boolean contains(Object o) {
//         return map.containsKey(o);
//     }
//
//     // 调用 map.remove(o) 删除键为 o 的条目。
//     // 如果 o 存在，remove 返回对应的值（即 PRESENT），说明删除成功。
//     // 如果 o 不存在，返回 null，说明删除失败。
//     // 所以返回 true 表示成功删除，false 表示元素不存在。
//     public boolean remove(Object o) {
//         return map.remove(o) == PRESENT;
//     }
// }




// Set 接口并不保证有序，而 SortedSet 接口则保证元素是有序的：
// - HashSet 是无序的，因为它实现了 Set 接口，并没有实现 SortedSet 接口；
// - TreeSet 是有序的，因为它实现了 SortedSet 接口。

// 用一张图表示：
//         ┌───┐
//         │Set│
//         └───┘
//           ▲
//      ┌────┴─────┐
//      │          │
//  ┌───────┐ ┌─────────┐
//  │HashSet│ │SortedSet│
//  └───────┘ └─────────┘
//                 ▲
//                 │
//             ┌─────────┐
//             │ TreeSet │
//             └─────────┘







// 我们来看 HashSet 的输出：
// import java.util.HashSet;
// import java.util.Set;
//
// public class Main {
//     public static void main(String[] args) {
//         Set<String> set = new HashSet<>();
//         set.add("apple");
//         set.add("banana");
//         set.add("pear");
//         set.add("orange");
//         System.out.println(set);
//
//         for (String s : set) {
//             System.out.println(s);
//         }
//     }
// }
// $ java Main.java
// [banana, orange, apple, pear]
// banana
// orange
// apple
// pear

// 注意输出的顺序既不是添加的顺序，也不是 String 排序的顺序，在不同版本的JDK中，这个顺序也可能是不同的。

// 把 HashSet 换成 TreeSet ，在遍历 TreeSet 时，输出就是有序的，这个顺序是元素的排序顺序：
// import java.util.TreeSet;
// import java.util.Set;
//
// public class Main {
//     public static void main(String[] args) {
//         Set<String> set = new TreeSet<>();
//         set.add("apple");
//         set.add("banana");
//         set.add("pear");
//         set.add("orange");
//         System.out.println(set);
//
//         for (String s : set) {
//             System.out.println(s);
//         }
//     }
// }
// $ java Main.java
// [apple, banana, orange, pear]
// apple
// banana
// orange
// pear

// 使用 TreeSet 和使用 TreeMap 的要求一样，添加的元素必须正确实现 Comparable 接口，
// 如果没有实现 Comparable 接口，那么创建 TreeSet 时必须传入一个 Comparator 对象。




//小结
//Set用于存储不重复的元素集合：
// - 放入HashSet的元素与作为HashMap的key要求相同；
// - 放入TreeSet的元素与作为TreeMap的Key要求相同。
//利用Set可以去除重复元素；
//遍历SortedSet按照元素的排序顺序遍历，也可以自定义排序算法。