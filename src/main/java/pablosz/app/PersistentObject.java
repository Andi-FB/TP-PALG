package pablosz.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pablosz.app.domain.Jugador;
import pablosz.app.domain.MySession;
import pablosz.app.domain.PersistentObjectDTO;
import javax.persistence.EntityManager;
import java.util.List;

@Component
@Transactional
public class PersistentObject {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private EntityManager em;

    public void createSession(long key, int ttl) {
        MySession session = new MySession(key, ttl);
        em.persist(session);
    }

    public void store(long key, Object object) {
        MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                .setParameter("value1", key).getSingleResult();

        if(entity == null) throw new RuntimeException();

        entity.addParameter(object);
    }

    public void destroySession(long key) {
        MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                .setParameter("value1", key).getSingleResult();
        em.remove(entity);
    }

    public Object load(long key, Class clazz) {
        MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                .setParameter("value1", key).getSingleResult();

        if(entity == null) return null;

        if(entity.getParameters().stream().filter(x -> x.getClass() == clazz).findFirst().isEmpty())
            return null;
        else
            return entity.getParameters().stream().filter(x -> x.getClass() == clazz).findFirst().get();
    }

    public void remove(long key, Class clazz) {
        MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                .setParameter("value1", key).getSingleResult();

        Object parameter = entity.getParameters().stream().filter(x -> x.getClass() == clazz).findFirst();
        if(parameter == null) throw new RuntimeException();
        entity.getParameters().remove(parameter);
    }
/*
    public MySession getSession(){
       return new MySession(1, 1);
    }

    public <T> T load(long key, Class clazz){

        return (clazz.cast(new Jugador())) ;
    }

    public void createSession(long key, int ttl){
        MySession session = new MySession(key, ttl);
        em.persist(session);
    }

    public void store(long key, Object object) {

        PersistentObjectDTO po = new PersistentObjectDTO();
        po.setData(this.gson.toJson(object));
        po.setClazz(object.getClass().getName());

//        em.

    }

    public void destroySession(long key){

    }
*/
}
