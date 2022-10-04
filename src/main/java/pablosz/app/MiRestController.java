package pablosz.app;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pablosz.app.domain.Jugador;

@RestController
public class MiRestController
{
	@RequestMapping("/hola/{name}")
	public String test(@PathVariable String name) {
		return "Hola Mundo, Hola " + name + " !";
	}

	@PostMapping("/hola")
	public String test2(@RequestBody Jugador jugador){
		return "Hola Jugador: " + jugador.getNombre() + " Numero de camiseta: " + jugador.getId() + " jugas de: " + jugador.getPosicion();

	}
	@RequestMapping("/hola")
	public String test() {
		return "Hola Mundo!";
	}
}
