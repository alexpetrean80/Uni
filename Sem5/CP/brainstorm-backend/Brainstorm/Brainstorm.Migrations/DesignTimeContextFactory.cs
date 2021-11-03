using System.ComponentModel.Design;
using Brainstorm.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;

namespace Booking.Migrations {
    public class DesignTimeContextFactory : IDesignTimeDbContextFactory<BrainstormContext> {
        private static readonly string MigrationsAssemblyName =
            typeof(DesignTimeContextFactory).Assembly.GetName().Name;

        public BrainstormContext CreateDbContext(string[] args) {
            var builder = new DbContextOptionsBuilder<BrainstormContext>()
                .UseNpgsql(
                    "host=localhost;user id=postgres;password=postgres;database=brainstorm;Pooling=false;Timeout=300;CommandTimeout=300;", x => x.MigrationsAssembly(MigrationsAssemblyName));
            return new BrainstormContext(builder.Options);
        }
    }
}