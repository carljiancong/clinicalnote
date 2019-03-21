package com.harmonycloud.service;

import com.harmonycloud.entity.ClinicalNoteTemplate;
import com.harmonycloud.repository.ClinicalTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalTemplateService {
    private Logger logger = LoggerFactory.getLogger(ClinicalTemplateService.class);


    @Autowired
    private ClinicalTemplateRepository clinicalTemplateRepository;

    /**
     * get clinical template
     *
     * @param clinicId
     * @return
     */
    public List<ClinicalNoteTemplate> getClinicalTemplate(Integer clinicId) {
        List<ClinicalNoteTemplate> clinicalNoteTemplateList = clinicalTemplateRepository.findByClinicId(clinicId);

        return clinicalNoteTemplateList;
    }

}
