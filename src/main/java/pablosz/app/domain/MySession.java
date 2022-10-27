package pablosz.app.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity()
public class MySession {
    @Id
    @Column(name="mykey")
    private long mykey;
    @Column(name="ttl")
    private int ttl; //time to live
    @OneToMany(mappedBy = "mysession")
    private List<PersistentObjectDTO> parameters;


    public MySession(long key, int ttl){
        this.mykey = key;
        this.ttl = ttl;
    }

    public MySession() {

    }

    public long getMykey() {
        return mykey;
    }

    public void setMykey(long key) {
        this.mykey = key;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public List<PersistentObjectDTO> getParameters() {
        if(this.parameters == null){
            this.parameters = new ArrayList<>();
        }
        return parameters;
    }

    public void setParameters(List<PersistentObjectDTO> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(PersistentObjectDTO parameter) {
        if(this.parameters == null){
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
    }
}
