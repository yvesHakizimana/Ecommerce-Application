package com.rca.ecommerce.email;

import com.rca.ecommerce.kafka.order.PurchaseResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rca.ecommerce.email.EmailTemplate.ORDER_CONFIRMATION;
import static com.rca.ecommerce.email.EmailTemplate.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name()
            );
            mimeMessageHelper.setFrom("yves@gmail.com");
            final String templateName = PAYMENT_CONFIRMATION.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);

            Context context = new Context();
            context.setVariables(variables);
            mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email successfully sent to " + destinationEmail);
        } catch (MessagingException | MailSendException e) {
            log.warn("Email could not be sent to " + destinationEmail, e);
            // Handle the exception here, like retry logic or notifying administrators
        }
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<PurchaseResponse> productsPurchased
    ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name()
            );
            mimeMessageHelper.setFrom("yves@gmail.com");
            final String templateName = ORDER_CONFIRMATION.getTemplate();
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerName", customerName);
            variables.put("amount", amount);
            variables.put("orderReference", orderReference);
            variables.put("productsPurchased", productsPurchased);

            Context context = new Context();
            context.setVariables(variables);
            mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Email successfully sent to " + destinationEmail);
        } catch (MessagingException | MailSendException e) {
            log.warn("Email could not be sent to " + destinationEmail, e);
            // Handle the exception here, like retry logic or notifying administrators
        }
    }
}
