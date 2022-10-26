package pablosz.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pablosz.app.domain.Jugador;
import pablosz.app.domain.MySession;
import pablosz.app.domain.PersistentObjectDTO;
import javax.persistence.EntityManager;

public class PersistentObject {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private EntityManager em;

    public MySession getSession(){
        //obtener sesion creada previamente
        return new MySession();
    }

    public <T> T load(long key, Class clazz){

        return (clazz.cast(new Jugador())) ;
    }

    public void createSession(long key, int ttl){
        //crear sesion para luego recuperarla
    }

    public void store(long key, Object object) {

        PersistentObjectDTO po = new PersistentObjectDTO();
        po.setData(this.gson.toJson(object));
        po.setClazz(object.getClass().getName());

//        em.

    }

    public void destroySession(long key){

    }

}
