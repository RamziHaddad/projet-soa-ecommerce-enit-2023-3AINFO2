package org.acme.service;

public interface MailerService {

    public void notifyConfirmationPaiment(String name, String email);
    public void notifyShippingOngoing(String name, String email);
}
