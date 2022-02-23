package com.samiharoon.apitdd.services.impl;

import com.samiharoon.apitdd.persistence.domain.Sample;
import com.samiharoon.apitdd.persistence.dto.SampleDTO;
import com.samiharoon.apitdd.persistence.repository.SampleRepository;
import com.samiharoon.apitdd.persistence.request.SampleRequest;
import com.samiharoon.apitdd.services.SampleService;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleRepository sampleRepository;

    @Override
    public List<Sample> getAllSamples(Sort sort) {
        return sampleRepository.findAll(sort);
    }

    @Override
    public List<SampleDTO> crateSamplesList(List<Sample> samples) {
        List<SampleDTO> sampleDTOS = new ArrayList<>();

        samples.forEach(sample -> {
            sampleDTOS.add(sample.toSampleDto());
        });

        return sampleDTOS;
    }

    @Override
    public Sample insertSample(SampleRequest sampleRequest) {
        Sample sample = new Sample();
        sample.setDate(Date.valueOf(sampleRequest.getDate()));
        sample.setPhoneNumber(Long.valueOf(sampleRequest.getPhoneNumber()));
        sample.setFirstName(sampleRequest.getFirstName());
        sample.setLastName(sampleRequest.getLastName());

        sample = sampleRepository.save(sample);
        return sample;
    }

    @Override
    public Optional<Sample> findById(Long id) {
        return sampleRepository.findById(id);
    }

    @Override
    public boolean deleteSample(Optional<Sample> sample) {
        sampleRepository.delete(sample.get());
        return true;
    }
}
