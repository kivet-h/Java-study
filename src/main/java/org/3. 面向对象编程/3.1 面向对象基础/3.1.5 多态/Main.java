/**
 * 3.1.5 多态
 */

// 在继承关系中，子类如果定义了一个与父类方法签名完全相同的方法，被称为覆写（Override）。

// 例如，在 Person 类中，我们定义了 run() 方法：
// class Person {
//     public void run() {
//         System.out.println("Person.run");
//     }
// }

// 在子类 Student 中，覆写这个 run() 方法：
// class Student extends Person {
//     @Override
//     public void run() {
//         System.out.println("Student.run");
//     }
// }







// 先解释下什么是方法签名：
// 方法签名是用来唯一标识一个方法的关键部分。

// 方法签名包括一下内容：
// - 方法名（Method Name）
// - 参数类型列表（Parameter Types）（参数的顺序和数量）

// 方法签名不包括以下内容：
// - 返回值类型（Return Type）
// - 访问修饰符（如 public、private）
// - 异常声明（如 throws IOException）
// - static、final、synchronized 等修饰符

// 举个例子：
// public int add(int a, int b)
// public double add(double a, double b)
// 这两个方法的方法签名分别是：
// add(int, int)
// add(double, double)
// 因为参数类型不同，所以是不同的方法签名，这也就是 方法重载（Overload）。




// Override(覆写) 与 Overload(重载) 的区别：
// 特性	        Override（重写）	            Overload（重载）
// 方法签名	    必须相同	                    必须不同
// 返回值类型     必须相同（协变返回类型除外）	    无关（可以不同）
// 所在位置	    子类重写父类的方法	            同一个类或子类中

// Override和Overload不同的是：如果方法签名不同，就是Overload。
// Overload方法是一个新方法；
// 如果方法签名相同，并且返回值也相同，就是 Override 。

// 代码示例：
// class Person {
//     public void run() { … }
// }
// class Student extends Person {
//     // 不是Override，因为参数不同:
//     public void run(String s) { … }
//
//     // 不是Override，因为返回值不同:
//     public int run() { … }
// }

// 注意：方法名相同，方法参数相同，但方法返回值不同，也是不同的方法。在Java程序中，出现这种情况，编译器会报错。

// 但是 @Override 不是必需的。

// 在上一节中，我们已经知道，引用变量的声明类型可能与其实际类型不符，例如：
// Person p = new Student();

// 现在，我们考虑一种情况，如果子类覆写了父类的方法：
// public class Main {
//     public static void main(String[] args) {
//         Person p = new Student();
//         p.run();
//     }
// }

// class Person {
//     public void run() {
//         System.out.println("Person.run");
//     }
// }

// class Student extends Person {
//     @Override
//     public void run() {
//         System.out.println("Student.run");
//     }
// }
// ==> Student.run

// 运行一下上面的代码就可以知道，实际上调用的方法是 Student 的 run() 方法。因此可得出结论：
// Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
// 这个非常重要的特性在面向对象编程中称之为多态。它的英文拼写非常复杂：Polymorphic。



















// ================================================================== 多态 ==================================================================
//多态是指，针对某个类型的方法调用，其真正执行的方法取决于运行时期实际类型的方法。
// 例如：
//public class Main {
//    public static void main(String[] args) {
//        Person p = new Student();
//        p.run(); // 无法确定运行时究竟调用哪个run()方法
//    }
//}
//
//class Person {
//    public void run() {
//        System.out.println("Person.run");
//    }
//}
//
//class Student extends Person {
//    @Override
//    public void run() {
//        System.out.println("Student.run");
//    }
//}




//有同学会说，从上面的代码一看就明白，肯定调用的是Student的run()方法啊。
//但是，假设我们编写这样一个方法：
//Person p = new Student();
//public void runTwice(Person p) {
//    p.run();
//    p.run();
//}
//它传入的参数类型是Person，我们是无法知道传入的参数实际类型究竟是Person，还是Student，还是Person的其他子类例如Teacher，因此，也无法确定调用的是不是Person类定义的run()方法。

//所以，多态的特性就是，运行期才能动态决定调用的子类方法。对某个类型调用某个方法，执行的实际方法可能是某个子类的覆写方法。

// 这种不确定性的方法调用，究竟有什么作用？
//们还是来举例子。

//假设我们定义一种收入，需要给它报税，那么先定义一个Income类：
//class Income {
//    protected double income;
//
//    public double getTax() {
//        return income * 0.1; // 税率10%
//    }
//}

//对于工资收入，可以减去一个基数，那么我们可以从Income派生出SalaryIncome，并覆写getTax()：
//class Salary extends Income {
//    @Override
//    public double getTax() {
//        if (income <= 5000) {
//            return 0;
//        }
//        return (income - 5000) * 0.2;
//    }
//}


//如果你享受国务院特殊津贴，那么按照规定，可以全部免税：
//class StateCouncilSpecialAllowance extends Income {
//    @Override
//    public double getTax() {
//        return 0;
//    }
//}


