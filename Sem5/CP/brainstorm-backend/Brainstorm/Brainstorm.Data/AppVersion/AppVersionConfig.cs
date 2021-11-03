using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace Brainstorm.Entities.AppVersion {
    public class AppVersionConfig:IEntityTypeConfiguration<AppVersion> {
        public void Configure(EntityTypeBuilder<AppVersion> builder) {
            builder.Property(v => v.MajorVersion).IsRequired();
            builder.Property(v => v.MinorVersion).HasDefaultValue(0);
            builder.HasData(new AppVersion {
                    Id = 1,
                    MajorVersion = 0,
                    MinorVersion = 1
                }
            );
        }
    }
} 