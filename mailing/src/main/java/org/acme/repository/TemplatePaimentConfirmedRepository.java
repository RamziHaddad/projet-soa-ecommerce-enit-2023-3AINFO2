package org.acme.repository;

import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class TemplatePaimentConfirmedRepository {
    
    @CheckedTemplate
    static class Templates{

        public static native MailTemplate.MailTemplateInstance templatePaimentConfirmed(String name, String mail);

    }

    
    public Uni<Void> notifyConfirmationPaiment(String name, String mail) {
        System.out.println("done");
        return Templates
            .templatePaimentConfirmed(name, mail)
            .to(mail)
            .subject("Payment Confirmation")
            .send();
            
    }

    

} 