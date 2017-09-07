package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "Message")
public class MessageModel {
    @Id
    @GeneratedValue
    @Column(name = "MessageId")
    private long messageId;

    @Column(name = "Content")
    @NotEmpty(message = "Pole nie moze byc puste")
    private String content;

    @Column(name = "SendTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar sendTime;

    @Column(name = "IsDisplayed")
    private boolean isDisplayed;

    @Column(name = "IsDeleted")
    private boolean isDeleted;

    @Column(name = "IsAnswered")
    private boolean isAnswered;

    @ManyToOne
    @JoinColumn(name = "SenderId")
    private UserModel sender;

    @ManyToOne
    @JoinColumn(name = "ReceiverId")
    private UserModel receiver;



    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public UserModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserModel receiver) {
        this.receiver = receiver;
    }
}
