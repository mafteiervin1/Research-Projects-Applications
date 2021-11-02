using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
using MongoDB.Bson;

namespace BackofficeComponent.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ProjectJsonController : ControllerBase
    {
        private readonly IProjectJsonRepository _projectJsonRepository;

        public ProjectJsonController(IProjectJsonRepository projectJsonRepository)
        {
            _projectJsonRepository = projectJsonRepository;
        }

        [HttpGet]
        public ActionResult<IEnumerable<ProjectJson>> Get() =>
            _projectJsonRepository.GetProjectJsons().ToList();

        [HttpGet("{id}")]
        public ActionResult<ProjectJson> Get(String id)
        {
            return _projectJsonRepository.GetProjectJsonById(id);
        }

        [HttpPost]
        public ActionResult<ProjectJson> Post([FromBody] ProjectJson projectJson)
        {
            return Created($"GetProjectJson/{projectJson.Id}",
            _projectJsonRepository.InsertProjectJson(projectJson));
        }

        [HttpPatch]
        public IActionResult Patch([FromBody] ProjectJson projectJson)
        {
            _projectJsonRepository.UpdateProjectJson(projectJson);
            return Ok();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(String id)
        {
            _projectJsonRepository.DeleteProjectJson(id);
            return Ok();
        }
    }
}
