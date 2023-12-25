using Microsoft.EntityFrameworkCore;

namespace ReviewService.Resources
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) : base(options) { }
        public DbSet<Review> Reviews { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Review>()
                .HasKey(up => new { up.userId, up.productId });
        }
    }
}
