package com.gfg.jbdl12majorproject.TransactionManagementSystem.model;


import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequest {
    private String fromUserId;
    private String toUserId;
    private Double amount;
    private String currency;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
}
