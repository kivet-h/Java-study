/**
 * 3.1.4 继承
 */

//在前面的章节中，我们已经定义了Person类：
//class Person {
//    private String name;
//    private int age;
//
//    public String getName() {...}
//    public void setName(String name) {...}
//    public int getAge() {...}
//    public void setAge(int age) {...}
//}

//现在，假设需要定义一个Student类，字段如下：
//class Student {
//    private String name;
//    private int age;
//    private int score;
//
//    public String getName() {...}
//    public void setName(String name) {...}
//    public int getAge() {...}
//    public void setAge(int age) {...}
//    public int getScore() { … }
//    public void setScore(int score) { … }
//}

//仔细观察，发现Student类包含了Person类已有的字段和方法，只是多出了一个score字段和相应的getScore()、setScore()方法。

//能不能在Student中不要写重复的代码？
//这个时候，继承就派上用场了。

//继承是面向对象编程中非常强大的一种机制，它首先可以复用代码。当我们让Student从Person继承时，Student就获得了Person的所有功能，我们只需要为Student编写新增的功能。

//Java使用extends关键字来实现继承：

//class Person {
//    private String name;
//    private int age;
//
//    public String getName() {...}
//    public void setName(String name) {...}
//    public int getAge() {...}
//    public void setAge(int age) {...}
//}
//
//class Student extends Person {
//    // 不要重复name和age字段/方法,
//    // 只需要定义新增score字段/方法:
//    private int score;
//
//    public int getScore() { … }
//    public void setScore(int score) { … }
//}

//可见，通过继承，Student只需要编写额外的功能，不再需要重复代码。




//注意!!: 子类自动获得了父类的所有字段，严禁定义与父类重名的字段！


//在OOP的术语中，
// 我们把Person称为超类（super class），父类（parent class），基类（base class），
// 把Student称为子类（subclass），扩展类（extended class）。

















// ================================================================== 继承树 ==================================================================
//注意到我们在定义Person的时候，没有写extends。在Java中，没有明确写extends的类，编译器会自动加上extends Object。
// 所以，任何类，除了Object，都会继承自某个类。下图是Person、Student的继承树：

//    ┌───────────┐
//    │  Object   │
//    └───────────┘
//          ▲
//          │
//    ┌───────────┐
//    │  Person   │
//    └───────────┘
//          ▲
//          │
//    ┌───────────┐
//    │  Student  │
//    └───────────┘

//Java只允许一个class继承自一个类，因此，一个类有且仅有一个父类。只有Object特殊，它没有父类。

//类似的，如果我们定义一个继承自Person的Teacher，它们的继承树关系如下：
//        ┌───────────┐
//        │  Object   │
//        └───────────┘
//              ▲
//              │
//        ┌───────────┐
//        │  Person   │
//        └───────────┘
//           ▲     ▲
//           │     │
//           │     │
// ┌───────────┐ ┌───────────┐
// │  Student  │ │  Teacher  │
// └───────────┘ └───────────┘























// ================================================================== protected ==================================================================
//继承有个特点，就是子类无法访问父类的private字段或者private方法。例如，Student类就无法访问Person类的name和age字段：


//public class Main {
//    public static void main(String[] args) {
//        Student hong = new Student();
//    }
//}
//
//class Person {
//    private String name;
//    private int age;
//}
//
//class Student extends Person {
//    public String getName() {
//        return name; // 错误: name 在 Person 中是 private 访问控制
//    }
//}
//$ java Main.java
//Main.java:161: 错误: name 在 Person 中是 private 访问控制
//        return name; // 错误: name 在 Person 中是 private 访问控制
//               ^
//                       1 个错误
//错误: 编译失败


//这使得继承的作用被削弱了。为了让子类可以访问父类的字段，我们需要把private改为protected。用protected修饰的字段可以被子类访问：

//public class Main {
//    public static void main(String[] args) {
//        Student hong = new Student();
//        System.out.println(hong.getName());
//    }
//}
//
//class Person {
//    protected String name = "xiao hong";
//}
//
//class Student extends Person {
//    public String getName() {
//        return name;
//    }
//}
//$ java Main.java
//xiao hong


//因此，protected关键字可以把字段和方法的访问权限控制在继承树内部，一个protected字段和方法可以被其子类，以及子类的子类所访问，后面我们还会详细讲解。




















