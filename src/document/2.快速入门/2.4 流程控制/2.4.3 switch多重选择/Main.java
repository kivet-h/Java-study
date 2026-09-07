/**
 * 2.4.3 Switch 多重选择
 */

//除了if语句外，还有一种条件判断，是根据某个表达式的结果，分别去执行不同的分支。

//例如，在游戏中，让用户选择选项：
//1.单人模式
//2.多人模式
//3.退出游戏
//这时，switch语句就派上用场了。

//switch语句根据switch (表达式)计算的结果，跳转到匹配的case结果，然后继续执行后续语句，直到遇到break结束执行。
//
//我们看一个例子：
//public class Main {
//    public static void main(String[] args) {
//        int option = 1;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1"); // ==> Selected 1
//                break;
//            case 2:
//                System.out.println("Selected 2");
//                break;
//            case 3:
//                System.out.println("Selected 3");
//        }
//    }
//}


//如果option的值没有匹配到任何case，例如option = 99，那么，switch语句不会执行任何语句。这时，可以给switch语句加一个default，当没有匹配到任何case时，执行default：
//public class Main {
//    public static void main(String[] args) {
//        int option = 10;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//                break;
//            case 2:
//                System.out.println("Selected 2");
//                break;
//            case 3:
//                System.out.println("Selected 3");
//            default:
//                System.out.println("Selected other"); // ==> Selected other
//                break;
//        }
//    }
//}




//if (option == 1) {
//        System.out.println("Selected 1");
//} else if (option == 2) {
//        System.out.println("Selected 2");
//} else if (option == 3) {
//        System.out.println("Selected 3");
//} else {
//        System.out.println("Selected other");
//}
//对比 if ... else if语句，对于多个==判断的情况，使用switch结构更加清晰。
//同时注意，上述“翻译”只有在switch语句中对每个case正确编写了break语句才能对应得上。

//使用switch时，注意case语句并没有花括号{}，
// 而且，case语句具有“穿透性”，漏写break将导致意想不到的结果：
// switch
//public class Main {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//            case 2:
//                System.out.println("Selected 2"); // ==> Selected 2
//            case 3:
//                System.out.println("Selected 3"); // ==> Selected 3
//            default:
//                System.out.println("Selected other"); // ==> Selected other
//        }
//    }
//}
//当option = 2时，将依次输出"Selected 2"、"Selected 3"、"Selected other"，原因是从匹配到case 2开始，后续语句将全部执行，直到遇到break语句。因此，任何时候都不要忘记写break。







//如果有几个case语句执行的是同一组语句块，可以这么写：
// switch
//public class Main {
//    public static void main(String[] args) {
//        int option = 2;
//        switch (option) {
//            case 1:
//                System.out.println("Selected 1");
//                break;
//            case 2:
//            case 3:
//                System.out.println("Selected 2, 3"); // ==> Selected 2, 3
//                break;
//            default:
//                System.out.println("Selected other");
//                break;
//        }
//    }
//}





//使用switch语句时，只要保证有break，case的顺序不影响程序逻辑：
//switch (option) {
//    case 3:
//        ...
//        break;
//    case 2:
//        ...
//        break;
//    case 1:
//        ...
//        break;
//}
//但是仍然建议按照自然顺序排列，便于阅读。





//switch语句还可以匹配字符串。字符串匹配时，是比较“内容相等”。例如：
// switch
//public class Main {
//    public static void main(String[] args) {
//        String fruit = "apple";
//        switch (fruit) {
//            case "apple":
//                System.out.println("Selected apple"); // ==> Selected apple
//                break;
//            case "pear":
//                System.out.println("Selected pear");
//                break;
//            case "mango":
//                System.out.println("Selected mango");
//                break;
//            default:
//                System.out.println("No fruit selected");
//                break;
//        }
//    }
//}



//switch语句还可以使用枚举类型，枚举类型后面讲解。





















































































































































































































