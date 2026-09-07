/**
 * 3.1.1 方法
 */

//一个class可以包含多个field，例如，我们给Person类就定义了两个field：
//class Person {
//    public String name;
//    public int age;
//}

//但是，直接把field用public暴露给外部可能会破坏封装性。比如，代码可以这样写：
//Person ming = new Person();
//ming.name = "Xiao Ming";
//ming.age = -99; // age设置为负数


//显然，直接操作field，容易造成逻辑混乱。为了避免外部代码直接去访问field，我们可以用private修饰field，拒绝外部访问：
//class Person {
//    private String name;
//    private int age;
//}

//试试private修饰的field有什么效果：
//public class Main {
//    public static void main(String[] args) {
//        Person wang = new Person();
//        wang.name = "kivet"; // 报错：错误: name 在 Person 中是 private 访问控制
//    }
//}
//class Person {
//    private String name;
//    private int age;
//}

//结果会导致编译报错，但是把访问field的赋值语句去了就可以正常编译了。






//把field从public改成private，外部代码不能访问这些field，那我们定义这些field有什么用？怎么才能给它赋值？怎么才能读取它的值？
//所以我们需要使用方法（method）来让外部代码可以间接修改field：
//public class Main {
//    public static void main(String[] args) {
//        Person ming = new Person();
//        ming.setName("Xiao Ming"); // 设置name
//        ming.setAge(12); // 设置age
////        ming.setAge(120); // 设置age // 报错：Exception in thread "main" java.lang.IllegalArgumentException: invalid age value
//        System.out.println(ming.getName() + ", " + ming.getAge()); // ==> Xiao Ming, 12
//    }
//}
//class Person {
//    private String name;
//    private int age;
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setAge(int age) {
//        if (age < 0 || age > 100) {
//            throw new IllegalArgumentException("invalid age value");
//        }
//        this.age = age;
//    }
//
//    public int getAge() {
//        return age;
//    }
//}

//虽然外部代码不能直接修改private字段，但是，外部代码可以调用方法setName()和setAge()来间接修改private字段。
// 在方法内部，我们就有机会检查参数对不对。比如，setAge()就会检查传入的参数，参数超出了范围，直接报错。这样，外部代码就没有任何机会把age设置成不合理的值。
//对setName()方法同样可以做检查，例如，不允许传入null和空字符串：
//public void setName(String name) {
//    if (name == null || name.isBlank()) {
//        throw new IllegalArgumentException("invalid name");
//    }
//    this.name = name.strip(); // 去掉首尾空格
//}

//同样，外部代码不能直接读取private字段，但可以通过getName()和getAge()间接获取private字段的值。
//所以，一个类通过定义方法，就可以给外部代码暴露一些操作的接口，同时，内部自己保证逻辑一致性。
//调用方法的语法是实例变量.方法名(参数);。一个方法调用就是一个语句，所以不要忘了在末尾加;。例如：ming.setName("Xiao Ming");


















// ================================================================== 定义方法 ==================================================================
//从上面的代码可以看出，定义方法的语法是：

//修饰符 方法返回类型 方法名(方法参数列表) {
//    若干方法语句;
//    return 方法返回值;
//}

//方法返回值通过return语句实现，如果没有返回值，返回类型设置为void，可以省略return。


















// ================================================================== private方法 ==================================================================
//有public方法，自然就有private方法。和private字段一样，private方法不允许外部调用，那我们定义private方法有什么用？

//定义private方法的理由是内部方法是可以调用private方法的。例如
// private method
//public class Main {
//    public static void main(String[] args) {
//        Person ming = new Person();
//        ming.setBirth(2008);
//        System.out.println(ming.getAge());
//    }
//}
//
//class Person {
//    private String name;
//    private int birth;
//
//    public void setBirth(int birth) {
//        this.birth = birth;
//    }
//
//    public int getAge() {
//        return calcAge(2019); // 调用private方法
//    }
//
//    // private方法:
//    private int calcAge(int currentYear) {
//        return currentYear - this.birth;
//    }
//}
//观察上述代码，calcAge()是一个private方法，外部代码无法调用，但是，内部方法getAge()可以调用它。
//此外，我们还注意到，这个Person类只定义了birth字段，没有定义age字段，获取age时，通过方法getAge()返























// ================================================================== this变量 ==================================================================
//在方法内部，可以使用一个隐含的变量this，它始终指向当前实例。因此，通过this.field就可以访问当前实例的字段。
//如果没有命名冲突，可以省略this。例如：

//class Person {
//    private String name;
//
//    public String getName() {
//        return name; // 相当于this.name
//    }
//}

//但是，如果有局部变量和字段重名，那么局部变量优先级更高，就必须加上this：

//class Person {
//    private String name;
//
//    public void setName(String name) {
//        this.name = name; // 前面的this不可少，少了就变成局部变量name了
//    }
//}






















// ================================================================== 方法参数 ==================================================================
//方法可以包含0个或任意个参数。方法参数用于接收传递给方法的变量值。调用方法时，必须严格按照参数的定义一一传递。例如：
//class Person {
//    ...
//    public void setNameAndAge(String name, int age) {
//        ...
//    }
//}

//调用这个setNameAndAge()方法时，必须有两个参数，且第一个参数必须为String，第二个参数必须为int：
//Person ming = new Person();
//ming.setNameAndAge("Xiao Ming"); // 编译错误：参数个数不对
//ming.setNameAndAge(12, "Xiao Ming"); // 编译错误：参数类型不对























// ================================================================== 可变参数 ==================================================================
//可变参数用类型...定义，可变参数相当于数组类型：
//import java.util.Arrays;
//public class Main {
//    public static void main(String[] args) {
//        Group group = new Group();
//        group.setNames();
//        group.setNames("xiao hong");
//        group.setNames("xiao hong", "xiao lv");
//        group.setNames("xiao hong", "xiao lv",  "xiao lan");
//        group.setNames(null);
//    }
//}
//
//class Group {
//    private String[] names;
//
//    public void setNames(String... names) {
//        System.out.println(Arrays.toString(names));
//        this.names = names;
//    }
//}
// ==> 依次打印
//[]
//[xiao hong]
//[xiao hong, xiao lv]
//[xiao hong, xiao lv, xiao lan]
//null // 注意：参数传null时，不是空数组，也不是数组，就是个null





//完全可以把可变参数改写为String[]类型：
//class Group {
//    private String[] names;
//
//    public void setNames(String[] names) {
//        this.names = names;
//    }
//}

//但是，调用方需要自己先构造String[]，比较麻烦。例如：

//Group g = new Group();
//g.setNames(new String[] {"Xiao Ming", "Xiao Hong", "Xiao Jun"}); // 传入1个String[]


//另一个问题是，调用方可以传入null：
//Group g = new Group();
//g.setNames(null);
//而可变参数可以保证无法传入null，因为传入0个参数时，接收到的实际值是一个空数组而不是null。
























// ================================================================== 参数绑定 ==================================================================
// 1. 基本类型参数传递：

//public class Main {
//    public static void main(String[] args) {
//        Person p = new Person();
//        int n = 15; // n的值为15
//        p.setAge(n); // 传入n的值
//        System.out.println(p.getAge()); // 15
//        n = 20; // n的值改为20
//        System.out.println(p.getAge()); // 结果是15，不是20
//    }
//}
//
//class Person {
//    private int age;
//
//    public int getAge() {
//        return this.age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}


//运行代码，从结果可知，修改外部的局部变量n，不影响实例p的age字段，原因是setAge()方法获得的参数，复制了n的值，因此，p.age和局部变量n互不影响。

//结论：基本类型参数的传递，是调用方值的复制。双方各自的后续修改，互不影响。






// 2. 引用类型参数传递：
// 引用类型参数绑定
//public class Main {
//    public static void main(String[] args) {
//        Person p = new Person();
//        String[] fullname = new String[] { "Homer", "Simpson" };
//        p.setName(fullname); // 传入fullname数组
//        System.out.println(p.getName()); // "Homer Simpson"
//        fullname[0] = "Bart"; // fullname数组的第一个元素修改为"Bart"
//        System.out.println(p.getName()); // 结果是：Bart Simpson，不是Homer Simpson
//    }
//}
//
//class Person {
//    private String[] name;
//
//    public String getName() {
//        return this.name[0] + " " + this.name[1];
//    }
//
//    public void setName(String[] name) {
//        this.name = name;
//    }
//}
//注意到setName()的参数现在是一个数组。一开始，把fullname数组传进去，然后，修改fullname数组的内容，结果发现，实例p的字段p.name也被修改了！

//结论：引用类型参数的传递，调用方的变量，和接收方的参数变量，指向的是同一个对象。双方任意一方对这个对象的修改，都会影响对方（因为指向同一个对象嘛）。


//指向过程详解：
//Step 1：创建数组
//String[] fullname = new String[] { "Homer", "Simpson" };
//在堆内存中创建了一个数组对象，假设地址为 0x100
//栈中的变量 fullname 保存了地址 0x100，指向这个数组
//
//栈内存              堆内存
//fullname ──────→  [0]="Homer"  [1]="Simpson"  (地址 0x100)
//
//
//Step 2：调用 setName(fullname)
//public void setName(String[] name) {
//    this.name = name;  // 关键行
//}
//传参时，将 fullname 里保存的地址 0x100 复制一份给参数 name
//this.name = name 又将这个地址 0x100 赋给了成员变量 this.name
//
//
//栈(main)           栈(setName)         堆内存
//fullname ──────→  name ─────────→  [0]="Homer"  [1]="Simpson"  (0x100)
//                  this.name ─────→  （同上，指向同一个数组）
//此时 fullname 和 this.name 指向堆中同一个数组对象。
//
//
//Step 3：修改数组内容
//fullname[0] = "Bart";
//通过 fullname 这个引用，找到堆中的数组对象，把索引 0 的内容改为 "Bart"
//由于 this.name 也指向同一个数组对象，所以通过 this.name 看到的数组也变了
//堆内存（修改后）：
//[0]="Bart"  [1]="Simpson"  (地址仍是 0x100)
//
//Step 4：输出结果
//System.out.println(p.getName()); // "Bart Simpson"







// 引用类型参数绑定
//public class Main {
//    public static void main(String[] args) {
//        Person p = new Person();
//        String bob = "Bob";
//        p.setName(bob); // 传入bob变量
//        System.out.println(p.getName()); // "Bob"
//        bob = "Alice"; // bob改名为Alice
//        System.out.println(p.getName()); // 结论是："Bob"，不是"Alice"
//    }
//}
//
//class Person {
//    private String name;
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}


