package pablosz.app.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jugador {

    @Id
    private Integer id;

    private String nombre;

    private String posicion;

    public Jugador(){}

    public Jugador(String nombre, String posicion){
        this.setNombre(nombre);
        this.setPosicion(posicion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


}
