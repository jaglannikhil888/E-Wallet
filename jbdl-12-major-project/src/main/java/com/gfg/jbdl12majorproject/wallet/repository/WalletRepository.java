package com.gfg.jbdl12majorproject.wallet.repository;

import com.gfg.jbdl12majorproject.wallet.entity.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Optional<Wallet> findByUserId(String username);
}
