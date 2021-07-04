package com.gfg.jbdl12majorproject.TransactionManagementSystem.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionUpdate {
    private String  transactionId;
    private TransactionStatus transactionStatus;
}
