using System;
using Microsoft.EntityFrameworkCore;

namespace Booking.Migrations {
    internal static class Program {
        static void Main(string[] args) {
            var ctx = new DesignTimeContextFactory().CreateDbContext(args);
            ctx.Database.Migrate();
        }
    }
}