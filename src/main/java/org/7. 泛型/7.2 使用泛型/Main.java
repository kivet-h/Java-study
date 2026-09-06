/**
 * 7.2 使用泛型
 */


//使用ArrayList时，如果不定义泛型类型时，泛型类型实际上就是Object：
// 编译器警告:
//List list = new ArrayList();
//list.add("Hello");
//list.add("World");
//String first = (String) list.get(0);
//String second = (String) list.get(1);

//此时，只能把<T>当作Object使用，没有发挥泛型的优势。




//当我们定义泛型类型<String>后，List<T>的泛型接口变为强类型List<String>：
//// 无编译器警告:
//List<String> list = new ArrayList<String>();
//list.add("Hello");
//list.add("World");
//// 无强制转型:
//String first = list.get(0);
//String second = list.get(1);


//当我们定义泛型类型<Number>后，List<T>的泛型接口变为强类型List<Number>：
//List<Number> list = new ArrayList<Number>();
//list.add(new Integer(123));
//list.add(new Double(12.34));
//Number first = list.get(0);
//Number second = list.get(1);


//编译器如果能自动推断出泛型类型，就可以省略后面的泛型类型。例如，对于下面的代码：
//List<Number> list = new ArrayList<Number>();

//编译器看到泛型类型List<Number>就可以自动推断出后面的ArrayList<T>的泛型类型必须是ArrayList<Number>，因此，可以把代码简写为：
// 可以省略后面的Number，编译器可以自动推断泛型类型：
//List<Number> list = new ArrayList<>();















// ========================================================== 泛型接口 ==========================================================
//除了ArrayList<T>使用了泛型，还可以在接口中使用泛型。
// 例如，Arrays.sort(Object[])可以对任意数组进行排序，但待排序的元素必须实现Comparable<T>这个泛型接口：
//public interface Comparable<T> {
//    /**
//     * 返回负数: 当前实例比参数o小
//     * 返回0: 当前实例与参数o相等
//     * 返回正数: 当前实例比参数o大
//     */
//    int compareTo(T o);
//}





//可以直接对String数组进行排序：
// sort
//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) {
//        String[] ss = new String[] { "Orange", "Apple", "Pear" };
//        Arrays.sort(ss);
//        System.out.println(Arrays.toString(ss));
//    }
//}
//$ java Main.java
//[Apple, Orange, Pear]


//这是因为String本身已经实现了Comparable<String>接口。




// 如果换成我们自定义的Person类型试试：
// sort
//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) {
//        Person[] ps = new Person[] {
//                new Person("Bob", 61),
//                new Person("Alice", 88),
//                new Person("Lily", 75),
//        };
//        Arrays.sort(ps);
//        System.out.println(Arrays.toString(ps));
//    }
//}
//
//class Person {
//    String name;
//    int score;
//    Person(String name, int score) {
//        this.name = name;
//        this.score = score;
//    }
//    public String toString() {
//        return this.name + "," + this.score;
//    }
//}
//$ java Main.java
//Exception in thread "main" java.lang.ClassCastException: class Person cannot be cast to class java.lang.Comparable (Person is in unnamed module of loader com.sun.tools.javac.launcher.MemoryClassLoader @6ee12bac; java.lang.Comparable is in module java.base of loader 'bootstrap')
//    at java.base/java.util.ComparableTimSort.countRunAndMakeAscending(ComparableTimSort.java:320)
//    at java.base/java.util.ComparableTimSort.sort(ComparableTimSort.java:188)
//    at java.base/java.util.Arrays.sort(Arrays.java:1038)
//    at Main.main(Main.java:105)

//运行程序，我们会得到ClassCastException，即无法将Person转型为Comparable。





// 我们修改代码，让Person实现Comparable<T>接口：
//import java.util.Arrays;
//
//public class Main {
//    public static void main(String[] args) {
//        Person[] ps = new Person[] {
//                new Person("Bob", 61),
//                new Person("Alice", 88),
//                new Person("Lily", 75),
//        };
//        Arrays.sort(ps);
//        System.out.println(Arrays.toString(ps));
//    }
//}
//
//class Person implements Comparable<Person> {
//    String name;
//    int score;
//
//    Person(String name, int score) {
//        this.name = name;
//        this.score = score;
//    }
//
//    public int compareTo(Person other) {
//        return this.name.compareTo(other.name);
//    }
//
//    public String toString() {
//        return this.name + "," + this.score;
//    }
//}
//$ java Main.java
//[Alice,88, Bob,61, Lily,75]









//小结
//使用泛型时，把泛型参数<T>替换为需要的class类型，例如：ArrayList<String>，ArrayList<Number>等；
//可以省略编译器能自动推断出的类型，例如：List<String> list = new ArrayList<>();；
//不指定泛型参数类型时，编译器会给出警告，且只能将<T>视为Object类型；
//可以在接口中定义泛型类型，实现此接口的类必须实现正确的泛型类型。
