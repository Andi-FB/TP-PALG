package pablosz.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pablosz.app.Application;
import pablosz.app.PersistentObject;
import pablosz.app.SessionListener;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class) // UPDATE: Para que levante el context e inyecte el PersistentObjects
public class SessionListenerTest implements SessionListener
{
	private static final long loopThread=1000;

	private static final long key1 = 1;
	private static final long timeOut1 = 5000;

	private static final long key2 = 2;
	private static final long timeOut2 = 10000;

	private int session1Opened=0;
	private int session2Opened=0;
	private int session1StillOpened=0;
	private int session2StillOpened=0;

	@Autowired
	private PersistentObject po;

	@BeforeEach
	public void init()
	{
		po.addListener(this); // UPDATE: Registrar Listener para que funcione el test
		po.createSession(key1,timeOut1);
		po.createSession(key2,timeOut2);
	}

	@AfterEach
	public void destroy()
	{
		po.destroySession(key1);
		po.destroySession(key2);
		po.removeListener(this); // UPDATE: Delete del listener después de cada test
	}

	@Test
	public void testSessionOpenedClosed()
	{
		assertTrue(session1Opened==1);
		assertTrue(session2Opened==1);

		esperar(timeOut1/2);
		assertTrue(session1Opened==1);
		assertTrue(session2Opened==1);

		esperar((timeOut1/2)+(timeOut1/2));
		assertTrue(session1Opened==0);
		assertTrue(session2Opened==1);

		long restaEsperar = Math.abs(timeOut1-timeOut2)+loopThread*2;
		esperar(restaEsperar);
		assertTrue(session1Opened==0);
		assertTrue(session2Opened==0);
	}

	@Test
	public void testSessionStillOpenedClosed()
	{
		assertTrue(session2Opened==1);

		// no deberia haberse llamado a sessionSTillOpen
		int i=0;
		assertTrue(session2StillOpened==i);

		long acum = 0;

		while( acum<(timeOut2 - loopThread) )
		{


			assertTrue(session2StillOpened==i);
			i++;

			esperar(loopThread+100);

			acum+=loopThread ;
		}

		esperar(loopThread); // UPDATE: espero un loop más, para que se cierre la session

		assertTrue(session2Opened==0);

		// espero un loop, debe seguir en closed
		esperar(loopThread);

		System.out.println("SESS2: " + session2StillOpened);
		System.out.println("I: " + i);

		assertTrue(session2StillOpened==i);

	}

	@Override
	public void sessionOpened(long key)
	{
		final int k=(int)key;
		final int k1=(int)key1;
		final int k2=(int)key2;

		switch( k )
		{
			case k1:
				session1Opened++;
				System.out.println(session1Opened);

				break;
			case k2:
				session2Opened++;
				System.out.println(session2Opened);

				break;
		}
	}

	@Override
	public void sessionStillOpened(long key)
	{
		final int k=(int)key;
		final int k1=(int)key1;
		final int k2=(int)key2;

		switch( k )
		{
			case k1:
				session1StillOpened++;
				System.out.println(session1StillOpened);
				break;
			case k2:
				session2StillOpened++;
				System.out.println(session2StillOpened);

				break;
		}
	}


	@Override
	public void sessionClosed(long key)
	{
		final int k=(int)key;
		final int k1=(int)key1;
		final int k2=(int)key2;

		switch( k )
		{
			case k1:
				session1Opened--;
				System.out.println(session1Opened);
				// UPDATE: cambio el ++ por --, ya que el stillClosed debería decrementar este contador
				break;
			case k2:
				session2Opened--;
				System.out.println(session2Opened);

				// UPDATE: cambio el ++ por --, ya que el stillClosed debería decrementar este contador
				break;
		}
	}

	@Override
	public void sessionStillClosed(long key)
	{
		final int k=(int)key;
		final int k1=(int)key1;
		final int k2=(int)key2;

		switch( k )
		{
			case k1:
				session1StillOpened--;
				System.out.println(session1StillOpened);
				break;
			case k2:
				session2StillOpened--;
				System.out.println(session2StillOpened);

				break;
		}
	}

	public void esperar(long n)
	{
		try
		{
			Thread.sleep(n);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
