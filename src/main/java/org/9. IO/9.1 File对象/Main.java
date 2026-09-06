/**
 * 9.1 File对象
 */

//在计算机系统中，文件是非常重要的存储方式。Java的标准库java.io提供了File对象来操作文件和目录。

//要构造一个File对象，需要传入文件路径：
//import java.io.*;
//public class Main {
//    public static void main(String[] args) {
//        File f = new File("C:\\Windows\\notepad.exe");
//        System.out.println(f);
//    }
//}

//构造File对象时，既可以传入绝对路径，也可以传入相对路径。绝对路径是以根目录开头的完整路径，例如：
//File f = new File("C:\\Windows\\notepad.exe");

//注意Windows平台使用\作为路径分隔符，在Java字符串中需要用\\表示一个\。

// Linux平台使用/作为路径分隔符：
//File f = new File("/usr/bin/javac");


//传入相对路径时，相对路径前面加上当前目录就是绝对路径：
// 假设当前目录是C:\Docs
//File f1 = new File("sub\\javac"); // 绝对路径是C:\Docs\sub\javac
//File f3 = new File(".\\sub\\javac"); // 绝对路径是C:\Docs\sub\javac
//File f3 = new File("..\\sub\\javac"); // 绝对路径是C:\sub\javac

//可以用.表示当前目录，..表示上级目录。


//import java.io.*;
//public class Main {
//    public static void main(String[] args) {
//        File f = new File("./demoFile.json");
//        System.out.println(f); // .\demoFile.json
//    }
//}
//$ java Main.java
//.\demoFile.json

//import java.io.*;
//public class Main {
//    public static void main(String[] args) {
//        File f = new File("D:\\web\\Java-study\\src\\9. IO\\9.1 File对象\\demoFile.json");
//        System.out.println(f); // D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//    }
//}
//$ java Main.java
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json










//File对象有3种形式表示的路径，
// 一种是getPath()，返回构造方法传入的路径，
// 一种是getAbsolutePath()，返回绝对路径，
// 一种是getCanonicalPath，它和绝对路径类似，但是返回的是规范路径。

//import java.io.*;
//public class Main {
//    public static void main(String[] args) throws IOException {
//        File f = new File("./demoFile.json");
//        System.out.println(f);                      // .\demoFile.json
//        System.out.println(f.getPath());            // .\demoFile.json
//        System.out.println(f.getAbsolutePath());    // D:\web\Java-study\src\9. IO\9.1 File对象\.\demoFile.json
//        System.out.println(f.getCanonicalPath());   // D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//    }
//}
//$ java Main.java
//.\demoFile.json
//.\demoFile.json
//D:\web\Java-study\src\9. IO\9.1 File对象\.\demoFile.json
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json



//import java.io.*;
//public class Main {
//    public static void main(String[] args) throws IOException {
//        File f = new File("D:\\web\\Java-study\\src\\9. IO\\9.1 File对象\\demoFile.json");
//        System.out.println(f);                      // D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//        System.out.println(f.getPath());            // D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//        System.out.println(f.getAbsolutePath());    // D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//        System.out.println(f.getCanonicalPath());   // D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//    }
//}
//$ java Main.java
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json








//什么是规范路径？举例：
//D:\
//└── a\
//    ├── demo.txt        ← 目标文件
//    └── b\              ← ✅程序启动工作目录
//        └── Main.java

//Main.java文件中执行：
//File file = new File("../demo.txt");
//System.out.println(f.getPath());            // 相对路径：..\demo.txt
//System.out.println(f.getAbsolutePath());    // 绝对路径：D:\a\b\..\demo.txt
//System.out.println(f.getCanonicalPath());   // 规范路径：D:\a\demo.txt


//1. **相对路径**：`../demo.txt`，只是字符串，不解析，相对于启动目录 `D:/a/b`
//2. **绝对路径**：只是拼上根目录，**不会消除 `..`**，路径字符串还带 `\b\..`
//3. **规范路径**：操作系统帮你解析 `..`，得到真实物理位置 `D:\a\demo.txt`









//因为Windows和Linux的路径分隔符不同，File对象有一个静态变量用于表示当前平台的系统分隔符：
//import java.io.*;
//public class Main {
//    public static void main(String[] args) {
//        System.out.println(File.separator); // 根据当前平台打印"\"或"/"。 Windows下是\
//    }
//}






















// ===================================================== 文件和目录 =====================================================
//File对象既可以表示文件，也可以表示目录。特别要注意的是，构造一个File对象，即使传入的文件或目录不存在，代码也不会出错，因为构造一个File对象，并不会导致任何磁盘操作。
// 只有当我们调用File对象的某些方法的时候，才真正进行磁盘操作。

//例如，调用isFile()，判断该File对象是否是一个已存在的文件，调用isDirectory()，判断该File对象是否是一个已存在的目录：
//import java.io.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        File f1 = new File("C:\\Windows");
//        File f2 = new File("C:\\Windows\\notepad.exe");
//        File f3 = new File("C:\\Windows\\nothing");
//        System.out.println(f1.isFile());
//        System.out.println(f1.isDirectory());
//        System.out.println(f2.isFile());
//        System.out.println(f2.isDirectory());
//        System.out.println(f3.isFile());
//        System.out.println(f3.isDirectory());
//    }
//}

