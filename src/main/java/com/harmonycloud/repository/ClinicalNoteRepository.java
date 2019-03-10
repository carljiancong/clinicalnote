package com.harmonycloud.repository;

import com.harmonycloud.entity.ClinicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClinicalNoteRepository extends JpaRepository<ClinicalNote, Integer> {

    List<ClinicalNote> findByPatientId(Integer patientId);

    ClinicalNote findByEncounterId(Integer encounterId);
}
