package com.harmonycloud.dto;

import com.harmonycloud.entity.ClinicalNote;

public class ClinicalNoteDto {
    private ClinicalNote oldClinicalNote;
    private ClinicalNote newClinicalNote;

    public ClinicalNoteDto(ClinicalNote oldClinicalNote, ClinicalNote newClinicalNote) {
        this.oldClinicalNote = oldClinicalNote;
        this.newClinicalNote = newClinicalNote;
    }

    public ClinicalNoteDto() {
    }

    public ClinicalNote getOldClinicalNote() {
        return oldClinicalNote;
    }

    public void setOldClinicalNote(ClinicalNote oldClinicalNote) {
        this.oldClinicalNote = oldClinicalNote;
    }

    public ClinicalNote getNewClinicalNote() {
        return newClinicalNote;
    }

    public void setNewClinicalNote(ClinicalNote newClinicalNote) {
        this.newClinicalNote = newClinicalNote;
    }
}
