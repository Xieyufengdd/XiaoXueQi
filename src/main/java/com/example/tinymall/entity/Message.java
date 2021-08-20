package com.example.tinymall.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer MID;

    private Integer SID;
    private Integer RID;
    private String text;
    private Integer type;

    @Override
    public String toString() {
        return "Message{" +
                "MID=" + MID +
                ", SID=" + SID +
                ", RID=" + RID +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public Integer getMID() {
        return MID;
    }

    public Integer getRID() {
        return RID;
    }

    public Integer getSID() {
        return SID;
    }

    public String getText() {
        return text;
    }

    public void setMID(Integer MID) {
        this.MID = MID;
    }

    public void setRID(Integer RID) {
        this.RID = RID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
