package com.harmonycloud.controller;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.entity.ClinicalNoteTemplate;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.service.ClinicalNoteService;
import com.harmonycloud.service.ClinicalTemplateService;
import com.harmonycloud.dto.ClinicalNoteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Clinical Note")
public class ClinicalNoteController {

    @Autowired
    private ClinicalTemplateService clinicalTemplateService;

    @Autowired
    private ClinicalNoteService clinicalNoteService;


    /**
     * list clinicalnote template
     *
     * @param clinicId
     * @return
     * @throws Exception
     */
    @GetMapping("/listTemplate")
    @ApiOperation(value = "list clinicalnote template", response = ClinicalNoteTemplate.class, httpMethod = "GET")
    @ApiImplicitParam(name = "clinicId", value = "clinicId", paramType = "query", dataType = "Integer")
    public CimsResponseWrapper<List> getClinicalTemplateList(@RequestParam("clinicId") Integer clinicId) throws Exception {
        if (clinicId == null || clinicId <= 0) {
            return new CimsResponseWrapper<>(false, ErrorMsgEnum.PARAMETER_ERROR.getMessage(), null);
        }
        List<ClinicalNoteTemplate> clinicalNoteTemplateList = clinicalTemplateService.getClinicalTemplate(clinicId);
        return new CimsResponseWrapper<>(true, null, clinicalNoteTemplateList);
    }

    /**
     * list this patient clinicalNote
     *
     * @param patientId
     * @return
     * @throws Exception
     */
    @GetMapping("/listClinicNote")
    @ApiOperation(value = "list this patient clinicalNote", response = ClinicalNote.class, httpMethod = "GET")
    @ApiImplicitParam(name = "patientId", value = "PatientId", paramType = "query", dataType = "Integer")
    public CimsResponseWrapper<List> getClinicalNoteList(@RequestParam("patientId") Integer patientId) throws Exception {
        if (patientId == null || patientId <= 0) {
            return new CimsResponseWrapper<>(false, ErrorMsgEnum.PARAMETER_ERROR.getMessage(), null);
        }
        List<ClinicalNote> clinicalNoteList = clinicalNoteService.getClinicalNoteList(patientId);
        return new CimsResponseWrapper<>(true, null, clinicalNoteList);
    }


    /**
     * get ClinicNote by encounterId
     *
     * @param encounterId
     * @return
     * @throws Exception
     */
    @GetMapping("/getClinicalNote")
    @ApiOperation(value = "get ClinicNote by encounterId ", response = ClinicalNote.class, httpMethod = "GET")
    @ApiImplicitParam(name = "encounterId", value = "encounterId", paramType = "query", dataType = "Integer")
    public CimsResponseWrapper<ClinicalNote> getClinicalNote(@RequestParam("encounterId") Integer encounterId) throws Exception {
        if (encounterId == null || encounterId <= 0) {
            return new CimsResponseWrapper<>(false, ErrorMsgEnum.PARAMETER_ERROR.getMessage(), null);
        }

        ClinicalNote clinicalNote = clinicalNoteService.getClinicalNote(encounterId);
        return new CimsResponseWrapper<>(true, null, clinicalNote);
    }

    /**
     * save clinicalNote
     *
     * @param clinicalNote model
     * @return CimsResponseWrapper
     * @throws Exception
     */
    @RequestMapping(path = "/saveClinicalNote", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @Compensable(compensationMethod = "saveClinicalNoteCancel", timeout = 10)
    @Transactional(rollbackFor = Exception.class)
    public CimsResponseWrapper<ClinicalNote> saveClinicalNote(@RequestBody ClinicalNote clinicalNote) throws Exception {
        clinicalNoteService.saveClinicalNote(clinicalNote);
        return new CimsResponseWrapper<>(true, null, null);
    }

    /**
     * save clinicalNote cancel
     *
     * @param clinicalNote model
     */
    @PostMapping(path = "/saveClinicalCancel")
    public void saveClinicalNoteCancel(@RequestBody ClinicalNote clinicalNote) throws Exception {
        clinicalNoteService.saveClinicalNoteCancel(clinicalNote);
    }

    /**
     * updateClinicalNote
     *
     * @param clinicalNoteDto
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/updateClinicalNote", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @Compensable(compensationMethod = "updateClinicalNoteCancel", timeout = 10)
    @Transactional(rollbackFor = Exception.class)
    public CimsResponseWrapper<List> updateClinicalNote(@RequestBody ClinicalNoteDto clinicalNoteDto) throws Exception {
        clinicalNoteService.updateClinicalNote(clinicalNoteDto);
        return new CimsResponseWrapper<>(true, null, null);
    }

    /**
     * update clinicalnotre cancel
     *
     * @param clinicalNoteDto
     */
    @PostMapping(path = "updateClinicalCancel")
    public void updateClinicalNoteCancel(@RequestBody ClinicalNoteDto clinicalNoteDto) throws Exception {
        clinicalNoteService.updateClinicalNoteCancel(clinicalNoteDto);
    }
}
