package com.tim3.User.services;

import com.tim3.User.models.Request;
import com.tim3.User.models.UserMatch;
import com.tim3.User.repositories.RequestRepository;
import com.tim3.User.repositories.UserMatchRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserMatchRepository userMatchRepository;

    public List<Request> getAllRequests(){
        ArrayList<Request> allRequests = new ArrayList<Request>();
        requestRepository.findAll().forEach(allRequests::add);
        return allRequests;

    }

    public Request getRequestById(Integer id){
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if(!optionalRequest.isPresent()){
            return null;
        }
        return optionalRequest.get();
    }

    public Request createRequest(Request request){
        Integer user_match_id = request.getUserMatch().getId();
        Optional<UserMatch> userMatch = userMatchRepository.findById(user_match_id);
        request.setUserMatch(userMatch.orElse(null));
        return requestRepository.save(request);
    }

    public List<Request> getAcceptedRequests(){
        ArrayList<Request> allRequests = new ArrayList<>();
        for (Request request : requestRepository.findAll()) {
            if (request.isAccepted())
                allRequests.add(request);
        }
        return allRequests;
    }

    public void deleteRequestById(Integer id){
        requestRepository.deleteById(id);
    }

}
