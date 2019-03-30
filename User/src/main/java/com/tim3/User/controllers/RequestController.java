package com.tim3.User.controllers;

import com.tim3.User.models.Request;
import com.tim3.User.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<Request>> getAllRequests(){
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/{id}")
    private ResponseEntity<Request> getRequestById(@PathVariable Integer id){
        return new ResponseEntity<>(requestService.getRequestById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(consumes = {"application/json"})
    private ResponseEntity<Request> createRequest(@RequestBody Request request){
        return new ResponseEntity<>(requestService.createRequest(request.isAccepted(), request.getUserMatch()), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path="/allAcceptedRequests")
    private ResponseEntity<List<Request>> getAcceptedRequests(){
        return new ResponseEntity<>(requestService.getAcceptedRequests(), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(path="{id}")
    private ResponseEntity<Void> deleteRequestById(@PathVariable Integer id){
        requestService.deleteRequestById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
