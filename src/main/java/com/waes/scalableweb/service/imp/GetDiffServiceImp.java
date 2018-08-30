package com.waes.scalableweb.service.imp;

import com.waes.scalableweb.constant.Message;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.enums.Side;
import com.waes.scalableweb.exception.MissingBinaryDataException;
import com.waes.scalableweb.exception.ResourceNotFoundException;
import com.waes.scalableweb.service.BinaryDataService;
import com.waes.scalableweb.service.GetDiffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ttdduman
 */
@Service
public class GetDiffServiceImp implements GetDiffService {

    private static final Logger logger = LoggerFactory.getLogger(GetDiffServiceImp.class);

    @Autowired
    BinaryDataService binaryDataService;

    public String getDiff(Long id) throws ResourceNotFoundException, MissingBinaryDataException {

        BinaryData binaryData = binaryDataService.get(id).orElseThrow(()
                -> ResourceNotFoundException.build(id));

        logger.debug("Binary data with id:{} is exist",id);
        /**Check left side of the data is empty*/

        logger.debug("Checking sides of Binary data");
        if ((binaryData.getLeft()==null)||(binaryData.getLeft().isEmpty())) {
            logger.error("Error occured, left side of the data is empty, can't compare");
            throw MissingBinaryDataException.build(id, Side.LEFT);
        }
        logger.debug("Left side is OK");

        /**Check right side of the data is empty*/
        if ((binaryData.getRight()==null)||(binaryData.getRight().isEmpty())) {
            logger.error("Error occured, right side of the data is empty, can't compare");
            throw MissingBinaryDataException.build(id, Side.RIGHT);
        }
        logger.debug("Right side is OK");

        byte[] sideLeft = binaryData.getLeft().getBytes();
        byte[] sideRight = binaryData.getRight().getBytes();

        /**Check lengths of left and  right sides*/
        if (sideLeft.length != sideRight.length) {
            logger.debug(Message.DATA_IS_NOT_EQUAL);
            return Message.DATA_IS_NOT_EQUAL;
        }

        /**
         * XOR left and right side data byte byte
         * If XOR result at i is not equal to 0, there is a difference at i.
         * Differences are added to offSetList for keeping.
         */
        int length = sideLeft.length;
        List<Integer> offSetList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if ((sideLeft[i] ^ sideRight[i]) != 0) {
                offSetList.add(i);
            }
        }

        if (offSetList.isEmpty()) {
            logger.debug(Message.DATA_IS_EQUAL);
            return Message.DATA_IS_EQUAL;
        }

        /**Concat for offsetlist*/
        String offsets = offSetList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        logger.debug(String.format(Message.DATA_IS_EQUAL_NOT_SAME, offsets));
        return String.format(Message.DATA_IS_EQUAL_NOT_SAME, offsets);

    }


}
