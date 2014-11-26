package com.mum.processexceldata;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public  class Service{
    @Id
    private long serviceCode;
    private String serviceName;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date startPeakTime;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date endPeakTime;    
    @OneToOne
    private Country country;
    @OneToMany(mappedBy = "service")
    private List<Rate> rates;

    public Service() {
    }

    public long getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(long serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
    
    
}
