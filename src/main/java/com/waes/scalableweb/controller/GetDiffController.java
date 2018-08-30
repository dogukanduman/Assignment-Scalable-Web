package com.waes.scalableweb.controller;

import com.waes.scalableweb.service.GetDiffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * GetDiff Rest controller. It compares left and right side of the binary data.
 *
 * @author ttdduman
 */

@RestController("getDiffControllerV1")
@RequestMapping("/v1/diff/{id}")
public class GetDiffController {

    private static final Logger logger = LoggerFactory.getLogger(GetDiffController.class);

    @Autowired
    GetDiffService getDiffService;

    /**
     * It compares left and right side of the binary data.
     *
     * @param id is binary data id.
     * @return HttpStatus.OK and comparison result.
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDiff(@PathVariable Long id) {

        logger.debug("getDiff is called with id:{}",id);

        String message = getDiffService.getDiff(id);

        return new ResponseEntity<String>(message, HttpStatus.OK);
    }


}
