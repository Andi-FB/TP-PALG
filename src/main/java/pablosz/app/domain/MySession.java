package pablosz.app.domain;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity()
public class MySession {

    @Column(name="key")
    private long key;
    @Column(name="ttl")
    private int ttl; //time to live


    public MySession(long key, int ttl){
        this.key = key;
        this.ttl = ttl;
    }

}
