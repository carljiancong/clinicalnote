package com.harmonycloud.controller;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.entity.ClinicalNoteTemplate;
import com.harmonycloud.repository.ClinicalNoteRepository;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.ClinicalNoteService;
import com.harmonycloud.service.ClinicalTemplateService;
import com.harmonycloud.bo.ClinicalNoteBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Clinical Note")
public class ClinicalNoteController {
    @Autowired
    private ClinicalTemplateService clinicalTemplateService;

    @Autowired
    private ClinicalNoteService clinicalNoteService;

    @Autowired
    private ClinicalNoteRepository clinicalNoteRepository;

    @GetMapping("/listTemplate")
    @ApiOperation(value = "list clinicalnote template", response = ClinicalNoteTemplate.class, httpMethod = "GET")
    @ApiImplicitParam(name = "clinicId", value = "clinicId", paramType = "query", dataType = "Integer")
    public Result getClinicalTemplateList(@RequestParam("clinicId") Integer clinicId) {
        if (clinicId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return clinicalTemplateService.getClinicalTemplate(clinicId);
    }

    @GetMapping("/listClinicNote")
    @ApiOperation(value = "list this patient clinicalNote", response = ClinicalNote.class, httpMethod = "GET")
    @ApiImplicitParam(name = "patientId", value = "PatientId", paramType = "query", dataType = "Integer")
    public Result getClinicalNoteList(@RequestParam("patientId") Integer patientId) {
        if (patientId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return clinicalNoteService.getClinicalNoteList(patientId);
    }

    @GetMapping("/getClinicalNote")
    @ApiOperation(value = "get ClinicNote by encounterId ", response = ClinicalNote.class, httpMethod = "GET")
    @ApiImplicitParam(name = "encounterId", value = "encounterId", paramType = "query", dataType = "Integer")
    public Result getClinicalNote(@RequestParam("encounterId") Integer encounterId) {
        if (encounterId <= 0) {
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return clinicalNoteService.getClinicalNote(encounterId);
    }

    @RequestMapping(path = "/saveClinicalNote", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @Compensable(compensationMethod = "saveClinicalNoteCancel", timeout = 10)
    @Transactional(rollbackFor = Exception.class)
    public Result saveClinicalNote(@RequestBody ClinicalNote clinicalNote) throws Exception {
        try {
            return clinicalNoteService.saveClinicalNote(clinicalNote);
        } catch (Exception e) {
            throw e;
        }
    }

    public void saveClinicalNoteCancel(ClinicalNote clinicalNote) {
        try {
            clinicalNoteService.saveClinicalNoteCancel(clinicalNote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/updateClinicalNote", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @Compensable(compensationMethod = "updateClinicalNoteCancel", timeout = 10)
    @Transactional(rollbackFor = Exception.class)
    public Result updateClinicalNote(@RequestBody ClinicalNoteBo clinicalNoteBo) throws Exception {
        try {
            return clinicalNoteService.updateClinicalNote(clinicalNoteBo);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateClinicalNoteCancel(ClinicalNoteBo clinicalNoteDto) {
        try {
            clinicalNoteService.updateClinicalNoteCancel(clinicalNoteDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
