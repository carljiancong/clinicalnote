package com.harmonycloud.vo;

import java.util.Date;

public class ClinicalNoteVo {
    private Integer encounterId;
    private String noteContent;
    private String createBy;
    private Date createDate;
    private Integer patientId;

    public ClinicalNoteVo(Integer encounterId, String noteContent, String createBy, Date createDate, Integer patientId) {
        this.encounterId = encounterId;
        this.noteContent = noteContent;
        this.createBy = createBy;
        this.createDate = createDate;
        this.patientId = patientId;
    }

    public Integer getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Integer encounterId) {
        this.encounterId = encounterId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
