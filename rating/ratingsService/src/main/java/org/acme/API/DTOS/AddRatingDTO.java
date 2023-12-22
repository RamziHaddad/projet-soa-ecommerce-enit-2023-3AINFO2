package org.acme.API.DTOS;

import org.acme.Domain.Avis;
import org.acme.Domain.ProductID;
import org.acme.Domain.UserID;

public record AddRatingDTO(UserID userID, ProductID productID, Avis avis) {
} 