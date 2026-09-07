/**
 * 3.2.7 记录类
 */


//使用String、Integer等类型的时候，这些类型都是不变类，一个不变类具有以下特点：
//1. 定义class时使用final，无法派生子类；
//2. 每个字段使用final，保证创建实例后无法修改任何字段。

//假设我们希望定义一个Point类，有x、y两个变量，同时它是一个不变类，可以这么写：
//public final class Point {
//    private final int x;
//    private final int y;
//
//    public Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public int x() {
//        return this.x;
//    }
//
//    public int y() {
//        return this.y;
//    }
//}

//为了保证不变类的比较，还需要正确覆写equals()和hashCode()方法，这样才能在集合类中正常使用。
// 后续我们会详细讲解正确覆写equals()和hashCode()，这里演示Point不变类的写法目的是，这些代码写起来都非常简单，但是很繁琐。




















// ============================================================= record =============================================================
// 从Java 14开始，引入了新的 Record 类。我们定义 Record 类时，使用关键字 record 。把上述Point 类改写为 Record 类，代码如下：
// Record
// public class Main {
//     public static void main(String[] args) {
//         Point p = new Point(123, 456);
//         System.out.println(p.x());
//         System.out.println(p.y());
//         System.out.println(p);
//     }
// }
// record Point(int x, int y) {};


// 仔细观察 Point 的定义：
// record Point(int x, int y) {};

// 把上述定义改写为class，相当于以下代码：
// final class Point extends Record {
//     private final int x;
//     private final int y;
//
//     public Point(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
//     public int x() {
//         return this.x;
//     }
//     public int y() {
//         return this.y;
//     }
//     public String toString() {
//         return String.format("Point[x=%s, y=%s]", x, y);
//     }
//     public boolean equals(Object o) {
//         // ...
//     }
//     public int hashCode() {
//         // ...
//     }
// }

// 除了用 final 修饰class以及每个字段外，编译器还自动为我们创建了构造方法，和字段名同名的方法，以及覆写 toString() 、 equals() 和 hashCode() 方法。
// 换句话说，使用 record 关键字，可以一行写出一个不变类。
// 和 enum 类似，我们自己不能直接从 Record 派生，只能通过 record 关键字由编译器实现继承。

// record 是 Java 在语言层面为简化数据类设计提供的一个强大特性，适用于大多数只用于存储数据的类。它提升了代码的可读性和可维护性，同时减少了样板代码的编写。


// 代码示例：
// public class Main {
//     public static void main(String[] args) {
//         // 创建 record 实例
//         User user1 = new User("Alice", 25);
//         User user2 = new User("Bob", 17);
//
//         // 使用 getter 方法
//         System.out.println("姓名: " + user1.name()); // 注意不是 getName()
//         System.out.println("年龄: " + user1.age());
//
//         // 使用自定义方法
//         System.out.println("Alice 是成年人吗? " + user1.isAdult()); // true
//         System.out.println("Bob 是成年人吗? " + user2.isAdult());   // false
//
//         // 自动生成的 toString()
//         System.out.println("user1 的字符串表示: " + user1);
//
//         // 自动生成的 equals 和 hashCode
//         User user3 = new User("Alice", 25);
//         System.out.println("user1 和 user3 相等吗? " + user1.equals(user3)); // true
//     }
// }
//
// // 定义一个 record
// public record User(String name, int age) {
//
//     // 可以添加自定义方法
//     public boolean isAdult() {
//         return age >= 18;
//     }
//
//     // 可以添加自定义构造逻辑（称为 compact constructor）
//     public User {
//         if (age < 0) {
//             throw new IllegalArgumentException("年龄不能为负数");
//         }
//     }
// }























// ============================================================= 构造方法 =============================================================
// 编译器默认按照 record 声明的变量顺序自动创建一个构造方法，并在方法内给字段赋值。
// 那么问题来了，如果我们要检查参数，应该怎么办？

// 假设 Point 类的 x 、 y 不允许负数，我们就得给 Point 的构造方法加上检查逻辑：
// public record Point(int x, int y) {
//     public Point {
//         if (x < 0 || y < 0) {
//             throw new IllegalArgumentException();
//         }
//     }
// }

// 注意到方法 public Point {...} 被称为Compact Constructor，它的目的是让我们编写检查逻辑，编译器最终生成的构造方法如下：
// public final class Point extends Record {
//     public Point(int x, int y) {
//         // 这是我们编写的Compact Constructor:
//         if (x < 0 || y < 0) {
//             throw new IllegalArgumentException();
//         }
//         // 这是编译器继续生成的赋值代码:
//         this.x = x;
//         this.y = y;
//     }
//     // ...
// }


// 作为 record 的 Point 仍然可以添加静态方法。
// 一种常用的静态方法是 of() 方法，用来创建 Point ：
// public record Point(int x, int y) {
//     public static Point of() {
//         return new Point(0, 0);
//     }
//     public static Point of(int x, int y) {
//         return new Point(x, y);
//     }
// }

// 这样我们可以写出更简洁的代码：
// var z = Point.of();
// var p = Point.of(123, 456);





// 小结:
// 从Java 14开始，提供新的 record 关键字，可以非常方便地定义Data Class：
// 使用 record 定义的是不变类；
// 可以编写Compact Constructor对参数进行验证；
// 可以定义静态方法。











