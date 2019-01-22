package com.sinoyoo.familyfunds.pojo;

import java.util.Date;

public class DepositeWithdraw {
    private String id;

    private Float exchangeAmount;

    private Date exchangeTime;

    private String exchangePlace;

    private String exchangeDeatil;

    private Integer exchangeType;

    private String userId;

    private String fundPlanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Float getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(Float exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getExchangePlace() {
        return exchangePlace;
    }

    public void setExchangePlace(String exchangePlace) {
        this.exchangePlace = exchangePlace == null ? null : exchangePlace.trim();
    }

    public String getExchangeDeatil() {
        return exchangeDeatil;
    }

    public void setExchangeDeatil(String exchangeDeatil) {
        this.exchangeDeatil = exchangeDeatil == null ? null : exchangeDeatil.trim();
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFundPlanId() {
        return fundPlanId;
    }

    public void setFundPlanId(String fundPlanId) {
        this.fundPlanId = fundPlanId == null ? null : fundPlanId.trim();
    }
}