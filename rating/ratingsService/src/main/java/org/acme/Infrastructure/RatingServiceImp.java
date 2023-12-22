package org.acme.Infrastructure;

import java.util.List;

import org.acme.Domain.Avis;
import org.acme.Domain.ProductID;
import org.acme.Domain.Rating;
import org.acme.Domain.RatingRepo;
import org.acme.Domain.UserID;
import org.acme.Service.RatingService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RatingServiceImp implements RatingService{
@Inject
RatingRepo ratingRepo;

    @Override
    public void addRating(UserID userID, ProductID productID, Avis avis) {
        Rating ratingToAdd = Rating.of(userID, productID, avis);
        this.ratingRepo.addRating(ratingToAdd);
        
    }

    @Override
    public Multi<Rating> getProductRatings(ProductID productID) {
        return this.ratingRepo.getProductRatings(productID);
    }
    @Override
    public Multi<Rating>  getUserRating(UserID userID){
        return this.ratingService.getUserRating(userID);
                   
    }
    
}
