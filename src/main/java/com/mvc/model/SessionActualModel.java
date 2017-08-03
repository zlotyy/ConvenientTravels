package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "SessionActual")
public class SessionActualModel {
    @Id
    @GeneratedValue
    @Column(name = "SessionId")
    private long sessionId;

    @Id
    @Column(name = "UserId")
    @NotEmpty
    private long userId;

    @Column(name = "LogInTime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    private Calendar logInTime;
}
