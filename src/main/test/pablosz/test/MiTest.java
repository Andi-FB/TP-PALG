package pablosz.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pablosz.app.Application;
import pablosz.app.domain.Jugador;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes=Application.class)
public class MiTest {

	private Gson gson = new GsonBuilder().create();

	@Autowired
	private EntityManager em;
	
	@Test
	public void funcionaOk()
	{
		assertNotNull(em);
	}

	@Test
	public void gsonTest()
	{
		Assertions.assertEquals( "1", gson.toJson(1));
	}

	@Test
	public void objectGetName()
	{
		Jugador messi = new Jugador();
		assertNotEquals(messi.getClass().getName(), "holamundo");
	}
}
