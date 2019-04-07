package com.tim3.Match.controllers;

import com.tim3.Match.models.Request;
import com.tim3.Match.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public ResponseEntity<List<Request>> getAll(){
        return new ResponseEntity<>(requestService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable Integer id){
        Request request = requestService.getById(id);
        if(request == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Request> create(@Valid @RequestBody Request request){
        Request createdRequest = requestService.create(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        if(!requestService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        requestService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