//现在，我们要编写一个报税的财务软件，对于一个人的所有收入进行报税，可以这么写：
//public double totalTax(Income incomes) {
//    double total = 0;
//    for (Income income: incomes) {
//        total += income.getTax();
//    }
//    return total;
//}

//完整代码：
//public class Main {
//    public static void main(String[] args) {
//        Income[] incomes = new Income[] {
//                new Income(3000),
//                new Salary(7500),
//                new StateCouncilSpecialAllowance(15000)
//        };
//        System.out.println(totalTax(incomes)); // ==> 800.0 = 3000 * 0.1 + (7500 - 5000) * 0.2 + 0 = 300 + 2500 * 0.2 = 300 + 500
//    }
//
//    public static double totalTax(Income... incomes) {
//        double total = 0;
//        for (Income income: incomes) {
//            total += income.getTax();
//        }
//        return total;
//    }
//}
//
//// 定义一个Income类
//class Income {
//    protected double income;
//
//    public Income(double income) {
//        this.income = income;
//    }
//
//    public double getTax() {
//        return income * 0.1; // 税率10%
//    }
//}
//
//// 工资收入，可以减去一个基数5000不用报税
//class Salary extends Income {
//    public Salary(double income) {
//        super(income);
//    }
//
//    @Override
//    public double getTax() {
//        if (income <= 5000) {
//            return 0;
//        }
//        return (income - 5000) * 0.2;
//    }
//}
//
////享受国务院特殊津贴，可以免税
//class StateCouncilSpecialAllowance extends Income {
//    public StateCouncilSpecialAllowance(double income) {
//        super(income);
//    }
//
//    @Override
//    public double getTax() {
//        return 0;
//    }
//}

//观察totalTax()方法：
// 利用多态，totalTax()方法只需要和Income打交道，它完全不需要知道Salary和StateCouncilSpecialAllowance的存在，就可以正确计算出总的税。
// 如果我们要新增一种稿费收入，只需要从Income派生，然后正确覆写getTax()方法就可以。把新的类型传入totalTax()，不需要修改任何代码。

//可见，多态具有一个非常强大的功能，就是允许添加更多类型的子类实现功能扩展，却不需要修改基于父类的代码。



























// ================================================================== 更多关于多态 ==================================================================
// Java 的多态（Polymorphism）是面向对象编程（OOP）的三大核心特性之一，另外两个是封装（Encapsulation）和继承（Inheritance）。
// 多态的字面意思是“多种形态”，在 Java 中指的是同一个接口或方法在不同对象中具有不同的实现。



// 一、多态的基本概念
// 多态性允许我们使用一个统一的接口来操作不同类型的对象。
// 例如:一个 Person 类型的引用可以指向 Student 或 Teacher 的对象，并调用它们各自重写的 run() 方法。


// 二、多态的实现条件
// 在 Java 中，实现多态需要满足以下三个条件：
// - 继承（Inheritance）：子类必须继承父类。
// - 方法重写（Override）：子类必须重写父类的方法。
// - 父类引用指向子类对象（Upcasting）：用父类的引用变量指向子类的对象。




// 三、多态的分类
// Java 中的多态主要分为两种类型：

// 1. 编译时多态（静态多态）——方法重载（Overload）
// - 在编译阶段就确定调用哪个方法。
// - 通过方法名相同、参数不同来实现。
// class Math {
//     int add(int a, int b) {
//         return a + b;
//     }
//
//     double add(double a, double b) {
//         return a + b;
//     }
// }

// 2. 运行时多态（动态多态）——方法重写（Override）
// - 在运行阶段根据对象的实际类型决定调用哪个方法。
// - 通过继承 + 方法重写 + 向上转型实现。
// class Animal {
//     void speak() {
//         System.out.println("Animal speaks");
//     }
// }
// class Dog extends Animal {
//     @Override
//     void speak() {
//         System.out.println("Dog barks");
//     }
// }
// class Cat extends Animal {
//     @Override
//     void speak() {
//         System.out.println("Cat meows");
//     }
// }
// public class Main {
//     public static void main(String[] args) {
//         Animal a1 = new Dog();
//         Animal a2 = new Cat();
//
//         a1.speak();  // 输出: Dog barks
//         a2.speak();  // 输出: Cat meows
//     }
// }





// 四、向上转型（Upcasting）与向下转型（Downcasting）
// 1. 向上转型（Upcasting）
// - 子类对象赋值给父类引用。
// - 安全、自动进行。
// Animal animal = new Dog();  // 向上转型

// 2. 向下转型（Downcasting）
// - 父类引用强制转换为子类类型。
// - 不安全，需要显式转换，容易引发 ClassCastException。
// Animal animal = new Dog();
// Dog dog = (Dog) animal;  // 向下转型

// 如果 animal 实际不是 Dog 类型，会抛出 ClassCastException。

// 可使用 instanceof 检查
// if (animal instanceof Dog) {
//      Dog dog = (Dog) animal;
//      dog.bark();
// }


