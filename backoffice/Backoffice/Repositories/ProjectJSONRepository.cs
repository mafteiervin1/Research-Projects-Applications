using System;
using System.Collections.Generic;
using BackofficeComponent.Loggers;
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

        // Plays the role of GetProjectJsons() !
        [LoggingAspect]
        public IEnumerable<ProjectJson> ProjectJsons => _collection.AsQueryable().ToEnumerable();
        
        public IEnumerable<ProjectJson> GetProjectJsons()
        {
            return ProjectJsons;
        }
        [LoggingAspect]
        public ProjectJson GetProjectJsonById(String id)
        {
            return _collection.FindSync(entry => entry.Id == id).FirstOrDefault();
        }
        [LoggingAspect]
        public ProjectJson InsertProjectJson(ProjectJson projectJson)
        {
            projectJson.Id ??= ObjectId.GenerateNewId().ToString();
            _collection.InsertOne(projectJson);
            return _collection.FindSync(entry => entry.Id == projectJson.Id).FirstOrDefault();
        }
        [LoggingAspect]
        public void DeleteProjectJson(String id)
        {
            _collection.FindOneAndDelete(entry => entry.Id == id);
        }
        [LoggingAspect]
        public void ReplaceProjectJson(ProjectJson projectJson)
        {
            _collection.ReplaceOne(entry => entry.Id == projectJson.Id, projectJson);
        }
        [LoggingAspect]
        public void UpdateProjectJson(string id, string updateJson)
        {
            throw new NotImplementedException();
        }
    }
}