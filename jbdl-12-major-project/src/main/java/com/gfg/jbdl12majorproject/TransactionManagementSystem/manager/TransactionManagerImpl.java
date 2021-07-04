package com.gfg.jbdl12majorproject.TransactionManagementSystem.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.entities.Transaction;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionRequest;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionResponse;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionStatus;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionUpdate;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.repository.TransactionRepository;
import com.gfg.jbdl12majorproject.notfication.NotificationRequest;
import com.gfg.jbdl12majorproject.notfication.NotificationType;
import com.gfg.jbdl12majorproject.wallet.model.UpdateWalletRequest;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TransactionManagerImpl implements TransactionManager{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) throws JsonProcessingException {
        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionMethod(transactionRequest.getTransactionMethod())
                .transactionStatus(TransactionStatus.PENDING)
                .amount(transactionRequest.getAmount())
                .transactionType(transactionRequest.getTransactionType())
                .currency(transactionRequest.getCurrency())
                .toUserId(transactionRequest.getToUserId())
                .fromUserId(transactionRequest.getFromUserId())
                .createdAt(new Date())
                .build();
        Transaction transactionSaved = transactionRepository.save(transaction);
        UpdateWalletRequest updateWalletRequest = UpdateWalletRequest.builder()
                .amount(transaction.getAmount())
                .fromUser(transaction.getFromUserId())
                .toUser(transaction.getToUserId())
                .transactionId(transaction.getTransactionId())
                .transactionType(transaction.getTransactionType())
                .build();

        kafkaTemplate.send("updateWallet",objectMapper.writeValueAsString(updateWalletRequest));

        return TransactionResponse.builder()
                .transactionId(transactionSaved.getTransactionId())
                .transactionStatus(transactionSaved.getTransactionStatus())
                .build();
    }

    @Override
    public TransactionResponse getTransaction(String id) throws NotFoundException {
        Transaction transaction = transactionRepository.findByTransactionId(id)
                .orElseThrow(()-> new NotFoundException("transaction is not present"));
        return  TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .transactionStatus(transaction.getTransactionStatus())
                .build();
    }

    @Override
    @KafkaListener(topics = "transactionUpdate" , groupId = "transaction")
    public void updateTransaction(String updateRequest) throws JsonProcessingException {
        TransactionUpdate transactionUpdate = objectMapper.readValue(updateRequest, TransactionUpdate.class);
        try {
            Transaction transaction = transactionRepository.findByTransactionId(transactionUpdate.getTransactionId())
                    .orElseThrow(()-> new NotFoundException("transaction is not present"));

            transaction.setTransactionStatus(transactionUpdate.getTransactionStatus());
            transactionRepository.save(transaction);
            NotificationRequest notificationRequest = NotificationRequest
                    .builder()
                    .user(transaction.getFromUserId())
                    .type(NotificationType.TRANSACTION_UPDATE)
                    .message("Trasaction with id ".concat(transaction.getTransactionId()).concat(" ")
                            .concat(transaction.getTransactionStatus().name())).build();
            kafkaTemplate.send("notification",objectMapper.writeValueAsString(notificationRequest));
            notificationRequest.setUser(transaction.getToUserId());
            if(transaction.getTransactionStatus().name().equals("SUCCESS")) {
                notificationRequest.setMessage("Your wallet got ".concat(transaction.getTransactionType().name()).concat("ed ")
                        .concat(String.valueOf(transaction.getAmount())));
                kafkaTemplate.send("notification", objectMapper.writeValueAsString(notificationRequest));
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }


    }

}
