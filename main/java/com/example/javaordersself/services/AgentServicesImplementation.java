package com.example.javaordersself.services;

import com.example.javaordersself.models.Agent;
import com.example.javaordersself.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentService")
public class AgentServicesImplementation implements AgentServices {

    @Autowired
    private AgentRepository restrepos;

    @Override
    public Agent findAgentById(long id) {
        return restrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant " + id + " Not Found"));
    }
}
