package annotation;

import java.lang.reflect.Field;

public class LoginProcessor {
    private LoginProcessor(){}

    public static void startProcess(SampleClass testObject) throws NoSuchFieldException, IllegalAccessException {
        Class classObj = testObject.getClass();

        final Field[] fields = classObj.getDeclaredFields();
        boolean key = false;
        for(Field field: fields) {
            key = field.getName().toString().equals("name") ? true : key;
        }
        if(key == false) {
            throw new NoSuchFieldException();
        }
        for(Field field: fields) {
            final Login annotation = field.getAnnotation(Login.class);
            if(annotation != null) {
                StringBuilder builder = new StringBuilder();
                builder.append(annotation.line());
                field.setAccessible(true);
                field.set(testObject, builder.reverse().toString());
            }
        }




    }
}
