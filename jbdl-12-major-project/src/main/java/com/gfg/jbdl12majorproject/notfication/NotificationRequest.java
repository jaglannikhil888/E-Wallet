package com.gfg.jbdl12majorproject.notfication;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationRequest {
    private String user;
    private String message;
    private NotificationType type;
}
