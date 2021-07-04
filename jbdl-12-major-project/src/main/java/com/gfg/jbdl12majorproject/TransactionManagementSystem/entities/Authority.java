package com.gfg.jbdl12majorproject.TransactionManagementSystem.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
