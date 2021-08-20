package com.example.tinymall.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer OID;
    private Integer GID; //商品ID
    private Integer UID; //用户ID
    private String address; //配送位置
    private Integer num;//商品数量

    public Orders() {}

    public Orders(Integer GID, Integer UID, String address) {
        this.GID = GID;
        this.UID = UID;
        this.address = address;
    }

    public Integer getOID() {
        return OID;
    }

    public void setOID(Integer OID) {
        this.OID = OID;
    }

    public Integer getGID() {
        return GID;
    }

    public void setGID(Integer GID) {
        this.GID = GID;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
