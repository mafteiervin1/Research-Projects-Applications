using System;
using System.Collections;
using System.Collections.Generic;
using BackofficeComponent.Models;
using MongoDB.Bson;

namespace BackofficeComponent.Repositories
{
    public interface IProjectJsonRepository
    {
        IEnumerable<ProjectJson> ProjectJsons { get; }
        IEnumerable<ProjectJson> GetProjectJsons();
        ProjectJson GetProjectJsonById(String id);
        ProjectJson InsertProjectJson(ProjectJson projectJson);
        void DeleteProjectJson(String id);
        void UpdateProjectJson(ProjectJson projectJson);
        
    }
}