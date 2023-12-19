package org.acme.domain;

import lombok.Data;

@Data
public class PaymentNotification {

    private Boolean PayementNotificationState;

    public Boolean getPayementNotificationState() {
        return PayementNotificationState;
    }

    public void setPayementNotificationState(Boolean payementNotificationState) {
        this.PayementNotificationState = payementNotificationState;
    }

}