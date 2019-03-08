package com.harmonycloud.service;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.repository.ClinicalNoteRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.dto.ClinicalNoteDto;
//import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
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

    public Result saveClinicalNote(ClinicalNote clinicalNote) throws Exception {
        ClinicalNote clinicalNote1 = null;

        clinicalNote1 = clinicalNoteRepository.findByEncounterId(clinicalNote.getEncounterId());
        if (clinicalNote1 == null) {
            clinicalNoteRepository.save(clinicalNote);
        } else {
            throw new Exception("The clinical note has been updated by another user");
        }

        return Result.buildSuccess(clinicalNote);
    }


    public Result updateClinicalNote(ClinicalNoteDto clinicalNoteDto) throws Exception {
        ClinicalNote clinicalNote1 = null;
        try {
            clinicalNoteRepository.update(clinicalNoteDto.getNewClinicalNote().getNoteContent(), clinicalNoteDto.getNewClinicalNote().getCreateBy(),
                    clinicalNoteDto.getNewClinicalNote().getCreateDate(), clinicalNoteDto.getOldClinicalNote().getNoteContent(), clinicalNoteDto.getOldClinicalNote().getCreateBy());

            return Result.buildSuccess(clinicalNoteDto.getNewClinicalNote());
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new Exception("The clinical note has been updated by another user");
        }
    }

}
