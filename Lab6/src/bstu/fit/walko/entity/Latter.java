package bstu.fit.walko.entity;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Latter {
    private long lattersID;
    private long sender;
    private long adressee;
    private String topic;
    private String text;
    private Date sendDate;

    public Latter(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getLattersID() {
        return lattersID;
    }

    public void setLattersID(long lattersID) {
        this.lattersID = lattersID;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getAdressee() {
        return adressee;
    }

    public void setAdressee(long adressee) {
        this.adressee = adressee;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "Latter{" +
                "lattersID=" + lattersID +
                ", sender=" + sender +
                ", adressee=" + adressee +
                ", topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
