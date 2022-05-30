package test;

import org.testng.annotations.Test;

public class TestClass {
    public String message;
    public void printMessage(){
        System.out.println(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Test
    public static String sayHello(){
        return "Hello";
    }
}
