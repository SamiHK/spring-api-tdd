package com.samiharoon.apitdd.services;

import com.samiharoon.apitdd.persistence.domain.Sample;
import com.samiharoon.apitdd.persistence.dto.SampleDTO;
import com.samiharoon.apitdd.persistence.request.SampleRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface SampleService {
    List<Sample> getAllSamples(Sort sort);

    List<SampleDTO> crateSamplesList(List<Sample> samples);

    Sample insertSample(SampleRequest sampleRequest);

    Optional<Sample> findById(Long id);

    boolean deleteSample(Optional<Sample> sample);
}
