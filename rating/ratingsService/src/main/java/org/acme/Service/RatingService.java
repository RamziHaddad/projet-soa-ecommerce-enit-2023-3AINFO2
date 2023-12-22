package org.acme.Service;

import java.util.List;

import org.acme.Domain.Avis;
import org.acme.Domain.ProductID;
import org.acme.Domain.Rating;
import org.acme.Domain.UserID;

public interface RatingService {
    public void addRating(UserID userID, ProductID productID, Avis avis);
    public Multi<Rating> getProductRatings(ProductID productID);
    public Multi<Rating>  getUserRating(UserID userID);

}
