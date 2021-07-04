package com.gfg.jbdl12majorproject.userservice.repository;

import com.gfg.jbdl12majorproject.userservice.entities.PaymentUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<PaymentUser,Long> {
    Optional<PaymentUser> findByUsername(String s);
}
