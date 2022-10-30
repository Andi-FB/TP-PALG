package pablosz.app;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


// +---------------------------------------------------------+
// | NOTA: Si queremos organizar los componentes y entidades | 
// | dentro de diferentes paquetes tendremos que especificar |
// | explicitamente cada uno de estos descomenando las       |
// | siguientes lineas: @ComponentScan y @EntityScan         |
// +---------------------------------------------------------+
@ComponentScan(basePackages={"pablosz.app"})
@EntityScan(basePackages={"pablosz.app,pablosz.app.domain"})

@SpringBootApplication
public class Application implements CommandLineRunner
{
    private static Logger LOG = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class,args);
	}
	
	@Autowired
	private EntityManager em;
	
	@Override
	public void run(String... args) throws Exception
	{
		LOG.info("Todo funciona correctamente? "  + (em!=null));
	}
}
