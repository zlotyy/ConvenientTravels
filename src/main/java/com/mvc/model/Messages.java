package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Messages implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "MessageId")
    private long messageId;

    @Id
    @Column(name = "SenderId")
    @NotEmpty
    private long senderId;

    @Id
    @Column(name = "ReceiverId")
    @NotEmpty
    private long receiverId;

    @Column(name = "Content")
    @NotEmpty
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
}
