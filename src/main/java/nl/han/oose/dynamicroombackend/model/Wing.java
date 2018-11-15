package nl.han.oose.dynamicroombackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wing {

    @Column(name = "WINGCODE")
    @Id
    String wingCode;

    public String getWingCode() {
        return wingCode;
    }

    public void setWingCode(String wingCode) {
        this.wingCode = wingCode;
    }
}
