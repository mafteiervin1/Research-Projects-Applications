using System;
using System.Collections.Generic;
using BackofficeComponent.Models;
using MongoDB.Bson;
using MongoDB.Driver;

namespace BackofficeComponent.Repositories
{
    public class ProjectJsonRepository : IProjectJsonRepository
    {
        private readonly IMongoCollection<ProjectJson> _collection;

        public ProjectJsonRepository(IRpaDatabaseSettings rpaDatabaseSettings)
        {
            var client = new MongoClient(rpaDatabaseSettings.ConnectionString);
            var database = client.GetDatabase(rpaDatabaseSettings.DatabaseName);

            _collection = database.GetCollection<ProjectJson>(rpaDatabaseSettings.ProjectsCollectionName);
        }

        public IEnumerable<ProjectJson> ProjectJsons => _collection.AsQueryable().ToEnumerable();
        public IEnumerable<ProjectJson> GetProjectJsons()
        {
            throw new System.NotImplementedException();
        }
        public ProjectJson GetProjectJsonById(String id)
        {
            throw new System.NotImplementedException();
        }
        public ProjectJson InsertProjectJson(ProjectJson projectJson)
        {
            throw new System.NotImplementedException();
        }
        public void DeleteProjectJson(String id)
        {
            throw new System.NotImplementedException();
        }
        public void UpdateProjectJson(ProjectJson projectJson)
        {
            throw new System.NotImplementedException();
        }
    }
}