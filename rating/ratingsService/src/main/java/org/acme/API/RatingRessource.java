package org.acme.API;

import java.util.List;
import java.util.UUID;

import org.acme.API.DTOS.AddRatingDTO;
import org.acme.Domain.ProductID;
import org.acme.Domain.Rating;
import org.acme.Service.RatingService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/rating")
public class RatingRessource {

    @Inject
    RatingService ratingService;

    @POST
    @Path("/add-rating")
    public void addRating(AddRatingDTO addRatingDTO){

        this.ratingService.addRating(addRatingDTO.userID(), addRatingDTO.productID(), addRatingDTO.avis());
    }
    @POST
    @Path("/get-product-ratings")
    public Multi<Rating>  getProductRating(ProductID productID){
        return this.ratingService.getProductRating(productID);
        
    }
    @POST
    @Path("/get-user-ratings")
    public Multi<Rating>  getUserRating(UserID userID){
        return this.ratingService.getUserRating(userID);
        
    }
}
