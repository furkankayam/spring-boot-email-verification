package com.furkankayam.repository;

import com.furkankayam.model.MailContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<MailContent, Long> {

    MailContent findFirstMailContentByEmailOrderByIdDesc(String email);
}
