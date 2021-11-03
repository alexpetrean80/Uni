using System;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Brainstorm.Business.AppVersions.Models;
using Brainstorm.Business.AppVersions.Queries;
using Brainstorm.Entities;
using MediatR;

namespace Brainstorm.Business.AppVersions.Handlers
{
    public class GetVersionQueryHandler : IRequestHandler<GetVersionQuery, AppVersionCode> {
        private readonly BrainstormContext _ctx;

        public GetVersionQueryHandler(BrainstormContext ctx) {
            _ctx = ctx;
        }

        public Task<AppVersionCode> Handle(GetVersionQuery request, CancellationToken cancellationToken) {
            if (!_ctx.AppVersions.Any()) {
                throw new Exception("No version found.");
            }

            var code = _ctx.AppVersions.OrderByDescending(v => v.Id).FirstOrDefault()
                .ToVersionCode();

            return Task.FromResult(code);
        }
    }
}