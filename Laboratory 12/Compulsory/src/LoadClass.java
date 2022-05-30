import org.testng.Assert;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadClass {
    public void loadClassByName() throws Exception {
        File file = new File("D:\\facultate_PA\\Programare-Avansata\\Laboratory 12\\Compulsory\\src");

        URL url = file.toURI().toURL();

        URL[] urls = new URL[]{url};
        ClassLoader classLoader = new URLClassLoader(urls);

        String searchedFile = "test.TestClass";
        Package classPackage = Class.forName(searchedFile).getPackage();
        System.out.println("Package is: " + classPackage);

        StringBuilder stringBuilder = new StringBuilder("Methods are: ");
        for(Method method : Class.forName(searchedFile).getMethods()){
            method.setAccessible(true);
            stringBuilder.append(method.getName()).append(", ");
        }
        System.out.println(stringBuilder);

        Class<?> clazz = classLoader.loadClass(searchedFile);
        Method method = clazz.getDeclaredMethod("sayHello");
        method.setAccessible(true);

        Object result = method.invoke(null);

        Assert.assertEquals("Hello", result);
    }


}