////指向过程详解：
//Step 1：创建字符串
//String bob = "Bob";
//在字符串常量池（堆的一部分）中创建 "Bob"，假设地址为 0x200
//栈中的 bob 保存地址 0x200，指向 "Bob"
//
//栈内存         堆内存（字符串常量池）
//bob ──────→  "Bob"  (地址 0x200)
//
//
//Step 2：调用 setName(bob)
//public void setName(String name) {
//    this.name = name;
//}
//将 bob 中的地址 0x200 复制一份给参数 name
//this.name = name 后，成员变量也保存了 0x200
//
//栈(main)      栈(setName)      堆内存
//bob ──────→  name ──────→  "Bob"  (0x200)
//             this.name ───→  （指向同一个字符串）
//
//
//Step 3：重新赋值
//bob = "Alice";
//String 是不可变的，这里不是把 0x200 地址里的 "Bob" 改成 "Alice"
//而是在常量池中新建了字符串 "Alice"（假设地址 0x300）
//然后把栈中 bob 的指向从 0x200 改为 0x300
//
//栈(main)                堆内存
//bob ──────────────→  "Alice" (0x300)   ← 新的指向
//this.name ─────────→  "Bob"   (0x200)   ← 仍指向原来的字符串
//
//关键区别：bob = "Alice" 改变的是变量 bob 自己的指向，而不是修改 0x200 处的内容。this.name 手里的地址副本仍然是 0x200，不受影响。
//
//
//Step 4：输出结果
//System.out.println(p.getName()); // "Bob"
//
//
//结论：String 是不可变对象，bob = "Alice" 只是让 bob 指向了新的字符串对象，并没有改变原来 "Bob" 对象的内容，而 this.name 仍然指向原来的 "Bob"。





























