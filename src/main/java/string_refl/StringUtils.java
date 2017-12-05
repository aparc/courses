package string_refl;

import java.lang.reflect.Field;

public class StringUtils {

    private StringUtils(){}

    public static String toString(Object obj) throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        Class classObj = obj.getClass();

        Field[] fields = classObj.getDeclaredFields();
        if(fields.length == 0) {
            builder.append(classObj.getSimpleName()).
                    append("{}");
        }
        else {
            for (Field field : fields) {
                field.setAccessible(true);
                Class type = field.getType();
                if (!type.isPrimitive() && type != String.class) {
                    builder.append(field.getName()).
                            append(" : ").
                            append(toString(field.get(obj)));
                } else {
                    builder.append(field.getName()).
                            append(" = ").
                            append(field.get(obj) + ", ");
                }
            }
        }
        return builder.toString();
    }
}
