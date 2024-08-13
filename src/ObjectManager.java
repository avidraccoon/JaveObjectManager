import java.lang.reflect.*;
import java.util.ArrayList;

public class ObjectManager {

    private final ArrayList<Object> objects = new ArrayList<>();

    public ObjectManager(Object object) {
        select(object);
    }
    public Object getObject(){
        return objects.getLast();
    }
    public Class<?> getObjectClass(){
        return getObject().getClass();
    }
    public Field[] getFields(){
        return getObjectClass().getFields();
    }
    public Field getField(String name){
        try{
            return getObjectClass().getDeclaredField(name);
        }catch (NoSuchFieldException e) {
            System.out.println("Cannot get Field");
        }
        return null;
    }
    public void setField(Field field, Object value){
        field.setAccessible(true);
        try {
            field.set(getObject(), value);
        }catch (IllegalAccessException e){
            System.out.println("Cannot set Field");
        }
    }
    public Object getFieldValue(Field field){
        field.setAccessible(true);
        try {
            return field.get(getObject());
        }catch (IllegalAccessException e){
            System.out.println("Cannot get Field");
        }
        return null;
    }
    public Method[] getMethods(){
        return getObjectClass().getMethods();
    }
    public Modifiers getModifiers(Field field){
        return new Modifiers(field.getModifiers());
    }
    public Modifiers getModifiers(Method method){
        return new Modifiers(method.getModifiers());
    }
    public void select(Object object){
        objects.add(object);
    }
    public void back(){
        objects.removeLast();
    }
    public boolean isPrimative(Object object){
        return isPrimative(object.getClass());
    }
    public boolean isPrimative(Class<?> clazz){
        if (clazz.equals(String.class)) return true;
        if (clazz.equals(Integer.class)) return true;
        if (clazz.equals(Double.class)) return true;
        if (clazz.equals(Long.class)) return true;
        if (clazz.equals(Short.class)) return true;
        if (clazz.equals(Boolean.class)) return true;
        return false;
    }
    public Object getPrimativeValue(String str, Class<?> clazz){
        if (clazz.equals(String.class)) return str;
        if (clazz.equals(Integer.class)) return getInt(str);
        if (clazz.equals(Double.class)) return getDouble(str);
        if (clazz.equals(Long.class)) return getLong(str);
        if (clazz.equals(Short.class)) return getShort(str);
        if (clazz.equals(Boolean.class)) return getBoolean(str);
        return null;
    }
    private Integer getInt(String str){
        return Integer.valueOf(str);
    }
    private Double getDouble(String str) {
        return Double.valueOf(str);
    }
    private Long getLong(String str){
        return Long.valueOf(str);
    }
    private Short getShort(String str){
        return Short.valueOf(str);
    }
    private Boolean getBoolean(String str){
        return Boolean.valueOf(str);
    }



    public static class Modifiers{
        public final boolean PUBLIC;
        public final boolean PRIVATE;
        public final boolean PROTECTED;
        public final boolean FINAL;
        public final boolean STATIC;
        public final boolean ABSTRACT;
        public final boolean INTERFACE;
        public final boolean STRICT;
        public final boolean NATIVE;
        public final boolean SYNCHRONIZED;
        public final boolean TRANSIENT;
        public final boolean VOLATILE;

        public Modifiers(int modifier){
            PUBLIC = Modifier.isPublic(modifier);
            PRIVATE = Modifier.isPrivate(modifier);
            PROTECTED = Modifier.isProtected(modifier);
            FINAL = Modifier.isFinal(modifier);
            STATIC = Modifier.isStatic(modifier);
            ABSTRACT = Modifier.isAbstract(modifier);
            INTERFACE = Modifier.isInterface(modifier);
            STRICT = Modifier.isStrict(modifier);
            NATIVE = Modifier.isNative(modifier);
            SYNCHRONIZED = Modifier.isSynchronized(modifier);
            TRANSIENT = Modifier.isTransient(modifier);
            VOLATILE = Modifier.isVolatile(modifier);
        }
    }

}
