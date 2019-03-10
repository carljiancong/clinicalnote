package com.harmonycloud.service;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.repository.ClinicalNoteRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.bo.ClinicalNoteBo;
//import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
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

    public Result saveClinicalNote(ClinicalNote clinicalNote) throws Exception {
        ClinicalNote clinicalNote1 = null;
        try {

            clinicalNote1 = clinicalNoteRepository.findByEncounterId(clinicalNote.getEncounterId());
            clinicalNoteRepository.save(clinicalNote);
        } catch (Exception e) {
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }
        return Result.buildSuccess(clinicalNote);
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

    public Result updateClinicalNote(ClinicalNoteBo clinicalNoteBo) throws Exception {

        ClinicalNote clinicalNote = clinicalNoteBo.getNewClinicalNote();
        try {
            clinicalNote.setClinicalNoteId(clinicalNoteBo.getOldClinicalNote().getClinicalNoteId());
            clinicalNoteRepository.save(clinicalNote);
        } catch (Exception e) {
            return Result.buildError(CodeMsg.SERVICE_ERROR);
        }

        return Result.buildSuccess(null);
    }

    public void updateClinicalNoteCancel(ClinicalNoteBo clinicalNoteDto) {
        try {
            ClinicalNote clinicalNote = clinicalNoteDto.getOldClinicalNote();
            clinicalNoteRepository.save(clinicalNote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
