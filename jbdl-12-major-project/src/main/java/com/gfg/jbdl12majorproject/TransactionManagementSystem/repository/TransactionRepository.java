package com.gfg.jbdl12majorproject.TransactionManagementSystem.repository;

import com.gfg.jbdl12majorproject.TransactionManagementSystem.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    Optional<Transaction> findByTransactionId(String id);
}
