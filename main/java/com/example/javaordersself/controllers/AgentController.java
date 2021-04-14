package com.example.javaordersself.controllers;

import com.example.javaordersself.models.Agent;
import com.example.javaordersself.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentServices agentServices;
    @GetMapping(value = "/agent/{agentcode}", produces = "application/json")
    public ResponseEntity<?> listAgentById(@PathVariable long agentcode)
    {
        Agent  rtnList = agentServices.findAgentById(agentcode);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }
}