// ================================================================== super ==================================================================
//super关键字表示父类（超类）。子类引用父类的字段时，可以用super.fieldName。例如：
//public class Main {
//    public static void main(String[] args) {
//        Student hong = new Student();
//        hong.hello();
//    }
//}
//
//class Person {
//    protected String name = "xiao hong";
//}
//
//class Student extends Person {
//    public void hello() {
//        System.out.println("Hello, " + super.name);
//        System.out.println("Hello, " + this.name);
//        System.out.println("Hello, " + name);
//    }
//}
//$ java Main.java
//Hello, xiao hong
//Hello, xiao hong
//Hello, xiao hong

//实际上，这里使用super.name，或者this.name，或者name，效果都是一样的。编译器会自动定位到父类的name字段。



//但是，在某些时候，就必须使用super。我们来看一个例子：
//public class Main {
//    public static void main(String[] args) {
//        Student s = new Student("xiao hong", 20, 90);
//    }
//}
//
//class Person {
//    protected String name;
//    protected int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//}
//
//class Student extends Person {
//    protected  int score;
//
//    public Student(String name, int age, int score) {
//        this.score = score;
//    }
//}
//$ java Main.java
//Main.java:264: 错误: 无法将类 Person中的构造器 Person应用到给定类型;
//public Student(String name, int age, int score) {
//                                                    ^
//    需要: String,int
//    找到:    没有参数
//    原因: 实际参数列表和形式参数列表长度不同
//    1 个错误
//    错误: 编译失败


//运行上面的代码，会得到一个编译错误，大意是在Student的构造方法中，无法调用Person的构造方法。

//这是因为在Java中，任何class的构造方法，第一行语句必须是调用父类的构造方法。
// 如果没有明确地调用父类的构造方法，编译器会帮我们自动加一句super();，
// 所以，Student类的构造方法实际上是这样：
//public class Main {
//    public static void main(String[] args) {
//        Student s = new Student("xiao hong", 20, 90);
//    }
//}
//
//class Person {
//    protected String name;
//    protected int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//}
//
//class Student extends Person {
//    protected  int score;
//
//    public Student(String name, int age, int score) {
//        super(); // 自动调用父类的构造方法
//        this.score = score;
//    }
//}
//$ java Main.java
//Main.java:304: 错误: 无法将类 Person中的构造器 Person应用到给定类型;
//        super(); // 自动调用父类的构造方法
//                ^
//需要: String,int
//找到:    没有参数
//原因: 实际参数列表和形式参数列表长度不同
//1 个错误
//错误: 编译失败


//但是，Person类并没有无参数的构造方法，因此，编译失败。


//解决方法是调用Person类存在的某个构造方法。例如：
//public class Main {
//    public static void main(String[] args) {
//        Student s = new Student("xiao hong", 20, 90);
//    }
//}
//
//class Person {
//    protected String name;
//    protected int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//}
//
//class Student extends Person {
//    protected  int score;
//
//    public Student(String name, int age, int score) {
//        super(name, age);
//        this.score = score;
//    }
//}
// $ java Main.java
// 运行通过，无报错



//这样就可以正常编译了！

//因此我们得出结论：如果父类没有默认的构造方法，子类就必须显式调用super()并给出参数以便让编译器定位到父类的一个合适的构造方法。

//这里还顺带引出了另一个问题：即子类不会继承任何父类的构造方法。子类默认的构造方法是编译器自动生成的，不是继承的。
























// ================================================================== 阻止继承 ==================================================================
//正常情况下，只要某个class没有final修饰符，那么任何类都可以从该class继承。

//从Java 15开始，允许使用sealed修饰class，并通过permits明确写出能够从该class继承的子类名称。
//例如，定义一个Shape类：
//public sealed class Shape permits Rect, Circle, Triangle {
//    ...
//}
//上述Shape类就是一个sealed类，它只允许指定的3个类继承它。

// 如果写：
//public final class Rect extends Shape {...}
//是没问题的，因为Rect出现在Shape的permits列表中。

// 但是，如果定义一个Ellipse就会报错：
//public final class Ellipse extends Shape {...}
// Compile error: class is not allowed to extend sealed class: Shape

//原因是Ellipse并未出现在Shape的permits列表中。这种sealed类主要用于一些框架，防止继承被滥用。

