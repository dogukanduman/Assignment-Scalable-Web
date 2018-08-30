package com.waes.scalableweb.service;

import com.waes.scalableweb.constant.Message;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.exception.MissingBinaryDataException;
import com.waes.scalableweb.exception.ResourceNotFoundException;
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
public class GetDiffServiceTest {


    @Autowired
    public BinaryDataRepository binaryDataRepository;

    @Autowired
    public GetDiffService getDiffService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    private String testData1;
    private String testData2;
    private String testData3;


    @Before
    public void setup() throws Exception {
        this.binaryDataRepository.deleteAll();

        testData1 = "AbtMVA==";
        testData2 = "ActxVA==";
        testData3 = "aaActxVA==";
    }


    @Test(expected = ResourceNotFoundException.class)
    public void ResourceNotFoundExceptionTest() {
        getDiffService.getDiff(1L);
    }


    @Test(expected = MissingBinaryDataException.class)
    public void MissingBinaryDataExceptionTest() {

        binaryDataRepository.save(new BinaryData(1l, testData1, ""));
        getDiffService.getDiff(1L);
    }

    @Test
    public void getDiffEqualTest() throws Exception {

        binaryDataRepository.save(new BinaryData(1L, testData1, testData1));
        String message = getDiffService.getDiff(1L);
        Assert.assertThat(message, Matchers.is(Message.DATA_IS_EQUAL));
    }

    @Test
    public void getDiffOffSetTest() throws Exception {

        binaryDataRepository.save(new BinaryData(1L, testData1, testData2));
        String message = getDiffService.getDiff(1L);


        /*AbtMVA==
         *ActxVA==
         * 1 3
         */

        String offsets = "1,3";

        String dataIsEqualNotSame = String.format(Message.DATA_IS_EQUAL_NOT_SAME, offsets);

        Assert.assertThat(message, Matchers.is(dataIsEqualNotSame));
    }

    @Test
    public void getDiffSizeTest() throws Exception {

        binaryDataRepository.save(new BinaryData(1L, testData1, testData3));
        String message = getDiffService.getDiff(1L);
        Assert.assertThat(message, Matchers.is(Message.DATA_IS_NOT_EQUAL));
    }

}