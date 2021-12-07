using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
using Microsoft.AspNetCore.JsonPatch;

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

        [HttpPost("one")]
        public ActionResult<ProjectJson> Post([FromBody] ProjectJson projectJson)
        {
            ProjectJson createdProjectJson = _projectJsonRepository.InsertProjectJson(projectJson);
            return Created($"GetProjectJson/{createdProjectJson.Id}", createdProjectJson);
        }

        [HttpPost("many")]
        public ActionResult<ProjectJson> Post([FromBody] IEnumerable<ProjectJson> projectJsons)
        {
            IEnumerable<ProjectJson> createdProjectJsons = _projectJsonRepository.InsertProjectJsonCollection(projectJsons);
            
            return Created("GetProjectJson/", createdProjectJsons);
        }

        [HttpPut]
        public IActionResult Put([FromBody] ProjectJson projectJson)
        {
            _projectJsonRepository.ReplaceProjectJson(projectJson);
            return Ok();
        }

        [HttpPatch("{id}")]
        public IActionResult Patch(String id, [FromBody] JsonPatchDocument<ProjectJson> projectJsonPatch)
        {
            
            _projectJsonRepository.UpdateProjectJson(id, projectJsonPatch);
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
