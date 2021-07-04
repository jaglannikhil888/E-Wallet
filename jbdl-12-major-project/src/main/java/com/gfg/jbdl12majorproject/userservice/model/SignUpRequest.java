package com.gfg.jbdl12majorproject.userservice.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}
