package org.acme.Domain;

import java.util.List;

public interface RatingRepo {

    public void addRating(Rating rating);
    public Multi<Rating> getProductRating(ProductID productID);
    
} 
