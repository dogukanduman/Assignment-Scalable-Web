package com.waes.scalableweb.service.imp;

import com.waes.scalableweb.dto.BinaryDataDto;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.enums.Side;
import com.waes.scalableweb.exception.MissingBinaryDataException;
import com.waes.scalableweb.exception.ResourceNotFoundException;
import com.waes.scalableweb.repository.BinaryDataRepository;
import com.waes.scalableweb.service.BinaryDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ttdduman
 */
@Service
public class BinaryDataServiceImp implements BinaryDataService {

    private static final Logger logger = LoggerFactory.getLogger(BinaryDataServiceImp.class);

    @Autowired
    BinaryDataRepository binaryDataRepository;

    @Override
    public BinaryData save(Long id, BinaryDataDto binaryDataDto, Side side) {


        Optional<BinaryData> binaryData = get(id);

        BinaryData entity = null;

        if (!binaryData.isPresent()) {
            logger.debug("Binary data will be created with id:{},side:{},data:{}",id,side,binaryDataDto);
            entity = new BinaryData();
            entity.setId(id);
        } else {
            logger.debug("Binary data with id:{} is exist",id);
            logger.debug("It will be updated",id);
            entity = binaryData.get();
        }

        if (side.equals(Side.LEFT)) {
            entity.setLeft(binaryDataDto.getData());
        } else {
            entity.setRight(binaryDataDto.getData());
        }

        return binaryDataRepository.save(entity);

    }

    @Override
    public Optional<BinaryData> get(Long id) {
        logger.debug("Get Binary data with id:{}",id);
        return binaryDataRepository.findById(id);
    }

    @Override
    public String getBySide(Long id, Side side) throws ResourceNotFoundException, MissingBinaryDataException{


        BinaryData binaryData = get(id).orElseThrow(()
                -> ResourceNotFoundException.build(id));

        logger.debug("Binary data with id:{} is exist",id);

        String message = "";

        if (side.equals(Side.LEFT)) {
            message = binaryData.getLeft();
        } else {
            message = binaryData.getRight();
        }

        if (message.isEmpty()) {
            logger.error("Error occured, data not found in given side {} ",side);
            throw MissingBinaryDataException.build(id, side);
        }

        return message;
    }
}
