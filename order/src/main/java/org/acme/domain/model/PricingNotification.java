package org.acme.domain.model;
import lombok.Data;

@Data
public class PricingNotification {
    private Boolean PricingNotificationState;

    public void setPricingNotificationState(boolean pricingNotificationState) {
        this.PricingNotificationState=pricingNotificationState;
    }


    public Boolean getricingNotificationState()
    {
        return PricingNotificationState;
    }

}