//用File对象获取到一个文件时，还可以进一步判断文件的权限和大小：
// - boolean canRead()：是否可读；
// - boolean canWrite()：是否可写；
// - boolean canExecute()：是否可执行；
// - long length()：文件字节大小。

//对目录而言，是否可执行表示能否列出它包含的文件和子目录。

























// ===================================================== 创建和删除文件 =====================================================
//当File对象表示一个文件时，可以通过
// 用createNewFile()创建一个新文件，
// 用delete()删除该文件：

//import java.io.*;
//public class Main {
//    public static void main(String[] args) throws IOException {
//        File file = new File("./newCreate.json");
//        if (file.createNewFile()) {
//            System.out.println("文件创建成功");
//
//            if (file.delete()) {
//                System.out.println("文件删除成功");
//            }
//        } else {
//            System.out.println("文件创建失败");
//        }
//    }
//}

// 通过file.createNewFile()，可以在当前目录下新增创建一个newCreate.json文件
// 通过file.delete()，可以在当前目录下的newCreate.json文件删除








//有些时候，程序需要读写一些临时文件，
// File对象提供了createTempFile()来创建一个临时文件，
// 以及deleteOnExit()在JVM退出时自动删除该文件。
//import java.io.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        File f = File.createTempFile("tmp-", ".txt"); // 提供临时文件的前缀和后缀
//        f.deleteOnExit(); // JVM退出时自动删除
//        System.out.println(f.isFile());
//        System.out.println(f.getAbsolutePath());
//    }
//}
//$ java Main.java
//true
//C:\Users\kivet\AppData\Local\Temp\tmp-12262595317640064642.txt



























// ===================================================== 遍历文件和目录 =====================================================
//当File对象表示一个目录时，可以使用list()和listFiles()列出目录下的文件和子目录名：
//例如当前目录为：
//9. IO
//    |-9.0 IO
//    |    |-Main.java
//    |-9.1 File对象
//         |-Main.java
//         |-demoFile.json
//         |-newCreate.json

//import java.io.*;
//public class Main {
//    public static void main(String[] args) {
//        File f = new File("D:\\web\\Java-study\\src\\9. IO");
//        File[] fs1 = f.listFiles();
//        printFilts(fs1);
//    }
//
//    static void printFilts(File[] files) {
//        if (files != null) {
//            for (File file : files) {
//                System.out.println(file);
//            }
//        }
//    }
//}
//$ java Main.java
//D:\web\Java-study\src\9. IO\9.0 IO
//D:\web\Java-study\src\9. IO\9.1 File对象





//listFiles()提供了一系列重载方法，可以过滤不想要的文件和目录：
//import java.io.*;
//public class Main {
//    public static void main(String[] args) {
//        File f = new File("D:\\web\\Java-study\\src\\9. IO\\9.1 File对象"); // 查看【9.1 File对象】目录下的文件
//        File[] fs1 = f.listFiles(new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".json"); // 筛选查看【9.1 File对象】目录下所有.json文件
//            }
//        });
//        printFilts(fs1);
//    }
//
//    static void printFilts(File[] files) {
//        if (files != null) {
//            for (File file : files) {
//                System.out.println(file);
//            }
//        }
//    }
//}
//$ java Main.java
//D:\web\Java-study\src\9. IO\9.1 File对象\demoFile.json
//D:\web\Java-study\src\9. IO\9.1 File对象\newCreate.json


//和文件操作类似，File对象如果表示一个目录，可以通过以下方法创建和删除目录：
// - boolean mkdir()：创建当前File对象表示的目录；
// - boolean mkdirs()：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
// - boolean delete()：删除当前File对象表示的目录，当前目录必须为空才能删除成功。

























// ================================================= Path =================================================
//import java.io.*;
//import java.nio.file.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        Path p1 = Paths.get(".", "project", "study"); // 构造一个Path对象
//        System.out.println(p1); // .\project\study
//
//        Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
//        System.out.println(p2); // D:\web\Java-study\src\9. IO\9.1 File对象\.\project\study
//
//        Path p3 = p2.normalize(); // 转换为规范路径
//        System.out.println(p3); // D:\web\Java-study\src\9. IO\9.1 File对象\project\study
//
//        File f = p3.toFile(); // 转换为File对象
//        System.out.println(f); // D:\web\Java-study\src\9. IO\9.1 File对象\project\study
//
//        for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
//            System.out.println("  " + p);
//        }
//    }
//}
//$ java Main.java
//.\project\study
//D:\web\Java-study\src\9. IO\9.1 File对象\.\project\study
//D:\web\Java-study\src\9. IO\9.1 File对象\project\study
//D:\web\Java-study\src\9. IO\9.1 File对象\project\study
//  web
//  Java-study
//  src
//  9. IO
//  9.1 File对象
//  ..


//如果需要对目录进行复杂的拼接、遍历等操作，使用Path对象更方便。

















//小结
//Java标准库的java.io.File对象表示一个文件或者目录：
// - 创建File对象本身不涉及IO操作；
// - 可以获取路径／绝对路径／规范路径：getPath()/getAbsolutePath()/getCanonicalPath()；
// - 可以获取目录的文件和子目录：list()/listFiles()；
// - 可以创建或删除文件和目录。