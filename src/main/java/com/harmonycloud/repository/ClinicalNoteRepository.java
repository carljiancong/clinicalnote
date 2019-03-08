package com.harmonycloud.repository;

import com.harmonycloud.entity.ClinicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClinicalNoteRepository extends JpaRepository<ClinicalNote, Integer> {

    List<ClinicalNote> findByPatientId(Integer patientId);

    ClinicalNote findByEncounterId(Integer encounterId);

    @Query(nativeQuery = true, value = "update \"clinical_note\" set \"note_content\"=1? and \"create_by\" = 2? and \"create_date\"=3?\n" +
            "where \"note_content\"=4? and \"create_by\" =5?")
    ClinicalNote update(String newNote, String newUserName, Date date,String oldNote,String oldUSerName);
}
