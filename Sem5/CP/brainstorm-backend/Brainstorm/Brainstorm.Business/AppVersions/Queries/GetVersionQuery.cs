using Brainstorm.Business.AppVersions.Models;
using MediatR;

namespace Brainstorm.Business.AppVersions.Queries {
    public class GetVersionQuery : IRequest<AppVersionCode> { }
}