package com.furkankayam.service;

import com.furkankayam.exception.InvalidOtpException;
import com.furkankayam.exception.VerificationTimeoutException;
import com.furkankayam.model.MailContent;
import com.furkankayam.model.Account;
import com.furkankayam.repository.MailRepository;
import com.furkankayam.util.MailUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.verify.routing}")
    private String verifyRoutingKey;

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final RabbitTemplate rabbitTemplate;
    private final MailRepository mailRepository;
    private final JavaMailSender javaMailSender;

    @RabbitListener(queues = "${rabbitmq.account.queue}")
    public void sendMail(Account account) throws MessagingException {
        String tempOtp = MailUtil.generateOTP();
        final MailContent mailContent = MailContent.builder()
                .email(account.getEmail())
                .otp(tempOtp)
                .createdDate(LocalDateTime.now())
                .build();
        mailRepository.save(mailContent);
        Context context = new Context();
        context.setVariable("name", account.getName());
        context.setVariable("url", MailUtil.generateContent(account.getEmail(), tempOtp));
        String text = templateEngine.process("mail-template", context);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setPriority(1);
        helper.setSubject("Account Activation");
        helper.setFrom(account.getEmail());
        helper.setTo(account.getEmail());
        helper.setText(text, true);
        javaMailSender.send(message);
    }

    public void accountVerify(String email, String otp) {
        MailContent mailContent = mailRepository.findFirstMailContentByEmailOrderByIdDesc(email);
        if (
                mailContent.getOtp().equals(otp) &&
                        Duration.between(
                                mailContent.getCreatedDate(), LocalDateTime.now()).getSeconds() < (60)
        ) {
            rabbitTemplate.convertAndSend(exchange, verifyRoutingKey, email);
        } else if (!mailContent.getOtp().equals(otp)) {
            throw new InvalidOtpException(otp);
        } else if (Duration.between(mailContent.getCreatedDate(), LocalDateTime.now()).getSeconds() > (60)) {
            throw new VerificationTimeoutException(email);
        }
    }
}
