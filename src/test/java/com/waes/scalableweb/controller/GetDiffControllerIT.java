package com.waes.scalableweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.scalableweb.constant.Message;
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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GetDiffControllerIT {

    @Autowired
    public BinaryDataRepository binaryDataRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private String testData1;

    @Before
    public void setup() throws Exception {
        this.mvc = webAppContextSetup(webApplicationContext).build();
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
    public void getDiffDataTest() throws Exception {


        binaryDataRepository.save(new BinaryData(1l, testData1, testData1));

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/v1/diff/1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.assertThat(content, Matchers.is(Message.DATA_IS_EQUAL));
    }

}
