package com.waes.scalableweb.service;

import com.waes.scalableweb.dto.BinaryDataDto;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.enums.Side;
import com.waes.scalableweb.exception.MissingBinaryDataException;
import com.waes.scalableweb.exception.ResourceNotFoundException;

import java.util.Optional;

/**
 * @author ttdduman
 */
public interface BinaryDataService {

    /**
     * Save binary data to the repository
     * @param id data id
     * @param dto is BinaryDataDto which carries based64 data
     * @param side which holds left or right
     * @return new BinaryData
     */

     BinaryData save(Long id, BinaryDataDto dto, Side side);

    /**
     * Get Binary Data object from repository
     * @param id
     * @return BinaryData
     */
     Optional<BinaryData> get(Long id);


    /**
     * Get Binary Data which is related to given side
     * @param id
     * @param side
     * @return
     * @throws ResourceNotFoundException throws an exception if given id does not exist
     * @throws MissingBinaryDataException throws an exception if left or right side of the binary data missing
     */
     String getBySide(Long id,Side side) throws ResourceNotFoundException, MissingBinaryDataException;

}
