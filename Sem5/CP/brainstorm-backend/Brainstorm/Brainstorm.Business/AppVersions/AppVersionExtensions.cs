using Brainstorm.Business.AppVersions.Models;
using Brainstorm.Entities.AppVersion;

namespace Brainstorm.Business.AppVersions {
    public static class AppVersionExtensions {
        public static AppVersionCode ToVersionCode(this AppVersion version) {
            return new AppVersionCode {
                Version = $"{version.MajorVersion}.{version.MinorVersion}"
            };
        }
    }
}