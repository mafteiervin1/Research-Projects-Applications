using System;
using System.Collections.Generic;
using BackofficeComponent.Models;
using Microsoft.AspNetCore.JsonPatch;

namespace BackofficeComponent.Repositories
{
    public interface IProjectJsonRepository
    {
        IEnumerable<ProjectJson> ProjectJsons { get; }
        IEnumerable<ProjectJson> GetProjectJsons();
        ProjectJson GetProjectJsonById(String id);
        ProjectJson InsertProjectJson(ProjectJson projectJson);
        IEnumerable<ProjectJson> InsertProjectJsonCollection(IEnumerable<ProjectJson> projectJsons);
        void DeleteProjectJson(String id);
        void ReplaceProjectJson(ProjectJson projectJson);
        void UpdateProjectJson(string id, JsonPatchDocument<ProjectJson> projectJsonPatch);
    }
}