import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) {
        LoadClass loadClass = new LoadClass();
        try{
            loadClass.loadClassByName();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
