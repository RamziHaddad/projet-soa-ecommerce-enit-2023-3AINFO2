package org.acme.Domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.vertx.core.cli.annotations.Description;

public class Rating {
    UUID ratingID;
    UserID userID;
    ProductID productID;
    Avis avis;

    @JsonCreator
    public static Rating of(UserID userID,ProductID productId, Avis avis){
        return new Rating(userID,productId,avis);
    }

    private Rating(UserID userID, ProductID productID, Avis avis) {
        this.ratingID = UUID.randomUUID();
        this.userID = userID;
        this.productID = productID;
        this.avis = avis;
    }

    public UUID getRatingID() {
        return ratingID;
    }

    public UserID getUserID() {
        return userID;
    }

    public ProductID getProductID() {
        return productID;
    }

    public Avis getAvis() {
        return avis;
    }
    public static Rating from(Row row) {
        return new Rating(
            new ratingID(row.getString("ratingID")),
            new UserID(row.getString("userID")),
            new ProductID(row.getString("productID")),
            new Avis(row.getString("avis"))
        );
    }
    
}
