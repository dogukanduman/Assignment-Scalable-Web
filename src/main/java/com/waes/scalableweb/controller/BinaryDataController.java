package com.waes.scalableweb.controller;


import com.waes.scalableweb.dto.BinaryDataDto;
import com.waes.scalableweb.entity.BinaryData;
import com.waes.scalableweb.enums.Side;
import com.waes.scalableweb.service.BinaryDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

/**
 * BinaryData Rest controller. It saves binary data with their side.
 *
 * @author ttdduman
 */

@RestController("binaryDataControllerV1")
@RequestMapping("/v1/diff/{id}")
public class BinaryDataController {

    private static final Logger logger = LoggerFactory.getLogger(BinaryDataController.class);

    @Autowired
    BinaryDataService binaryDataService;


    /**
     * Post binary data as left side with id.
     * If data does not exist, create new one.
     * if it is, update existing one.
     *
     * @param id is binary data id.
     * @param binaryDataDto Base64 binary data.
     * @return HttpStatus.CREATED and binaryData location in header if successfully.
     */
    @RequestMapping(value = "/left", method = RequestMethod.POST)
    public ResponseEntity<Void> createLeftBinaryData(@PathVariable Long id, @Valid @RequestBody BinaryDataDto binaryDataDto) {

        logger.debug("createLeftBinaryData is called with id:{},data:{}",id,binaryDataDto);

        BinaryData binaryData = binaryDataService.save(id, binaryDataDto, Side.LEFT);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(binaryData.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    /**
     * Post binary data as right side with id.
     * If data does not exist, create new one.
     * if it is, update existing one.
     *
     * @param id is binary data id.
     * @param binaryDataDto Base64 binary data.
     * @return HttpStatus.CREATED and binaryData location in header if successfully.
     */
    @RequestMapping(value = "/right", method = RequestMethod.POST)
    public ResponseEntity<Void> createRightBinaryData(@PathVariable Long id, @Valid @RequestBody BinaryDataDto binaryDataDto) {

        logger.debug("createRightBinaryData is called with id:{},data:{}",id,binaryDataDto);

        BinaryData binaryData = binaryDataService.save(id, binaryDataDto, Side.RIGHT);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(binaryData.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Get binary data with given id and side .
     *
     * @param id is binary data id.
     * @param side side of data
     * @return HttpStatus.OK and binaryData .
     */
    @RequestMapping(value = "/{side}", method = RequestMethod.GET)
    public ResponseEntity<String> getBySide(@PathVariable Long id, @PathVariable String side) {
        logger.debug("getBySide is called with id:{},side:{}",id,side);
        String data = binaryDataService.getBySide(id, getSide(side));

        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<String>(data, HttpStatus.OK);
    }

    private Side getSide(String side) throws IllegalArgumentException {
        logger.debug("Validating side:{}",side);
        if (!side.equalsIgnoreCase("left") && !side.equalsIgnoreCase("right")) {
            logger.error("Error occured, No such a side argument as {} ",side);
            throw new IllegalArgumentException("No such a side argument as " + side);
        }

        return side.equals(Side.LEFT.getValue()) ? Side.LEFT : Side.RIGHT;

    }


}
