package com.harmonycloud.vo;

import com.harmonycloud.entity.ClinicalNote;

public class ClinicalNoteVo {
    private ClinicalNote oldClinicalNote;
    private ClinicalNote newClinicalNote;

    public ClinicalNoteVo(ClinicalNote oldClinicalNote, ClinicalNote newClinicalNote) {
        this.oldClinicalNote = oldClinicalNote;
        this.newClinicalNote = newClinicalNote;
    }

    public ClinicalNoteVo() {
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
