package com.waes.scalableweb.service;

import com.waes.scalableweb.dto.BinaryDataDto;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.enums.Side;
import com.waes.scalableweb.repository.BinaryDataRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BinaryDataServiceTest {


    @Autowired
    public BinaryDataRepository binaryDataRepository;

    @Autowired
    public BinaryDataService binaryDataService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    private String testData1;
    private String testData2;
    private String testData3;
    private String testData4;

    @Before
    public void setup() throws Exception {
        this.binaryDataRepository.deleteAll();

        testData1 = "AbtMVA==";
        testData2 = "ActxVA==";
        testData3 = "aaActxVA==";
    }


    @Test
    public void saveLeft() throws Exception {

        binaryDataService.save(1L, new BinaryDataDto(testData1), Side.LEFT);
        BinaryData binaryData = binaryDataRepository.findById(1L).get();

        Assert.assertThat(binaryData.getId(), Matchers.is(1L));
        Assert.assertThat(binaryData.getLeft(), Matchers.is(testData1));
        Assert.assertThat(binaryData.getRight(), Matchers.isEmptyOrNullString());
    }

    @Test
    public void saveRight() throws Exception {

        binaryDataService.save(2L, new BinaryDataDto(testData2), Side.RIGHT);
        BinaryData binaryData = binaryDataRepository.findById(2L).get();

        Assert.assertThat(binaryData.getId(), Matchers.is(2L));
        Assert.assertThat(binaryData.getRight(), Matchers.is(testData2));
        Assert.assertThat(binaryData.getLeft(), Matchers.isEmptyOrNullString());
    }
}