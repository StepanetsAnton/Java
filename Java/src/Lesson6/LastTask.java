package Lesson6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;

public class LastTask {
    public static void main(String[] args){

        String fileName = args[0];

        List<String> classList= new ArrayList<>();
        try (FileInputStream in = new FileInputStream(fileName);
             ZipInputStream stream = new ZipInputStream(in);) {
            ZipEntry ze;

            while ((ze = stream.getNextEntry()) != null) {

                if(ze.getName().contains("class")){
                    String name = ze.getName().replace('/', '.');
                    System.out.println(name);
                    classList.add(name.substring(0, name.length() - ".class".length()));

                }
            }

            try (URLClassLoader loader = new URLClassLoader(new URL[]{new URL("file:"+fileName)})) {

                for (String each : classList) {
                    Class<?> cl = loader.loadClass(each);
                    System.out.println(cl.getSimpleName());
                    Method[] method = cl.getDeclaredMethods();
                    for (int i = 0; i < method.length; i++) System.out.println(method[i]);
                    Field[] fields = cl.getDeclaredFields();
                    for (int k = 0; k < fields.length; k++) System.out.println(fields[k]);
                    Annotation[] annotations = cl.getAnnotations();
                    for (int a = 0; a < annotations.length; a++) System.out.println(annotations[a]);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}