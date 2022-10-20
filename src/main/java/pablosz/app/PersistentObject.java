package pablosz.app;

import pablosz.app.domain.MySession;
import pablosz.app.domain.PersistentObjectDTO;

import java.lang.reflect.Field;

public class PersistentObject {

    public MySession getSession(){
        //obtener sesion creada previamente
        return new MySession();
    }

    public void createNewSession(){
        //crear sesion para luego recuperarla
    }

    public void store(Object object) throws IllegalAccessException {
        /**Aca debemos guardar en una tabla nuestro objeto, con la clase
         *
         */

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(object);
            if (value != null) {
                System.out.println(field.getName() + "=" + value);
            }
        }
//        new PersistentObjectDTO(object.getClass().toString(), object.);
    }

}
