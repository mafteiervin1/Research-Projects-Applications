using System;
using System.Collections.Generic;
using System.Linq;
using BackofficeComponent.Loggers;
using BackofficeComponent.Models;
using Microsoft.AspNetCore.JsonPatch;
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

        public ProjectJsonRepository(IMongoCollection<ProjectJson> collection)
        {
            _collection = collection;
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
        public IEnumerable<ProjectJson> InsertProjectJsonCollection(IEnumerable<ProjectJson> projectJsons)
        {
            List<ProjectJson> projectJsonsList = projectJsons.ToList();
            List<String> addedIds = new List<string>();
            foreach (ProjectJson projectJson in projectJsonsList)
            {
                projectJson.Id ??= ObjectId.GenerateNewId().ToString();
                addedIds.Add(projectJson.Id);
            }
            _collection.InsertMany(projectJsonsList);
            return _collection.FindSync(entry => addedIds.Contains(entry.Id)).ToList();
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
        public void UpdateProjectJson(string id, JsonPatchDocument<ProjectJson> projectJsonPatch)
        {
            ProjectJson projectJsonToUpdate = _collection.FindSync(entry => entry.Id == id).FirstOrDefault();
            ProjectJson updatedProjectJson = (ProjectJson) projectJsonToUpdate.Clone();
            projectJsonPatch.ApplyTo(updatedProjectJson);
            var updateDoc = Builders<ProjectJson>.Update.Combine(GetUpdates(projectJsonToUpdate, updatedProjectJson));
            _collection.UpdateOne(entry => entry.Id == projectJsonToUpdate.Id, updateDoc);
        }

        private IEnumerable<UpdateDefinition<ProjectJson>> GetUpdates(ProjectJson projectJsonToUpdate, ProjectJson updatedProjectJson)
        {
            List<UpdateDefinition<ProjectJson>> updates = new List<UpdateDefinition<ProjectJson>>();
            if (updatedProjectJson.Id != projectJsonToUpdate.Id)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.Id, updatedProjectJson.Id));
            }
            if (updatedProjectJson.ActivePeriod != projectJsonToUpdate.ActivePeriod)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.ActivePeriod, updatedProjectJson.ActivePeriod));
            }
            if (updatedProjectJson.Budget != projectJsonToUpdate.Budget)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.Budget, updatedProjectJson.Budget));
            }
            if (updatedProjectJson.Domain != projectJsonToUpdate.Domain)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.Domain, updatedProjectJson.Domain));
            }
            if (updatedProjectJson.EligibleActivities != projectJsonToUpdate.EligibleActivities)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.EligibleActivities, updatedProjectJson.EligibleActivities));
            }
            if (updatedProjectJson.EligibleApplicants != projectJsonToUpdate.EligibleApplicants)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.EligibleApplicants, updatedProjectJson.EligibleApplicants));
            }
            if (updatedProjectJson.Finances != projectJsonToUpdate.Finances)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.Finances, updatedProjectJson.Finances));
            }
            if (updatedProjectJson.MoreInfo != projectJsonToUpdate.MoreInfo)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.MoreInfo, updatedProjectJson.MoreInfo));
            }
            if (updatedProjectJson.Purpose != projectJsonToUpdate.Purpose)
            {
                updates.Add(Builders<ProjectJson>.Update.Set(pJ => pJ.Purpose, updatedProjectJson.Purpose));
            }
            return updates;
        }
    }
}