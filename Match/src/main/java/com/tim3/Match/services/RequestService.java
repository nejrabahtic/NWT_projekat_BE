package com.tim3.Match.services;

import com.tim3.Match.models.Request;
import com.tim3.Match.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getAll(){
        ArrayList<Request> requests = new ArrayList<>();
        requestRepository.findAll().forEach(requests::add);
        return requests;
    }
    public Request getById(Integer id){
        Optional<Request> request = requestRepository.findById(id);
        return request.orElse(null);
    }
    public boolean existsById(Integer id){
        return requestRepository.existsById(id);
    }
    public Request create(Request request){
        return requestRepository.save(request);
    }
    public void deleteById(Integer id){
        requestRepository.deleteById(id);
    }

}
