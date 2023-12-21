package org.acme.infrastructure;

import org.acme.repository.MailerRepository;
import org.acme.service.MailerService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MailerServiceImp implements MailerService{

    @Inject MailerRepository mailerRepository;

    
    @Override
    public void notifyConfirmationPaiment() {
        mailerRepository.sendMail();
    }

    @Override
    public void notifyShippingOngoing() {
        mailerRepository.sendMail();
      }

}
