package com.mum.processexceldata;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public  class Rate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Service service;
    @OneToOne
    private Country destination;
    private Double offPeakRate;
    private Double peakRate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date effectiveDate;

    public Rate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Country getDestination() {
        return destination;
    }

    public void setDestination(Country destination) {
        this.destination = destination;
    }

    public Double getOffPeakRate() {
        return offPeakRate;
    }

    public void setOffPeakRate(Double offPeakRate) {
        this.offPeakRate = offPeakRate;
    }

    public Double getPeakRate() {
        return peakRate;
    }

    public void setPeakRate(Double peakRate) {
        this.peakRate = peakRate;
    }

//    public Date getStartPeakTime() {
//        return startPeakTime;
//    }
//
//    public void setStartPeakTime(Date startPeakTime) {
//        this.startPeakTime = startPeakTime;
//    }
//
//    public Date getEndPeakTime() {
//        return endPeakTime;
//    }
//
//    public void setEndPeakTime(Date endPeakTime) {
//        this.endPeakTime = endPeakTime;
//    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    
}
