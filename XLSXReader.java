import jxl.demo.Demo;

import java.io.File;

public class XLSXReader {
    public static void main(String[] args) {
        String filename = new File("Book1.xls").getAbsolutePath();
        Demo.main(new String[] {filename});
    }
}
