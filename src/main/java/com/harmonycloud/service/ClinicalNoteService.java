package com.harmonycloud.service;

import com.harmonycloud.bo.UserPrincipal;
import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.ClinicalnoteException;
import com.harmonycloud.repository.ClinicalNoteRepository;
import com.harmonycloud.dto.ClinicalNoteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClinicalNoteService {
    private Logger logger = LoggerFactory.getLogger(ClinicalTemplateService.class);


    @Autowired
    private ClinicalNoteRepository clinicalNoteRepository;

    /**
     * get clinical note list
     *
     * @param patientId
     * @return
     * @throws ClinicalnoteException
     */
    public List<ClinicalNote> getClinicalNoteList(Integer patientId) throws ClinicalnoteException {
        List<ClinicalNote> clinicalNoteList = clinicalNoteRepository.findByPatientId(patientId);

        return clinicalNoteList;
    }


    /**
     * get clinical note
     *
     * @param encounterId
     * @return
     * @throws ClinicalnoteException
     */
    public ClinicalNote getClinicalNote(Integer encounterId) throws ClinicalnoteException {
        ClinicalNote clinicalNote = clinicalNoteRepository.findByEncounterId(encounterId);
        return clinicalNote;
    }

    /**
     * save clinical note
     *
     * @param clinicalNote
     * @return
     * @throws Exception
     */
    public boolean saveClinicalNote(ClinicalNote clinicalNote) throws Exception {

        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        clinicalNote.setCreateBy(userDetails.getUsername());
        clinicalNote.setCreateDate(new Date());
        clinicalNoteRepository.save(clinicalNote);

        return true;
    }

    /**
     * save clinical note cancel
     *
     * @param clinicalNote
     * @throws Exception
     */
    public void saveClinicalNoteCancel(ClinicalNote clinicalNote) throws Exception {
        ClinicalNote oldClinicalNote = null;

        oldClinicalNote = clinicalNoteRepository.findByEncounterId(clinicalNote.getEncounterId());
        clinicalNoteRepository.delete(oldClinicalNote);

    }

    /**
     * update clinical note
     *
     * @param clinicalNoteDto
     * @return
     * @throws Exception
     */
    public boolean updateClinicalNote(ClinicalNoteDto clinicalNoteDto) throws Exception {
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        ClinicalNote clinicalNote = clinicalNoteDto.getNewClinicalNote();
        clinicalNote.setCreateBy(userDetails.getUsername());
        clinicalNote.setCreateDate(new Date());

        clinicalNote.setClinicalNoteId(clinicalNoteDto.getOldClinicalNote().getClinicalNoteId());
        clinicalNoteRepository.save(clinicalNote);

        return true;
    }

    /**
     * update clinical note cancel
     *
     * @param clinicalNoteDto
     * @throws Exception
     */
    public void updateClinicalNoteCancel(ClinicalNoteDto clinicalNoteDto) throws Exception {

        ClinicalNote clinicalNote = clinicalNoteDto.getOldClinicalNote();
        clinicalNoteRepository.save(clinicalNote);

    }

}
