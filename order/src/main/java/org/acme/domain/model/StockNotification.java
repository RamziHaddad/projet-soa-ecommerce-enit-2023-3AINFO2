package org.acme.domain.model;

import lombok.Data;

@Data
public class StockNotification {
    
    private boolean StockNotificationState;

    public boolean isStockNotificationState() {
        return StockNotificationState;
    }

    public void setStockNotificationState(boolean stockNotificationState) {
        this.StockNotificationState = stockNotificationState;
    }
}
