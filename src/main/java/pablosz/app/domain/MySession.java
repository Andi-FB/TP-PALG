package pablosz.app.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity()
public class MySession {
    @Id
    @Column(name="mykey")
    private long mykey;
    @Column(name="ttl")
    private long ttl; //time to live
    @Column(name="date_created")
    private LocalDateTime dateCreated;
    @OneToMany(mappedBy = "mysession")
    private List<PersistentObjectDTO> parameters;


    public MySession(long key, long ttl){
        this.mykey = key;
        this.ttl = ttl;
        this.dateCreated = LocalDateTime.now();
    }

    public MySession() {

    }

    public long getMykey() {
        return mykey;
    }

    public void setMykey(long key) {
        this.mykey = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
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

    public void removeParameter(PersistentObjectDTO parameter) {
        if(this.parameters == null){
            return;
        }
        this.parameters.remove(parameter);
    }
}
