package com.mvc.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordDTO {

    @NotEmpty(message = "Pole nie moze byc puste")
    private String oldPassword;

    @NotEmpty(message = "Pole nie moze byc puste")
    private String newPassword;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
