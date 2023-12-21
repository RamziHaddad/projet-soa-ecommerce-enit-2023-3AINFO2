package org.acme.infrastructure;

import org.acme.repository.MailerRepository;
import org.acme.repository.TemplatePaimentConfirmedRepository;
import org.acme.service.MailerService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MailerServiceImp implements MailerService{

    @Inject MailerRepository mailerRepository;
    @Inject TemplatePaimentConfirmedRepository templateRepository;

    
    @Override
    public void notifyConfirmationPaiment(String name, String email) {
        templateRepository.notifyConfirmationPaiment(name, email);
        
    }

    @Override
    public void notifyShippingOngoing(String name, String email) {
        
      }

}
