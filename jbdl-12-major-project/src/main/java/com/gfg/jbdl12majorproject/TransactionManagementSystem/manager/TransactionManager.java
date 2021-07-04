package com.gfg.jbdl12majorproject.TransactionManagementSystem.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionRequest;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionResponse;
import javassist.NotFoundException;

public interface TransactionManager {
    TransactionResponse createTransaction(TransactionRequest transactionRequest) throws JsonProcessingException;

    TransactionResponse getTransaction(String id) throws NotFoundException;

    void updateTransaction(String updateRequest) throws JsonProcessingException;
}
