package com.example.tinymall.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer FID;
    private Integer USR_ID;
    private Integer FRI_ID;

    public Friend(){}

    public Friend(Integer USR_ID, Integer FRI_ID) {
        this.USR_ID = USR_ID;
        this.FRI_ID = FRI_ID;
    }

    public Integer getFID() {
        return FID;
    }

    public void setFID(Integer FID) {
        this.FID = FID;
    }

    public Integer getUSR_ID() {
        return USR_ID;
    }

    public void setUSR_ID(Integer USR_ID) {
        this.USR_ID = USR_ID;
    }

    public Integer getFRI_ID() {
        return FRI_ID;
    }

    public void setFRI_ID(Integer FRI_ID) {
        this.FRI_ID = FRI_ID;
    }
}
