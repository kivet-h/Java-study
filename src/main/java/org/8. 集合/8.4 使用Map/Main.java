/**
 * 8.4 使用Map
 */

//我们知道，List是一种顺序列表，如果有一个存储学生Student实例的List，要在List中根据name查找某个指定的Student的分数，应该怎么办？

//最简单的方法是遍历List并判断name是否相等，然后返回指定元素：
//List<Student> list = ...
//Student target = null;
//for (Student s : list) {
//        if ("Xiao Ming".equals(s.name)) {
//            target = s;
//            break;
//        }
//}
//System.out.println(target.score);

//这种需求其实非常常见，即通过一个键去查询对应的值。使用List来实现存在效率非常低的问题，因为平均需要扫描一半的元素才能确定，
// 而Map这种键值（key-value）映射表的数据结构，作用就是能高效通过key快速查找value（元素）。

//用Map来实现根据name查询某个Student的代码如下：
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//    public static void main(String[] args) {
//        Student s = new Student("Xiao Ming", 99);
//        Map<String, Student> map = new HashMap<>();
//        map.put("Xiao Ming", s);
//        Student target = map.get("Xiao Ming");
//        System.out.println(target == s); // true，同一个实例
//        System.out.println(target.score);// 99
//
//        Student target2 = map.get("Bob");
//        System.out.println(target2); // null
//    }
//}
//
//class Student {
//    public String name;
//    public int score;
//    public Student(String name, int score) {
//        this.name = name;
//        this.score = score;
//    }
//}
//$ java Main.java
//true
//99
//null

//通过上述代码可知：Map<K, V>是一种键-值映射表，
// 当我们调用put(K key, V value)方法时，就把key和value做了映射并放入Map。
// 当我们调用V get(K key)时，就可以通过key获取到对应的value。
// 如果key不存在，则返回null。
// 和List类似，Map也是一个接口，最常用的实现类是HashMap。

//如果只是想查询某个key是否存在，可以调用boolean containsKey(K key)方法。
//import java.util.Map;
//import java.util.HashMap;
//public class Main {
//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("hong", "hong");
//        String hong = map.get("hong");
//        System.out.println(hong); // hong
//
//        System.out.println(map.containsKey("hong")); // true
//        System.out.println(map.containsKey("lan")); // false
//    }
//}
//$ java Main.java
//hong
//true
//false



//如果我们在存储Map映射关系的时候，对同一个key调用两次put()方法，分别放入不同的value，会有什么问题呢？例如：
//import java.util.HashMap;
//import java.util.Map;
//public class Main {
//    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("apple", 123);
//        map.put("pear", 456);
//        System.out.println(map.get("apple")); // 123
//        map.put("apple", 789); // 再次放入apple作为key，但value变为789
//        System.out.println(map.get("apple")); // 789
//    }
//}

//重复放入key-value并不会有任何问题，但是一个key只能关联一个value。
// 在上面的代码中，一开始我们把key对象"apple"映射到Integer对象123，
// 然后再次调用put()方法把"apple"映射到789，
// 这时，原来关联的value对象123就被“冲掉”了。
// 实际上，put()方法的签名是V put(K key, V value)，如果放入的key已经存在，put()方法会返回被删除的旧的value，否则，返回null。



//始终牢记:
//Map中不存在重复的key，因为放入相同的key，只会把原有的key-value对应的value给替换掉。



//此外，在一个Map中，虽然key不能重复，但value是可以重复的：
//Map<String, Integer> map = new HashMap<>();
//map.put("apple", 123);
//map.put("pear", 123); // ok




















// =================================================== 遍历Map ===================================================
//对Map来说，要遍历key可以使用for each循环遍历Map实例的keySet()方法返回的Set集合，它包含不重复的key的集合：
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("apple", 123);
//        map.put("pear", 456);
//        map.put("banana", 789);
//        System.out.println(map.keySet()); // [banana, apple, pear]
//        for (String key : map.keySet()) {
//            Integer value = map.get(key);
//            System.out.println(key + " = " + value);
//        }
//    }
//}
//$ java Main.java
//[banana, apple, pear]
//banana = 789
//apple = 123
//pear = 456





//同时遍历key和value可以使用for each循环遍历Map对象的entrySet()集合，它包含每一个key-value映射：
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("apple", 123);
//        map.put("pear", 456);
//        map.put("banana", 789);
//
//        System.out.println(map.entrySet()); // [banana=789, apple=123, pear=456]
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry);
//
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println(key + " = " + value);
//        }
//    }
//}
//$ java Main.java
//[banana=789, apple=123, pear=456]
//banana=789
//banana = 789
//apple=123
//apple = 123
//pear=456
//pear = 456





//Map和List不同的是，Map存储的是key-value的映射关系，并且，它不保证顺序。
// 在遍历的时候，遍历的顺序既不一定是put()时放入的key的顺序，也不一定是key的排序顺序。
// 使用Map时，任何依赖顺序的逻辑都是不可靠的。
// 以HashMap为例，假设我们放入"A"，"B"，"C"这3个key，遍历的时候，每个key会保证被遍历一次且仅遍历一次，但顺序完全没有保证，甚至对于不同的JDK版本，相同的代码遍历的输出顺序都是不同的！


//注意
//遍历Map时，不可假设输出的key是有序的！














//小结
//Map是一种映射表，可以通过key快速查找value；
//
//可以通过for each遍历keySet()，也可以通过for each遍历entrySet()，直接获取key-value；
//
//最常用的一种Map实现是HashMap。








