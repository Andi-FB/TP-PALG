package pablosz.app.domain;

public class PersistentObjectDTO {

    public String clazz;
    public String data;

    public PersistentObjectDTO(String clazz, String data){
        this.setClazz(clazz);
        this.setData(data);
    }

    public String getClazz() {
        return clazz;
    }

    public String getData() {
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public void setClazz(String clazz){
        this.data = clazz;
    }
}
