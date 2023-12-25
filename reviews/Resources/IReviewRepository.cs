namespace ReviewService.Resources
{
    public interface IReviewRepository
    {
        public void AddReview(Review review);
        public void DeleteReview(int userId, int productId);
        public ICollection<Review> GetUserReviews(int userId);
        public ICollection<Review> GetProductReviews(int productId);
    }
}
