package com.mvc.dto;

public class UserRatesDTO {

    private int userRates;
    private int drivingSkills;
    private String personalityAssessment;


    public String getPersonalityAssessment() {
        return personalityAssessment;
    }

    public void setPersonalityAssessment(String personalityAssessment) {
        this.personalityAssessment = personalityAssessment;
    }


    public int getUserRates() {
        return userRates;
    }

    public void setUserRates(int userRates) {
        this.userRates = userRates;
    }

    public int getDrivingSkills() {
        return drivingSkills;
    }

    public void setDrivingSkills(int drivingSkills) {
        this.drivingSkills = drivingSkills;
    }

    @Override
    public String toString() {
        return "UserRatesDTO{" +
                "userRates=" + userRates +
                ", personalityAssessment=" + personalityAssessment +
                ", drivingSkills=" + drivingSkills +
                '}';
    }
}
