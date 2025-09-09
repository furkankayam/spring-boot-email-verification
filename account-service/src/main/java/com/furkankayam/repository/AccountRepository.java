package com.furkankayam.repository;

import com.furkankayam.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean existsAccountByEmail(String email);
    Account findByEmail(String email);
}
