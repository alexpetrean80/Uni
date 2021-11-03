using System.Threading.Tasks;
using Brainstorm.Business.AppVersions.Models;
using Brainstorm.Business.AppVersions.Queries;
using MediatR;
using Microsoft.AspNetCore.Mvc;

namespace Brainstorm.API.Controllers {
    [ApiController]
    [Route("api/[controller]")]
    public class AppVersionController : ControllerBase {
        private readonly IMediator _mediator;

        public AppVersionController(IMediator mediator) {
            _mediator = mediator;
        }

        [HttpGet("version")]
        public async Task<ActionResult<string>> GetAppVersion() {
            var res = await _mediator.Send(new GetVersionQuery());
            return Ok(res);
        }
    }
}