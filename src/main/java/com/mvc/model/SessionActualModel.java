package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "SessionActual")
public class SessionActualModel {
    @Id
    @GeneratedValue
    @Column(name = "SessionId")
    private long sessionId;

    @Column(name = "LogInTime")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty(message = "Pole nie moze byc puste")
    private Calendar logInTime;

    // Polaczenie 1 User do N Sesji
    @ManyToOne
    @JoinColumn(name = "UserId")
    private UserModel user;

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public Calendar getLogInTime() {
        return logInTime;
    }

    public void setLogInTime(Calendar logInTime) {
        this.logInTime = logInTime;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
