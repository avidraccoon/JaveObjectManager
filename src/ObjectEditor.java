import java.lang.reflect.*;
import java.util.ArrayList;

public class ObjectEditor {


    public ArrayList<Object> objects;

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
