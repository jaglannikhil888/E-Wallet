package com.gfg.jbdl12majorproject.TransactionManagementSystem.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.manager.TransactionManager;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionRequest;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionResponse;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class Controller {

    @Autowired
    private TransactionManager transactionManager;


    @PostMapping("/transaction")
    public ResponseEntity createTransaction(@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transactionResponse
                = null;
        try {
            transactionResponse = transactionManager.createTransaction(transactionRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.accepted().body(transactionResponse);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity getTransaction(@PathVariable("id") String id){
        TransactionResponse transactionResponse
                = null;
        try {
            transactionResponse = transactionManager.getTransaction(id);
            return ResponseEntity.ok(transactionResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
