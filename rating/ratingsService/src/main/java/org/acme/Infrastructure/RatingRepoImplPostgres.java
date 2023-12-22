package org.acme.Infrastructure;

import java.util.ArrayList;
import java.util.List;


import org.acme.Domain.ProductID;
import org.acme.Domain.Rating;
import org.acme.Domain.RatingRepo;
import org.acme.Domain.UserID;
import org.acme.Domain.Rating;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;




@ApplicationScoped
public class RatingRepoImplPostgres implements RatingRepo{

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Override
    public void addRating(Rating rating) {
        client.query(String.format("INSERT INTO ratings (rating_id, user_id, product_id, avis) VALUES ('%s', '%s', '%s', '%s')",
        rating.getRatingID(),
              rating.getUserID().userID(),
              rating.getProductID().productID(),
              rating.getAvis().avis()))
        .execute().await().indefinitely();
    }

    @Override
    public Multi<Rating> getProductRatings(ProductID productID) {
        Uni<RowSet<Row>> rowSet = client.query(String.format("SELECT * FROM ratings WHERE product_id='%s'", productID)).execute();
        Multi<Rating> ratings = rowSet
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Rating::from);
        return ratings;
    }

    @Override
    public Multi<Rating> getUserRating(UserID userID) {
        Uni<RowSet<Row>> rowSet = client.query(String.format("SELECT * FROM ratings WHERE user_id='%s'", userID)).execute();
        Multi<Rating> ratings = rowSet
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Rating::from);
        return ratings;
    }

}
