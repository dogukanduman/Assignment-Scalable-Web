package com.waes.scalableweb.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.scalableweb.dto.BinaryDataDto;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.repository.BinaryDataRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BinaryDataControllerIT {


    @Autowired
    public BinaryDataRepository binaryDataRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private String testData1;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.binaryDataRepository.deleteAll();

        ObjectMapper mapper = new ObjectMapper();
        testData1 = mapper.writeValueAsString(new BinaryDataDto("AbtMVA=="));
    }

    /**
     * Validate if left endpoint is working and persisting the object
     *
     * @throws Exception
     */
    @Test
    public void createLeftBinaryDataTest() throws Exception {


        mvc.perform(MockMvcRequestBuilders.post("/v1/diff/1/left").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(testData1))
                .andExpect(status().isCreated())
                .andReturn();


        BinaryData binaryData = binaryDataRepository.findById(1L).get();
        Assert.assertThat(binaryData.getId(), Matchers.is(1L));
        Assert.assertThat(binaryData.getLeft(), Matchers.is("AbtMVA=="));
        Assert.assertThat(binaryData.getRight(), Matchers.isEmptyOrNullString());
    }

    @Test
    public void createRightBinaryDataTest() throws Exception {


        mvc.perform(MockMvcRequestBuilders.post("/v1/diff/2/right").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(testData1))
                .andExpect(status().isCreated())
                .andReturn();


        BinaryData binaryData = binaryDataRepository.findById(2L).get();
        Assert.assertThat(binaryData.getId(), Matchers.is(2L));
        Assert.assertThat(binaryData.getRight(), Matchers.is("AbtMVA=="));
        Assert.assertThat(binaryData.getLeft(), Matchers.isEmptyOrNullString());
    }

    @Test
    public void getBySideTest() throws Exception {


        mvc.perform(MockMvcRequestBuilders.post("/v1/diff/2/right").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(testData1))
                .andExpect(status().isCreated())
                .andReturn();


        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/v1/diff/2/right").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assert.assertThat(content, Matchers.is("AbtMVA=="));
    }


}
