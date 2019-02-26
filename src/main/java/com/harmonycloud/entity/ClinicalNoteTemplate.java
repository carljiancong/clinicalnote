package com.harmonycloud.entity;



public class ClinicalNoteTemplate {
    private Integer clinicalNoteTemplateId;
    private String templateName;
    private String templateContent;
    private Integer clinicId;


    public ClinicalNoteTemplate() {
    }

    public ClinicalNoteTemplate(Integer clinicalNoteTemplateId, String templateName, String templateContent, Integer clinicId) {
        this.clinicalNoteTemplateId = clinicalNoteTemplateId;
        this.templateName = templateName;
        this.templateContent = templateContent;
        this.clinicId = clinicId;

    }

    public Integer getClinicalNoteTemplateId() {
        return clinicalNoteTemplateId;
    }

    public void setClinicalNoteTemplateId(Integer clinicalNoteTemplateId) {
        this.clinicalNoteTemplateId = clinicalNoteTemplateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }
}
