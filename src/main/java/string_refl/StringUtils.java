package string_refl;

import java.lang.reflect.Field;

public class StringUtils {

    private StringUtils(){}

    public static String toString(SomeClass obj) throws IllegalAccessException {

        Class classObj = obj.getClass();
        Field[] fields = classObj.getDeclaredFields();
        String result = "";
        for(Field field: fields) {
            field.setAccessible(true);
            result += field.getName() + " = " + String.valueOf(field.get(obj)) + "\n";
        }

        return result;
    }
}
