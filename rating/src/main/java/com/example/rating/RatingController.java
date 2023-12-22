package com.example.ratingservice.controller;

import com.example.ratingservice.entity.Rating;
import com.example.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public Optional<Rating> getRatingById(@PathVariable Long id) {
        return ratingService.getRatingById(id);
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @PutMapping("/{id}")
    public Rating updateRating(@PathVariable Long id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}
