package pablosz.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pablosz.app.PersistentObject;
import pablosz.app.domain.Jugador;

@SpringBootTest(classes= PersistentObject.class)
public class PersistentObjectTest {

    public PersistentObject po = new PersistentObject();

    @Test
    public void storeTest() throws IllegalAccessException {
        Jugador messi = new Jugador("Lio Messi", "Volante");
        Object ojeto = new Object();
        po.store(messi);
        Assertions.assertEquals(1, 1); //Queria ver como funcionaba el metodo store
    }
}
