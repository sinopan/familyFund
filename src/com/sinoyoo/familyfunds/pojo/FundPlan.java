package com.sinoyoo.familyfunds.pojo;

import java.util.Date;

public class FundPlan {
    private String id;

    private Float amountGoal;

    private Float realizeGoal;

    private Date beginTime;

    private Date endTime;

    private String planType;

    private Integer planState;

    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Float getAmountGoal() {
        return amountGoal;
    }

    public void setAmountGoal(Float amountGoal) {
        this.amountGoal = amountGoal;
    }

    public Float getRealizeGoal() {
        return realizeGoal;
    }

    public void setRealizeGoal(Float realizeGoal) {
        this.realizeGoal = realizeGoal;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType == null ? null : planType.trim();
    }

    public Integer getPlanState() {
        return planState;
    }

    public void setPlanState(Integer planState) {
        this.planState = planState;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}