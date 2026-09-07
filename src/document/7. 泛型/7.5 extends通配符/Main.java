/**
 * 7.5 extends通配符
 */

//我们前面已经讲到了泛型的继承关系：Pair<Integer>不是Pair<Number>的子类。

//假设我们定义了Pair<T>：
//public class Pair<T> {...}


//然后，我们又针对Pair<Number>类型写了一个静态方法，它接收的参数类型是Pair<Number>：
//public class PairHelper {
//    static int add(Pair<Number> p) {
//        Number first = p.getFirst();
//        Number last = p.getLast();
//        return first.intValue() + last.intValue();
//    }
//}


//上述代码是可以正常编译的。使用的时候，我们传入：
//int sum = PairHelper.add(new Pair<Number>(1, 2));



//完整代码：
//public class Main {
//    public static void main(String[] args) {
//        Pair<Number> p = new Pair<>(213, 456); // <== 传入的类型是Pair<Number>，实际参数类型是(Integer, Integer)。
//        int n = add(p);
//        System.out.println(n);
//    }
//
//    static int add(Pair<Number> p) {
//        Number first = p.getFirst();
//        Number last = p.getLast();
//        return first.intValue() + last.intValue();
//    }
//}
//
//class Pair<T> {
//    private  T first;
//    private T last;
//
//    public Pair(T first, T last) {
//        this.first = first;
//        this.last = last;
//    };
//
//    public T getFirst() {
//        return first;
//    }
//
//    public T getLast() {
//        return last;
//    }
//}
//$ java Main.java
//669


//注意：传入的类型是Pair<Number>，实际参数类型是(Integer, Integer)。




//既然实际参数是Integer类型，试试传入Pair<Integer>：
//public class Main {
//    public static void main(String[] args) {
//        Pair<Integer> p = new Pair<>(213, 456);
//        int n = add(p);
//        System.out.println(n);
//    }
//
//    static int add(Pair<Number> p) {
//        Number first = p.getFirst();
//        Number last = p.getLast();
//        return first.intValue() + last.intValue();
//    }
//}
//
//class Pair<T> {
//    private  T first;
//    private T last;
//
//    public Pair(T first, T last) {
//        this.first = first;
//        this.last = last;
//    };
//
//    public T getFirst() {
//        return first;
//    }
//
//    public T getLast() {
//        return last;
//    }
//}
//$ java Main.java
//Main.java:30: 错误: 无法将类 Main中的方法 add应用到给定类型;
//            int n = add(p);
//                    ^
//    需要: Pair<Number>
//    找到:    Pair<Integer>
//    原因: 参数不匹配; Pair<Integer>无法转换为Pair<Number>
//1 个错误
//错误: 编译失败


//直接运行，会得到一个编译错误：
//incompatible types: Pair<Integer> cannot be converted to Pair<Number>


//原因很明显，因为Pair<Integer>不是Pair<Number>的子类，因此，add(Pair<Number>)不接受参数类型Pair<Integer>。

//但是从add()方法的代码可知，传入Pair<Integer>是完全符合内部代码的类型规范，因为语句：
//Number first = p.getFirst();
//Number last = p.getLast();

//实际类型是Integer，引用类型是Number，没有问题。问题在于方法参数类型定死了只能传入Pair<Number>。








//有没有办法使得方法参数接受Pair<Integer>？
// 办法是有的，这就是使用Pair<? extends Number>使得方法接收所有泛型类型为Number或Number子类的Pair类型。
// 我们把代码改写如下：
//public class Main {
//    public static void main(String[] args) {
//        Pair<Integer> p = new Pair<>(123, 456);
//        int n = add(p);
//        System.out.println(n);
//    }
//
//    static int add(Pair<? extends Number> p) { // <== add(Pair<? extends Number> p)
//        Number first = p.getFirst();
//        Number last = p.getLast();
//        return first.intValue() + last.intValue();
//    }
//}
//
//class Pair<T> {
//    private T first;
//    private T last;
//    public Pair(T first, T last) {
//        this.first = first;
//        this.last = last;
//    }
//    public T getFirst() {
//        return first;
//    }
//    public T getLast() {
//        return last;
//    }
//}
//$ java Main.java
//579

//这样一来，给方法传入Pair<Integer>类型时，它符合参数Pair<? extends Number>类型。
// 这种使用<? extends Number>的泛型定义称之为上界通配符（Upper Bounds Wildcards），即把泛型类型T的上界限定在Number了。

//除了可以传入Pair<Integer>类型，我们还可以传入Pair<Double>类型，Pair<BigDecimal>类型等等，因为Double和BigDecimal都是Number的子类。





//如果我们考察对Pair<? extends Number>类型调用getFirst()方法，实际的方法签名变成了：
//<? extends Number> getFirst();

//即返回值是Number或Number的子类，因此，可以安全赋值给Number类型的变量：
//Number x = p.getFirst();


//然后，我们不可预测实际类型就是Integer，例如，下面的代码是无法通过编译的：
//Integer x = p.getFirst();
//这是因为实际的返回类型可能是Integer，也可能是Double或者其他类型，编译器只能确定类型一定是Number的子类（包括Number类型本身），但具体类型无法确定。