// 五、多态的优缺点
// ✅ 优点：
// 代码复用性强：通过继承和方法重写，减少重复代码。
// 可扩展性好：新增子类时无需修改已有代码。
// 可维护性高：统一接口，便于统一管理。
// 提高程序灵活性：可以实现通用的编程逻辑。

// ❌ 缺点：
// 不能访问子类特有的方法或属性：向上转型后，只能访问父类中定义的方法。
// 需要向下转型：如果需要访问子类特有功能，必须进行强制类型转换。
// 性能略低：运行时多态需要在运行时查找实际方法，效率略低于静态绑定。




























// ================================================================== 覆写Object方法 ==================================================================
//因为所有的class最终都继承自Object，而Object定义了几个重要的方法：
// - toString()：把instance输出为String；
// - equals()：判断两个instance是否逻辑相等；
// - hashCode()：计算一个instance的哈希值。
// - 在必要的情况下，我们可以覆写Object的这几个方法。例如：

//在必要的情况下，我们可以覆写Object的这几个方法。例如：
//class Person {
//    ...
//    // 显示更有意义的字符串:
//    @Override
//    public String toString() {
//        return "Person:name=" + name;
//    }
//
//    // 比较是否相等:
//    @Override
//    public boolean equals(Object o) {
//        // 当且仅当o为Person类型:
//        if (o instanceof Person) {
//            Person p = (Person) o;
//            // 并且name字段相同时，返回true:
//            return this.name.equals(p.name);
//        }
//        return false;
//    }
//
//    // 计算hash:
//    @Override
//    public int hashCode() {
//        return this.name.hashCode();
//    }
//}


























// ================================================================== 调用super ==================================================================
//在子类的覆写方法中，如果要调用父类的被覆写的方法，可以通过super来调用。例如：
//public class Main {
//    public static void main(String[] args) {
//        Student s =  new Student();
//        System.out.println(s.hello());
//    }
//}
//
//class Person {
//    protected String name = "person default name";
//    public String hello() {
//        return "Hello, " + name;
//    }
//}
//
//class Student extends Person {
//    @Override
//    public String hello() {
//        // 调用父类的hello()方法:
//        return super.hello() + "!";
//    }
//}
//$ java Main.java
//Hello, person default name!

























// ================================================================== final ==================================================================
//继承可以允许子类覆写父类的方法。如果一个父类不允许子类对它的某个方法进行覆写，可以把该方法标记为final。用final修饰的方法不能被Override：
//public class Main {
//    public static void main(String[] args) {
//        Student s =  new Student();
//        System.out.println(s.hello());
//    }
//}
//
//class Person {
//    protected String name = "person default name";
//    public final String hello() {
//        return "Hello, " + name;
//    }
//}
//
//class Student extends Person {
//    @Override
//    public String hello() {
//        // 调用父类的hello()方法:
//        return super.hello() + "!";
//    }
//}
//$ java Main.java
//Main.java:554: 错误: Student中的hello()无法覆盖Person中的hello()
//public String hello() {
//                  ^
//    被覆盖的方法为final
//    1 个错误
//    错误: 编译失败






//如果一个类不希望任何其他类继承自它，那么可以把这个类本身标记为final。用final修饰的类不能被继承：
//public class Main {
//    public static void main(String[] args) {
//        Student s =  new Student();
//        System.out.println(s.hello());
//    }
//}
//
//final class Person {
//    protected String name = "person default name";
//    public String hello() {
//        return "Hello, " + name;
//    }
//}
//
//class Student extends Person {
//    @Override
//    public String hello() {
//        // 调用父类的hello()方法:
//        return super.hello() + "!";
//    }
//}
//$ java Main.java
//Main.java:587: 错误: 无法从最终Person进行继承
//class Student extends Person {
//                      ^
//    1 个错误
//    错误: 编译失败






//对于一个类的实例字段，同样可以用final修饰。用final修饰的字段在初始化后不能被修改。
//例如：
//class Person {
//    public final String name = "Unamed";
//}


// 正常修改：
//public class Main {
//    public static void main(String[] args) {
//        Person p =  new Person();
//        p.name = "new name";
//        System.out.println(p.name); // new name
//    }
//}
//class Person {
//    public String name = "person default name";
//}
//$ java Main.java
//new name

// 使用final修饰：
//public class Main {
//    public static void main(String[] args) {
//        Person p =  new Person();
//        p.name = "new name";
//        System.out.println(p.name); // new name
//    }
//}
//class Person {
//    public final String name = "person default name";
//}
//$ java Main.java
//Main.java:631: 错误: 无法为 final 变量 name 分配值
//p.name = "new name";
//        ^
//1 个错误
//错误: 编译失败







// 但是可以在构造方法中初始化final字段，不会报错：
//public class Main {
//    public static void main(String[] args) {
//        Person p =  new Person("new name");
//        System.out.println(p.name); // new name
//    }
//}
//class Person {
//    public final String name;
//    public Person(String name) {
//        this.name = name;
//    }
//}
//$ java Main.java
//new name

//这种方法更为常用，因为可以保证实例一旦创建，其final字段就不可修改。














































