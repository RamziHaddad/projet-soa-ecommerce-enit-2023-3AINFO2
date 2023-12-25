using Microsoft.AspNetCore.Mvc;

namespace ReviewService.Resources
{
    [Route("api/reviews")]
    [ApiController]
    public class ReviewController : ControllerBase
    {
        private readonly IReviewRepository _reviewRepository;

        public ReviewController(IReviewRepository reviewRepository)
        {
            _reviewRepository = reviewRepository;
        }

        [HttpPost]
        public IActionResult sendReview(string content, int userId, int productId)
        {
            try
            {
                Review review = new Review()
                {
                    content = content,
                    userId = userId,
                    productId = productId,
                    reviewDate = DateTime.Now
                };
                _reviewRepository.AddReview(review);
                return Ok("Review sent");
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("users/{userId}")]
        public IActionResult GetUserReviews(int userId)
        {
            try
            {
                var reviews = _reviewRepository.GetUserReviews(userId);
                return Ok(reviews);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet("products/{productId}")]
        public IActionResult GetProductReviews(int productId)
        {
            try
            {
                var reviews = _reviewRepository.GetProductReviews(productId);
                return Ok(reviews);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpDelete("products/{userId}")]
        public IActionResult DeleteTicket(int userId, int productId)
        {
            try
            {
                _reviewRepository.DeleteReview(userId, productId);
                return Ok();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}
