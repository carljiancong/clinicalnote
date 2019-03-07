package com.harmonycloud.controller;

import com.harmonycloud.entity.ClinicalNote;
import com.harmonycloud.entity.ClinicalNoteTemplate;
import com.harmonycloud.result.CodeMsg;
import com.harmonycloud.result.Result;
import com.harmonycloud.service.ClinicalNoteService;
import com.harmonycloud.service.ClinicalTemplateService;
import com.harmonycloud.vo.ClinicalNoteVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Clinical Note")
public class ClinicalNoteController {
    @Autowired
    private ClinicalTemplateService clinicalTemplateService;

    @Autowired
    private ClinicalNoteService clinicalNoteService;

    @GetMapping("/listTemplate")
    @ApiOperation(value = "template", response = ClinicalNoteTemplate.class, httpMethod = "GET")
    @ApiImplicitParam(name = "clinicId", value = "clinicId", paramType = "query", dataType = "Integer")
    public Result getClinicalTemplateList(@RequestParam("clinicId") Integer clinicId){
        if(clinicId <=0){
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return clinicalTemplateService.getClinicalTemplate(clinicId);
    }

    @GetMapping("/listClinicNote")
    @ApiOperation(value = "ClinicNote", response = ClinicalNote.class, httpMethod = "GET")
    @ApiImplicitParam(name = "patientId", value = "PatientId", paramType = "query", dataType = "Integer")
    public Result getClinicalNoteList(@RequestParam("patientId") Integer patientId){
        if(patientId <=0){
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return clinicalNoteService.getClinicalNoteList(patientId);
    }

    @RequestMapping(path = "/saveClinicalNote", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Result saveClinicalNote(@RequestBody ClinicalNote clinicalNote){
        return clinicalNoteService.saveClinicalNote(clinicalNote);
    }
    @RequestMapping(path = "/updateClinicalNote", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Result updateClinicalNote(@RequestBody ClinicalNoteVo clinicalNoteVo){
        return clinicalNoteService.updateClinicalNote(clinicalNoteVo);
    }


    @GetMapping("/listClinicNote")
    @ApiOperation(value = "ClinicNote in this encounter ", response = ClinicalNote.class, httpMethod = "GET")
    @ApiImplicitParam(name = "encounterId", value = "encounterId", paramType = "query", dataType = "Integer")
    public Result getClinicalNote(@RequestParam("encounterId") Integer encounterId){
        if(encounterId <=0){
            return Result.buildError(CodeMsg.PARAMETER_ERROR);
        }
        return clinicalNoteService.getClinicalNote(encounterId);
    }
}
