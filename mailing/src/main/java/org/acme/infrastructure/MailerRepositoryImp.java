package org.acme.infrastructure;

import org.acme.repository.MailerRepository;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class MailerRepositoryImp implements MailerRepository{

    @Inject Mailer mailer;
    
    @Override
    public void sendMail() {
        mailer.send(Mail.withText("slim.njah2@gmail.com", "salem", "salem"));
    }

    
    
}
