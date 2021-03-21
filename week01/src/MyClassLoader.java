import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class MyClassLoader extends ClassLoader {

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;
        String classFilename = name + ".xlass";
        try {
            byte[] b = readFileJar(classFilename);
            for (int i = 0; i < b.length; i++) {
                b[i] = (byte) (255 - b[i]);
            }
            clazz = defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clazz = myClassLoader.findClass("Hello");
        Object obj = clazz.newInstance();
        Method sayHello = clazz.newInstance().getClass().getMethod("hello");
        sayHello.invoke(obj);
    }

    public byte[] readFileJar(String filename) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(getClass().getResource(filename).openStream());
        int len = bufferedInputStream.available();
        byte[] bytes = new byte[len];
        int r = bufferedInputStream.read(bytes);
        if (len != r) {
            bytes = null;
            throw new IOException("read file error!");
        }
        bufferedInputStream.close();
        return bytes;
    }
}