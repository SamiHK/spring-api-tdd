package com.samiharoon.apitdd.controllers;

import com.samiharoon.apitdd.persistence.domain.Sample;
import com.samiharoon.apitdd.persistence.request.SampleRequest;
import com.samiharoon.apitdd.persistence.response.GenericResponseEntity;
import com.samiharoon.apitdd.persistence.utils.StatusCodes;
import com.samiharoon.apitdd.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endpoint")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/select")
    public ResponseEntity<?> getAllSamples() throws Exception {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            List<Sample> samples = sampleService.getAllSamples(sort);
            ResponseEntity response = GenericResponseEntity.create(StatusCodes.SUCCESS, sampleService.createSamplesList(samples), HttpStatus.OK);
            return response;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/select/{id}")
    public ResponseEntity<?> getSampleById(@PathVariable Long id) throws Exception {
        try {
            Optional<Sample> sample = sampleService.findById(id);
            ResponseEntity response;

            if(sample.isPresent()){
                response = GenericResponseEntity.create(StatusCodes.SUCCESS, sample.get().toSampleDto(), HttpStatus.OK);
            } else {
                response = GenericResponseEntity.create(StatusCodes.NOT_FOUND, "NOT FOUND", HttpStatus.NOT_FOUND);
            }

            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }


    @PostMapping("/insert")
    public ResponseEntity<?> insertSample(@Valid @RequestBody SampleRequest sampleRequest) throws Exception {
        try {
            Sample sample = sampleService.insertSample(sampleRequest);
            return GenericResponseEntity.create(StatusCodes.CREATED, sample.toSampleDto(), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSample(@PathVariable Long id) throws Exception {
        try {
            Optional<Sample> sample = sampleService.findById(id);

            ResponseEntity response;

            if(sample.isPresent()){
                sampleService.deleteSample(sample);
                response = GenericResponseEntity.create(StatusCodes.SUCCESS, "DELETED", HttpStatus.OK);
            } else {
                response = GenericResponseEntity.create(StatusCodes.NOT_FOUND, "CANNOT DELETE", HttpStatus.NOT_FOUND);
            }

            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

}
