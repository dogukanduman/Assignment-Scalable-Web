package com.waes.scalableweb.service;

import com.waes.scalableweb.exception.MissingBinaryDataException;
import com.waes.scalableweb.exception.ResourceNotFoundException;

/**
 * @author ttdduman
 */
public interface GetDiffService {

    /**
     * Calculate differences between left and right side version of the base64 binary data.
     * @param id binary data id
     * @return A message which left and right equal or not
     * @throws ResourceNotFoundException throws an exception if given id does not exist
     * @throws MissingBinaryDataException throws an exception if left or right side of the binary data missing
     */
     String getDiff(Long id) throws ResourceNotFoundException,MissingBinaryDataException;
}
