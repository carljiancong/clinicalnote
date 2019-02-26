package com.harmonycloud.service;

import com.harmonycloud.entity.ClinicalNoteTemplate;
import com.harmonycloud.repository.ClinicalTemplateRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
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

    public Result getClinicalTemplate(Integer clinicId) {
        List<ClinicalNoteTemplate> clinicalNoteTemplateList = null;
        try {
            clinicalNoteTemplateList=clinicalTemplateRepository.findByClinicId(clinicId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
        return Result.buildSuccess(clinicalNoteTemplateList);
    }

}