//sealed类在Java 15中目前是预览状态，要启用它，必须使用参数--enable-preview和--source 15。
























// ================================================================== 向上转型 ==================================================================
//如果一个引用变量的类型是Student，那么它可以指向一个Student类型的实例：
//Student s = new Student();

//如果一个引用类型的变量是Person，那么它可以指向一个Person类型的实例：
//Person p = new Person();

//现在问题来了：如果Student是从Person继承下来的，那么，一个引用类型为Person的变量，能否指向Student类型的实例？
//Person p = new Student(); // ???

//测试一下就可以发现，这种指向是允许的！
//这是因为Student继承自Person，因此，它拥有Person的全部功能。Person类型的变量，如果指向Student类型的实例，对它进行操作，是没有问题的！

//这种把一个子类类型安全地变为父类类型的赋值，被称为向上转型（upcasting）。

//向上转型实际上是把一个子类型安全地变为更加抽象的父类型：
//Student s = new Student();
//Person p = s; // upcasting, ok
//Object o1 = p; // upcasting, ok
//Object o2 = s; // upcasting, ok

//注意到继承树是Student > Person > Object，所以，可以把Student类型转型为Person，或者更高层次的Object。
























// ================================================================== 向下转型 ==================================================================
//和向上转型相反，如果把一个父类类型强制转型为子类类型，就是向下转型（downcasting）。例如：
//Person p1 = new Student(); // upcasting, ok
//Person p2 = new Person();
//Student s1 = (Student) p1; // ok
//Student s2 = (Student) p2; // runtime error! ClassCastException!

//如果测试上面的代码，可以发现：
// Person类型p1实际指向Student实例，
// Person类型变量p2实际指向Person实例。
// 在向下转型的时候，把p1转型为Student会成功，因为p1确实指向Student实例，
// 把p2转型为Student会失败，因为p2的实际类型是Person，
// 不能把父类变为子类，因为子类功能比父类多，多的功能无法凭空变出来。


//因此，向下转型很可能会失败。失败的时候，Java虚拟机会报ClassCastException。

//为了避免向下转型出错，Java提供了instanceof操作符，可以先判断一个实例究竟是不是某种类型：
//Person p = new Person();
//System.out.println(p instanceof Person); // true
//System.out.println(p instanceof Student); // false

//Student s = new Student();
//System.out.println(s instanceof Person); // true
//System.out.println(s instanceof Student); // true
//instanceof实际上判断一个变量所指向的实例是否是指定类型，或者这个类型的子类。


// 如果一个引用变量为null，那么对任何instanceof的判断都为false。
//Student n = null;
//System.out.println(n instanceof Student); // false



//利用instanceof，在向下转型前可以先判断：
//Person p = new Student();
//if (p instanceof Student) {
//    // 只有判断成功才会向下转型:
//    Student s = (Student) p; // 一定会成功
//}





//从Java 14开始，判断instanceof后，可以直接转型为指定变量，避免再次强制转型。例如，对于以下代码：
//Object obj = "hello";
//if (obj instanceof String) {
//    String s = (String) obj;
//    System.out.println(s.toUpperCase());
//}

//可以改写如下：
// instanceof variable:
//public class Main {
//    public static void main(String[] args) {
//        Object obj = "hello";
//        if (obj instanceof String s) {
//            // 可以直接使用变量s:
//            System.out.println(s.toUpperCase());
//        }
//    }
//}

//这种使用instanceof的写法更加简洁。
























// ================================================================== 区分继承和组合 ==================================================================
// 在使用继承时，我们要注意逻辑一致性。

// 考察下面的 Book 类：
// class Book {
//     protected String name;
//     public String getName() {...}
//     public void setName(String name) {...}
// }

// 这个 Book 类也有 name 字段，也能让 Student 继承自 Book
// class Student extends Book {
//     protected int score;
// }

// 但是从逻辑上讲，这是不合理的， Student 不应该从 Book 继承，而应该从 Person 继承。
// 究其原因，是因为 Student 是 Person 的一种，它们是is关系，而 Student 并不是 Book。
// 实际上 Student 和 Book 的关系是has关系。

// 具有has关系不应该使用继承，而是使用组合，即 Student 可以持有一个 Book 实例：
// class Student extends Person {
//     protected Book book;
//     protected int score;
// }

// 总结：继承是is关系，组合是has关系。

























































































































