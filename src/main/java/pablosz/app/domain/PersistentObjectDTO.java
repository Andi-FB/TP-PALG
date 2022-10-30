package pablosz.app.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity()
public class PersistentObjectDTO {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private long mykey;

    @ManyToOne
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "mysession_mykey")
    private MySession mysession;
    @Column
    private String clazz;
    @Column
    private String data;

    public MySession getMysession() {
        return mysession;
    }

    public PersistentObjectDTO(MySession mysessionkey, String clazz, String data) {
        this.mysession = mysessionkey;
        this.clazz = clazz;
        this.data = data;
    }

    public PersistentObjectDTO() {
    }

    public long getMykey() {
        return mykey;
    }

    public void setMykey(long mykey) {
        this.mykey = mykey;
    }

    public MySession getMysessionkey() {
        return mysession;
    }

    public void setMysessionkey(MySession mysessionkey) {
        this.mysession = mysessionkey;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
