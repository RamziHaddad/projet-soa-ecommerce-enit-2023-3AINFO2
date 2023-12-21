package org.acme.repository;

import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemplateShippingOngoingRepository {
    
    @CheckedTemplate
    static class Templates{

        /**
         * @param name
         * @param mail
         * @return
         */
        public static native MailTemplate.MailTemplateInstance templateShippingOngoing(String name, String mail);

    }

    
    public Uni<Void> notifyShippingOngoing(String name, String mail) {
        System.out.println("done");
        return Templates
            .templateShippingOngoing(name, mail)
            .to(mail)
            .subject("Payment Confirmation")
            .send();
            
    }
}
