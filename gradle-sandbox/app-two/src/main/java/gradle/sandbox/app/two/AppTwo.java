package gradle.sandbox.app.two;

import com.google.common.collect.Lists;

import java.util.List;

public class AppTwo {

    public static void main(String[] args) {
        // we used the linked external library in build.gradle here:
        List<Character> characters = Lists.charactersOf("app-two");

        System.out.println("Hello World from app-two!");
    }

}
