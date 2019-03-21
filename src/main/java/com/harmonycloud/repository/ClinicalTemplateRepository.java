package com.harmonycloud.repository;

import com.harmonycloud.entity.ClinicalNoteTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalTemplateRepository extends JpaRepository<ClinicalNoteTemplate, Integer> {
    List<ClinicalNoteTemplate> findByClinicId(Integer clinicId);
}
