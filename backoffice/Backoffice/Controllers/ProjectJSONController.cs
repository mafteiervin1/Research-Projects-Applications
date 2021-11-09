using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
using Microsoft.AspNetCore.JsonPatch;
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
            _projectJsonRepository.ProjectJsons.ToList();

        [HttpGet("{id}")]
        public ActionResult<ProjectJson> Get(String id)
        {
            return _projectJsonRepository.GetProjectJsonById(id);
        }

        [HttpPost]
        public ActionResult<ProjectJson> Post([FromBody] ProjectJson projectJson)
        {
            ProjectJson createdProjectJson = _projectJsonRepository.InsertProjectJson(projectJson);
            return Created($"GetProjectJson/{createdProjectJson.Id}", createdProjectJson);
        }

        [HttpPut]
        public IActionResult Put([FromBody] ProjectJson projectJson)
        {
            _projectJsonRepository.ReplaceProjectJson(projectJson);
            return Ok();
        }

        [HttpPatch("{id}")]
        public IActionResult Patch(String id, [FromBody] JsonPatchDocument<ProjectJson> projectJson)
        {
            _projectJsonRepository.UpdateProjectJson(id, projectJson.ToString());
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
