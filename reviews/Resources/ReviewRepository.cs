namespace ReviewService.Resources
{
    public class ReviewRepository : IReviewRepository
    {
        private readonly DataContext _context;
        public ReviewRepository(DataContext context)
        {
            _context = context;
        }

        public void AddReview(Review review)
        {
            _context.Reviews.Add(review);
            _context.SaveChanges();
        }

        public void DeleteReview(int userId, int productId)
        {
            var review = _context.Reviews.FirstOrDefault(r => r.userId == userId && r.productId == productId)
            ?? throw new Exception("Review doesn't exist.");
            _context.Reviews.Remove(review);
            _context.SaveChanges();
        }

        public ICollection<Review> GetUserReviews(int userId)
        {
            ICollection<Review> Reviews = _context.Reviews
                .Where(r => r.userId == userId)
                .OrderBy(r => r.reviewDate).ToList();
            return Reviews;
        }

        public ICollection<Review> GetProductReviews(int productId)
        {
            ICollection<Review> Reviews = _context.Reviews
                .Where(r => r.productId == productId)
                .OrderBy(r => r.reviewDate).ToList();
            return Reviews;
        }
    }
}
