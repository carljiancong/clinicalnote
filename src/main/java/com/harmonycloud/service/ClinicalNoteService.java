package com.harmonycloud.service;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.repository.ClinicalNoteRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.vo.ClinicalNoteVo;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public Result getClinicalNote(Integer encounterId) {
        ClinicalNote clinicalNote = null;
        try {
            clinicalNote = clinicalNoteRepository.findByEncounterId(encounterId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
        return Result.buildSuccess(clinicalNote);
    }

    @Transactional(rollbackFor = Exception.class)
    @Compensable(compensationMethod = "saveClinicalNoteCancel", timeout = 10)
    public Result saveClinicalNote(ClinicalNote clinicalNote) {
        ClinicalNote clinicalNote1 = null;
        try {
            clinicalNote1 = clinicalNoteRepository.findByEncounterId(clinicalNote.getEncounterId());
            if (clinicalNote1 == null) {
                clinicalNoteRepository.save(clinicalNote);
            } else {
                throw new Exception("The clinical note has been updated by another user");
            }
            return Result.buildSuccess(clinicalNote);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }

    public void saveClinicalNoteCancel(ClinicalNote clinicalNote) {
        ClinicalNote clinicalNote1 = null;
        try {
            clinicalNote1 = clinicalNoteRepository.findByEncounterId(clinicalNote.getEncounterId());
            clinicalNoteRepository.delete(clinicalNote1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Compensable(compensationMethod = "updateClinicalNoteCancel", timeout = 10)
    public Result updateClinicalNote(ClinicalNoteVo clinicalNoteVo) {
        ClinicalNote clinicalNote1 = null;
        try {
            clinicalNote1 = clinicalNoteRepository.findByClinicalNoteId(clinicalNoteVo.getOldClinicalNote().getClinicalNoteId());
            if (clinicalNote1.getNoteContent().equals(clinicalNoteVo.getOldClinicalNote().getNoteContent()) && clinicalNote1.getCreateBy().equals(clinicalNoteVo.getOldClinicalNote().getCreateBy())) {
                clinicalNoteRepository.save(clinicalNoteVo.getNewClinicalNote());
            } else {
                throw new Exception("The clinical note has been updated by another user");
            }
            return Result.buildSuccess(clinicalNoteVo.getNewClinicalNote());
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
    }

    public void updateClinicalNoteCancel(ClinicalNoteVo clinicalNoteVo) {
        ClinicalNote clinicalNote1 = null;
        ClinicalNote clinicalNote = null;
        try {
            clinicalNote1 = clinicalNoteRepository.findByClinicalNoteId(clinicalNoteVo.getOldClinicalNote().getClinicalNoteId());
            clinicalNote = clinicalNoteVo.getOldClinicalNote();
            clinicalNote.setClinicalNoteId(clinicalNote1.getClinicalNoteId());
            clinicalNoteRepository.save(clinicalNote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
