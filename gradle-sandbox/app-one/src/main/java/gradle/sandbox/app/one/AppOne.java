package gradle.sandbox.app.one;

import gradle.sandbox.library.one.LibraryOneUtil;

public class AppOne {

    public static void main(String[] args) {
        // we used the linked internal library in build.gradle here:
        var util = new LibraryOneUtil();
        util.hello();

        System.out.println("Hello World from app-one!");
    }

}
