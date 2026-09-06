/**
 * 3.2.3 StringJoiner
 */

//要高效拼接字符串，应该使用StringBuilder。

//很多时候，我们拼接的字符串像这样：
//public class Main {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sb = new StringBuilder();
//        for(String name : names) {
//            sb.append(name);
//            sb.append(", ");
//        }
//        sb.delete(sb.length()-2, sb.length());
//        sb.append("!");
//        System.out.println(sb.toString());
//    }
//}
//$ java Main.java
//Bob, Alice, Grace!


//类似用分隔符拼接数组的需求很常见，所以Java标准库还提供了一个StringJoiner来干这个事：
//import java.util.StringJoiner;            // 注意需要引入
//public class Main {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sj = new StringJoiner(", ");
//        for(String name : names) {
//            sj.add(name);                 // 注意这里是add，不是append
//        }
//        System.out.println(sj.toString());
//    }
//}
//$ java Main.java
//Bob, Alice, Grace


//慢着！用StringJoiner的结果少了前面的"Hello "和结尾的"!"！遇到这种情况，需要给StringJoiner指定“开头”和“结尾”：
//import java.util.StringJoiner;            // 注意需要引入
//public class Main {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sj = new StringJoiner(", ", "Hello", "!");
//        for(String name : names) {
//            sj.add(name);                 // 注意这里是add，不是append
//        }
//        System.out.println(sj.toString());
//    }
//}
//$ java Main.java
//HelloBob, Alice, Grace!
























// ================================================================== String.join() ==================================================================
//String还提供了一个静态方法join()，这个方法在内部使用了StringJoiner来拼接字符串，在不需要指定“开头”和“结尾”的时候，用String.join()更方便：
//public class Main {
//    public static void main(String[] args) {
//        String[] names = {"Bob", "Alice", "Grace"};
//        var sj = String.join(", ", names);
//        System.out.println(sj);
//    }
//}
//$ java Main.java
//Bob, Alice, Grace



































































