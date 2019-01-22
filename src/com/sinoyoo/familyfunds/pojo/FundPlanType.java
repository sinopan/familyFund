package com.sinoyoo.familyfunds.pojo;

public class FundPlanType {
    private String id;

    private String planTypeName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName == null ? null : planTypeName.trim();
    }
}