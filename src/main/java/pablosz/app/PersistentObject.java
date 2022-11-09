package pablosz.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pablosz.app.domain.MySession;
import pablosz.app.domain.PersistentObjectDTO;
import pablosz.app.gson.config.MyExclusionStrategy;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class PersistentObject {

    private Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new MyExclusionStrategy()).create();

    private static final Set<String> primitiveTypes = new HashSet<>(
            Arrays.asList("java.lang.String", "java.lang.Integer", "java.lang.Double") // Agregar los "primitivos"
    );


    @Autowired
    private EntityManager em;

    public void createSession(long key, long ttl) {
        MySession session = new MySession(key, ttl);
        em.persist(session);
    }

    public void store(long key, Object object) {
        MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                .setParameter("value1", key).getSingleResult();

        if(entity == null) throw new RuntimeException();
        PersistentObjectDTO persistentObjectDTO;

        if (primitiveTypes.contains(object.getClass().getName())){
            persistentObjectDTO = new PersistentObjectDTO(entity, object.getClass().getName(), object.toString());
        }else {
            persistentObjectDTO = new PersistentObjectDTO(entity, object.getClass().getName(), gson.toJson(object));
        }

        em.persist(persistentObjectDTO);
    }

    public void destroySession(long key) {
        try {
            MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                    .setParameter("value1", key).getSingleResult();
            entity.getParameters().forEach(x -> em.remove(x));
            em.remove(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object load(long key, Class clazz) {
        Object result = null;
        try{
            MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                    .setParameter("value1", key).getSingleResult();

            if(entity == null) return null;

            if(!entity.getParameters().stream().filter(x -> x.getClazz().equals(clazz.getName())).findFirst().isEmpty()){
                PersistentObjectDTO persistentObjectDTO = entity.getParameters().stream().filter(x -> x.getClazz().equals(clazz.getName())).findFirst().get();

                switch (persistentObjectDTO.getClazz()) {
                    case "java.lang.String":
                        return persistentObjectDTO.getData();
                    case "java.lang.Integer":
                        return Integer.parseInt(persistentObjectDTO.getData());
                    default:
                        return gson.fromJson(persistentObjectDTO.getData(), clazz);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public void remove(long key, Class clazz) {
        MySession entity = (MySession) em.createQuery("SELECT t FROM MySession t where t.mykey = :value1")
                .setParameter("value1", key).getSingleResult();

        PersistentObjectDTO parameter = entity.getParameters().stream().filter(x -> x.getClazz().equals(clazz.getName())).findFirst().orElse(null);
        if(parameter == null) throw new RuntimeException();
        em.remove(parameter);
    }

}
