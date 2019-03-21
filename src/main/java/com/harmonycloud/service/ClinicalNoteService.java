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
     * @throws ClinicalnoteException
     */
    public boolean saveClinicalNote(ClinicalNote clinicalNote) throws ClinicalnoteException {
        try {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            clinicalNote.setCreateBy(userDetails.getUsername());
            clinicalNote.setCreateDate(new Date());
            clinicalNoteRepository.save(clinicalNote);
        } catch (Exception e) {
            throw new ClinicalnoteException(ErrorMsgEnum.SAVE_ERROR.getMessage());
        }
        return true;
    }

    /**
     * save clinical note cancel
     *
     * @param clinicalNote
     * @throws ClinicalnoteException
     */
    public void saveClinicalNoteCancel(ClinicalNote clinicalNote) throws ClinicalnoteException {
        ClinicalNote oldClinicalNote = null;
        try {
            oldClinicalNote = clinicalNoteRepository.findByEncounterId(clinicalNote.getEncounterId());
            clinicalNoteRepository.delete(oldClinicalNote);
        } catch (Exception e) {
            throw new ClinicalnoteException(ErrorMsgEnum.SAVE_ERROR.getMessage());
        }
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
     * @throws ClinicalnoteException
     */
    public void updateClinicalNoteCancel(ClinicalNoteDto clinicalNoteDto) throws ClinicalnoteException {
        try {
            ClinicalNote clinicalNote = clinicalNoteDto.getOldClinicalNote();
            clinicalNoteRepository.save(clinicalNote);
        } catch (Exception e) {
            throw new ClinicalnoteException(ErrorMsgEnum.UPDATE_ERROR.getMessage());
        }
    }

}
