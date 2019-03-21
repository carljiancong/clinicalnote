package com.harmonycloud.dto;

import com.harmonycloud.entity.ClinicalNote;

public class ClinicalNoteDto {
    private ClinicalNote newClinicalNote;
    private ClinicalNote oldClinicalNote;

    public ClinicalNoteDto() {
    }

    public ClinicalNoteDto(ClinicalNote newClinicalNote, ClinicalNote oldClinicalNote) {
        this.newClinicalNote = newClinicalNote;
        this.oldClinicalNote = oldClinicalNote;
    }

    public ClinicalNote getNewClinicalNote() {
        return newClinicalNote;
    }

    public void setNewClinicalNote(ClinicalNote newClinicalNote) {
        this.newClinicalNote = newClinicalNote;
    }

    public ClinicalNote getOldClinicalNote() {
        return oldClinicalNote;
    }

    public void setOldClinicalNote(ClinicalNote oldClinicalNote) {
        this.oldClinicalNote = oldClinicalNote;
    }
}
