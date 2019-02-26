package com.harmonycloud.service;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.repository.ClinicalNoteRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.vo.ClinicalNoteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalNoteService {
    private Logger logger = LoggerFactory.getLogger(ClinicalTemplateService.class);


    @Autowired
    private ClinicalNoteRepository clinicalNoteRepository;

    public Result getClinicalNoteList(Integer patientId) {
        List<ClinicalNote> clinicalNoteList = null;
        try {
            clinicalNoteList = clinicalNoteRepository.findByPatientId(patientId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
        return Result.buildSuccess(clinicalNoteList);
    }

    public Result saveClinicalNote(ClinicalNoteVo clinicalNoteVo){
        try{
            ClinicalNote clinicalNote = new ClinicalNote(clinicalNoteVo.getEncounterId(),clinicalNoteVo.getNoteContent(),
                    clinicalNoteVo.getCreateBy(),clinicalNoteVo.getCreateDate());
            clinicalNoteRepository.save(clinicalNote);
            return Result.buildSuccess(clinicalNote);
        }catch (Exception e){
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }
}
