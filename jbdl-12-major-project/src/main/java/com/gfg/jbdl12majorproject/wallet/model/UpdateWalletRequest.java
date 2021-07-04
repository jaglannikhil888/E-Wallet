package com.gfg.jbdl12majorproject.wallet.model;

import com.gfg.jbdl12majorproject.TransactionManagementSystem.model.TransactionType;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateWalletRequest {
    String fromUser;
    String toUser;
    Double amount;
    TransactionType transactionType;
    String transactionId;
}
