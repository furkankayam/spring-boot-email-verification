package com.furkankayam.service;

import com.furkankayam.dto.AccountRequestDto;
import com.furkankayam.exception.DuplicateEmailException;
import com.furkankayam.model.Account;
import com.furkankayam.repository.AccountRepository;
import com.furkankayam.util.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.account.routing}")
    private String accountRoutingKey;

    private final AccountMapper accountMapper;
    private final RabbitTemplate rabbitTemplate;
    private final AccountRepository accountRepository;

    public void saveAccount(AccountRequestDto accountRequestDto){
        Account account = accountMapper.toAccount(accountRequestDto);
        if (!accountRepository.existsAccountByEmail(account.getEmail())){
            account.setIsActive(false);
            accountRepository.save(account);
            rabbitTemplate.convertAndSend(
                    exchange,
                    accountRoutingKey,
                    account
            );
        } else throw new DuplicateEmailException(account.getEmail());
    }

    @RabbitListener(queues = "${rabbitmq.verify.queue}")
    public void accountVerify(String email) {
        Account account = accountRepository.findByEmail(email);
        if (!account.getIsActive()){
            account.setIsActive(true);
            accountRepository.save(account);
        }
    }

    public void reVerifyAccount(String email) {
        final Account reVerifyAccountByEmail = accountRepository.findByEmail(email);
        if (!reVerifyAccountByEmail.getIsActive()){
            rabbitTemplate.convertAndSend(
                    exchange,
                    accountRoutingKey,
                    reVerifyAccountByEmail
            );
        }
    }
}
