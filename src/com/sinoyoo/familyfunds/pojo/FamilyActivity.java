package com.sinoyoo.familyfunds.pojo;

import java.util.Date;

public class FamilyActivity {
    private String id;

    private Date beginTime;

    private Date endTime;

    private String content;

    private String planer;

    private String participants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPlaner() {
        return planer;
    }

    public void setPlaner(String planer) {
        this.planer = planer == null ? null : planer.trim();
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants == null ? null : participants.trim();
    }
}