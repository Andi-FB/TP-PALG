package pablosz.app.gson.config;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import pablosz.ann.NotPersistable;
import pablosz.ann.Persistable;

public class MyExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        //Posiblemente necesitemos adaptar mejor esto, capaz checkear la superclass tmb?

        if(fieldAttributes.getDeclaringClass().getAnnotation(NotPersistable.class) != null){
            return fieldAttributes.getAnnotation(Persistable.class) == null;
        } else if (fieldAttributes.getDeclaringClass().getAnnotation(Persistable.class) != null){
            return ! (fieldAttributes.getAnnotation(NotPersistable.class) == null);
        } else {
            return fieldAttributes.getAnnotation(Persistable.class) == null;
        }
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
