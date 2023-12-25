using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace ReviewService.Resources
{
    public class Review
    {
        [Key]
        [Column(Order = 1)]
        public int userId { get; set; }
        [Key]
        [Column(Order = 2)]
        public int productId { get; set; }
        public string content { get; set; }
        public DateTime reviewDate { get; set; }
    }
}
