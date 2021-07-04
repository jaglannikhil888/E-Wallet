package com.gfg.jbdl12majorproject.TransactionManagementSystem.model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {
    private String  transactionId;
    private TransactionStatus transactionStatus;
}
