package pablosz.app.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity()
public class MySession {
    @Id
    @Column(name="key")
    private long key;
    @Column(name="ttl")
    private int ttl; //time to live
    @Transient
    private List<Object> parameters;


    public MySession(long key, int ttl){
        this.key = key;
        this.ttl = ttl;
    }

    public MySession() {

    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(Object parameter) {
        if(this.parameters == null){
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
    }
}
