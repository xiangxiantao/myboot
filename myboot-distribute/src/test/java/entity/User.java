package entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -3363120264501521428L;

    private String name;

    private int agr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgr() {
        return agr;
    }

    public void setAgr(int agr) {
        this.agr = agr;
    }
}
