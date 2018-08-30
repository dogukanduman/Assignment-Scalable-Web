package com.waes.scalableweb.repository;

import com.waes.scalableweb.entity.BinaryData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ttdduman
 */
@Repository
public interface BinaryDataRepository extends CrudRepository<BinaryData, Long> {

}