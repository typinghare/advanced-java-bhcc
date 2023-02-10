package edu.bhcc;

/**
 * Passenger entity.
 */
public class Passenger {
    private Integer passengerId;

    private Boolean survived;

    private Integer pClass;

    private String name;

    private Sex sex;

    private Double age;

    private Integer sibSP;

    private Integer parch;

    private String ticket;

    private Double fare;

    private String cabin;

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Boolean getSurvived() {
        return survived;
    }

    public void setSurvived(Boolean survived) {
        this.survived = survived;
    }

    public Integer getPClass() {
        return pClass;
    }

    public void setPClass(Integer pClass) {
        this.pClass = pClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Integer getSibSP() {
        return sibSP;
    }

    public void setSibSP(Integer sibSP) {
        this.sibSP = sibSP;
    }

    public Integer getParch() {
        return parch;
    }

    public void setParch(Integer parch) {
        this.parch = parch;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
}
