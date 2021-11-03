using Brainstorm.Entities.AppVersion;
using Microsoft.EntityFrameworkCore;

namespace Brainstorm.Entities {
    public class BrainstormContext : DbContext {
        public BrainstormContext() { }
        public BrainstormContext(DbContextOptions opts) : base(opts) { }

        public DbSet<AppVersion.AppVersion> AppVersions { get; init; }

        protected override void OnModelCreating(ModelBuilder builder) {
            builder.ApplyConfiguration(new AppVersionConfig());
        }
    }
}