package com.samiharoon.apitdd;

import com.google.gson.Gson;
import com.samiharoon.apitdd.persistence.request.SampleRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ApiTddApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
    }

    @Test
    public void insertSampleTest() throws Exception{

        SampleRequest sampleRequest =  new SampleRequest();
        sampleRequest.setDate("1984-12-31");
        sampleRequest.setFirstName("test");
        sampleRequest.setLastName("it");
        sampleRequest.setPhoneNumber("3876542090");


        mockMvc.perform(post("/endpoint/insert").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(sampleRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.date").value("1984-12-31"))
                .andExpect(jsonPath("$.data.phoneNumber").isNumber())
                .andExpect(jsonPath("$.data.firstName").isString())
                .andExpect(jsonPath("$.data.lastName").isString())
                .andExpect(status().isCreated());
    }

    @Test
    public void insertSampleTestFail() throws Exception {
        mockMvc.perform(post("/endpoint/insert").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public void getAllSamplesTest() throws Exception {

        mockMvc.perform(get("/endpoint/select").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(status().isOk());
    }

    @Test
    public void getSampleByIdTest() throws Exception {
        SampleRequest sampleRequest =  new SampleRequest();
        sampleRequest.setDate("1985-01-01");
        sampleRequest.setFirstName("test");
        sampleRequest.setLastName("it");
        sampleRequest.setPhoneNumber("3876542091");


        mockMvc.perform(post("/endpoint/insert").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(sampleRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(get("/endpoint/select/{id}", "1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.date").value("1984-12-31"))
                .andExpect(jsonPath("$.data.phoneNumber").isNumber())
                .andExpect(jsonPath("$.data.firstName").isString())
                .andExpect(jsonPath("$.data.lastName").isString())
                .andExpect(status().isOk());
    }

    @Test
    public void getSampleByIdTestFail() throws Exception {
        mockMvc.perform(get("/endpoint/select/{id}", "1000"))
                .andDo(print())
                .andExpect(jsonPath("$.data").value("NOT FOUND"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void deleteSampleTest() throws Exception {
        SampleRequest sampleRequest =  new SampleRequest();
        sampleRequest.setDate("1985-01-01");
        sampleRequest.setFirstName("test");
        sampleRequest.setLastName("it");
        sampleRequest.setPhoneNumber("3876542091");


        mockMvc.perform(post("/endpoint/insert").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(sampleRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/endpoint/delete/{id}", "1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.data").value("DELETED"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSampleTestFail() throws Exception {
        mockMvc.perform(delete("/endpoint/delete/{id}", "1000").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.data").value("CANNOT DELETE"))
                .andExpect(status().isNotFound());
    }

}
