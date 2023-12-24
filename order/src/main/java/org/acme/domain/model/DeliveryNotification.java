package org.acme.domain.model;

import lombok.Data;

@Data
public class DeliveryNotification {
    
    private boolean DeliveryNotificationState;

    public boolean isDeliveryNotificationState() {
        return DeliveryNotificationState;
    }

    public void setDeliveryNotificationState(boolean deliveryNotificationState) {
        this.DeliveryNotificationState = deliveryNotificationState;
    }
}
