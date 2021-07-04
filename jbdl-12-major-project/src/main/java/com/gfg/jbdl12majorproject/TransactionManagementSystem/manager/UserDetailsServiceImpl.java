package com.gfg.jbdl12majorproject.TransactionManagementSystem.manager;

import com.gfg.jbdl12majorproject.TransactionManagementSystem.entities.Authority;
import com.gfg.jbdl12majorproject.TransactionManagementSystem.entities.User;
import com.gfg.jbdl12majorproject.userservice.entities.PaymentUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        PaymentUser user = restTemplate.getForEntity("http://localhost:8080/user/".concat(username), PaymentUser.class).getBody();
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorityList(Arrays.asList(new Authority(user.getAuthority())))
                .build();
    }
}