//我们再来考察一下Pair<T>的set方法：
//public class Main {
//    public static void main(String[] args) {
//        Pair<Integer> p = new Pair<>(123, 456);
//        int n = add(p);
//        System.out.println(n);
//    }
//
//    static int add(Pair<? extends Number> p) {
//        Number first = p.getFirst();
//        Number last = p.getLast();
//        p.setFirst(new Integer(first.intValue() + 100));
//        p.setLast(new Integer(last.intValue() + 100));
//        return p.getFirst().intValue() + p.getLast().intValue();
//    }
//}
//
//class Pair<T> {
//    private T first;
//    private T last;
//
//    public Pair(T first, T last) {
//        this.first = first;
//        this.last = last;
//    }
//
//    public T getFirst() {
//        return first;
//    }
//    public T getLast() {
//        return last;
//    }
//    public void setFirst(T first) {
//        this.first = first;
//    }
//    public void setLast(T last) {
//        this.last = last;
//    }
//}
//$ java Main.java
//Main.java:197: 错误: 无法将类 Pair<T>中的方法 setFirst应用到给定类型;
//        p.setFirst(new Integer(first.intValue() + 100));
//        ^
//    需要: CAP#1
//    找到:    Integer
//    原因: 参数不匹配; Integer无法转换为CAP#1
//    其中, T是类型变量:
//    T扩展已在类 Pair中声明的Object
//    其中, CAP#1是新类型变量:
//    CAP#1从? extends Number的捕获扩展Number
//Main.java:198: 错误: 无法将类 Pair<T>中的方法 setLast应用到给定类型;
//        p.setLast(new Integer(last.intValue() + 100));
//        ^
//    需要: CAP#1
//    找到:    Integer
//    原因: 参数不匹配; Integer无法转换为CAP#1
//    其中, T是类型变量:
//    T扩展已在类 Pair中声明的Object
//    其中, CAP#1是新类型变量:
//    CAP#1从? extends Number的捕获扩展Number
//Main.java:197: 警告: [deprecation] Integer中的Integer(int)已过时
//        p.setFirst(new Integer(first.intValue() + 100));
//        ^
//Main.java:198: 警告: [deprecation] Integer中的Integer(int)已过时
//        p.setLast(new Integer(last.intValue() + 100));
//        ^
//2 个错误
//2 个警告
//错误: 编译失败



//不出意外，我们会得到一个编译错误：
//incompatible types: Integer cannot be converted to CAP#1
//where CAP#1 is a fresh type-variable:
//CAP#1 extends Number from capture of ? extends Number

//编译错误发生在p.setFirst()传入的参数是Integer类型。有些童鞋会问了，既然p的定义是Pair<? extends Number>，那么setFirst(? extends Number)为什么不能传入Integer？
//原因还在于擦拭法。如果我们传入的p是Pair<Double>，显然它满足参数定义Pair<? extends Number>，然而，Pair<Double>的setFirst()显然无法接受Integer类型。

//这就是<? extends Number>通配符的一个重要限制：方法参数签名setFirst(? extends Number)无法传递任何Number的子类型给setFirst(? extends Number)。

//这里唯一的例外是可以给方法参数传入null：
//p.setFirst(null); // ok, 但是后面会抛出NullPointerException
//p.getFirst().intValue(); // NullPointerException










// ================================================ extends通配符的作用 ================================================
//如果我们考察Java标准库的java.util.List<T>接口，它实现的是一个类似“可变数组”的列表，主要功能包括：
//public interface List<T> {
//    int size(); // 获取个数
//    T get(int index); // 根据索引获取指定元素
//    void add(T t); // 添加一个新元素
//    void remove(T t); // 删除一个已有元素
//}

//现在，让我们定义一个方法来处理列表的每个元素：
//int sumOfList(List<? extends Integer> list) {
//    int sum = 0;
//    for (int i=0; i<list.size(); i++) {
//        Integer n = list.get(i);
//        sum = sum + n;
//    }
//    return sum;
//}

//为什么我们定义的方法参数类型是List<? extends Integer>而不是List<Integer>？
// 从方法内部代码看，传入List<? extends Integer>或者List<Integer>是完全一样的，但是，注意到List<? extends Integer>的限制：
// - 允许调用get()方法获取Integer的引用；
// - 不允许调用set(? extends Integer)方法并传入任何Integer的引用（null除外）。

//因此，方法参数类型List<? extends Integer>表明了该方法内部只会读取List的元素，
// 不会修改List的元素（因为无法调用add(? extends Integer)、remove(? extends Integer)这些方法。
// 换句话说，这是一个对参数List<? extends Integer>进行只读的方法（恶意调用set(null)除外）。

















// ================================================ 使用extends限定T类型 ================================================
//在定义泛型类型Pair<T>的时候，也可以使用extends通配符来限定T的类型：
//public class Pair<T extends Number> { ... }

//现在，我们只能定义：
//Pair<Number> p1 = null;
//Pair<Integer> p2 = new Pair<>(1, 2);
//Pair<Double> p3 = null;

//因为Number、Integer和Double都符合<T extends Number>。


//非Number类型将无法通过编译：
//Pair<String> p1 = null; // compile error!
//Pair<Object> p2 = null; // compile error!

//因为String、Object都不符合<T extends Number>，因为它们不是Number类型或Number的子类。








//小结
//使用类似<? extends Number>通配符作为方法参数时表示：
// - 方法内部可以调用获取Number引用的方法，例如：Number n = obj.getFirst();；
// - 方法内部无法调用传入Number引用的方法（null除外），例如：obj.setFirst(Number n);。
//即一句话总结：使用extends通配符表示可以读，不能写。
//使用类似<T extends Number>定义泛型类时表示：
// - 泛型类型限定为Number以及Number的子类。



