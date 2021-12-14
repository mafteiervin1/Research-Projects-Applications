using System.Collections.Generic;
using System.Linq;
using BackofficeComponent.Controllers;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
using Microsoft.AspNetCore.JsonPatch;
using Microsoft.AspNetCore.Mvc;
using Moq;
using Xunit;

namespace UnitTesting
{
    public class ControllerTests
    {
        [Fact]
        public void GetAllTest()
        {
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.ProjectJsons).Returns(Auxiliaries.GetMockData());
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Get().Value;
            
            mockRepo.Verify(repo => repo.ProjectJsons, Times.Once);
            var projectJsons = Assert.IsAssignableFrom<IEnumerable<ProjectJson>>(result);
            Assert.Equal(3, projectJsons.Count());
        }
        
        [Fact]
        public void GetByIdTest()
        {
            ProjectJson mockProjectJson = Auxiliaries.MockProjectJson;
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.GetProjectJsonById(It.IsAny<string>())).Returns(mockProjectJson);
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Get("4").Value;

            var projectJson = Assert.IsAssignableFrom<ProjectJson>(result);
            Assert.Equal("4", projectJson?.Id);
            Assert.Equal("4 months", projectJson?.ActivePeriod);
            Assert.Equal("Domain4", projectJson?.Domain);
            Assert.Equal("EA4", projectJson?.EligibleApplicants);
            Assert.Equal("Purpose4", projectJson?.Purpose);
            Assert.Equal("no more info", projectJson?.MoreInfo);
            Assert.Equal("4000$", projectJson?.Budget);
            Assert.Equal("Any", projectJson?.EligibleActivities);
            Assert.Equal("All", projectJson?.Finances);
        }

        [Fact]
        public void PostOneTest()
        {
            ProjectJson newProjectJson = Auxiliaries.MockProjectJson;
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.InsertProjectJson(It.IsAny<ProjectJson>())).Returns(newProjectJson);
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Post(newProjectJson);

            var actionResult = Assert.IsType<ActionResult<ProjectJson>>(result);
            var actionValue = Assert.IsType<CreatedResult>(result.Result);
            var projectJson = (ProjectJson) actionValue.Value;
            Assert.Equal("4", projectJson?.Id);
            Assert.Equal("4 months", projectJson?.ActivePeriod);
            Assert.Equal("Domain4", projectJson?.Domain);
            Assert.Equal("EA4", projectJson?.EligibleApplicants);
            Assert.Equal("Purpose4", projectJson?.Purpose);
            Assert.Equal("no more info", projectJson?.MoreInfo);
            Assert.Equal("4000$", projectJson?.Budget);
            Assert.Equal("Any", projectJson?.EligibleActivities);
            Assert.Equal("All", projectJson?.Finances);
        }

        [Fact]
        public void PostManyTest()
        {
            List<ProjectJson> newProjectJson = Auxiliaries.GetMockData().ToList();
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.InsertProjectJsonCollection(It.IsAny<IEnumerable<ProjectJson>>())).Returns(newProjectJson);
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Post(newProjectJson);

            var actionResult = Assert.IsType<ActionResult<ProjectJson>>(result);
            var actionValue = Assert.IsType<CreatedResult>(result.Result);
            var projectJson = (List<ProjectJson>) actionValue.Value;
            Assert.Equal(3, projectJson?.Count);
        }

        [Fact]
        public void PutTest()
        {
            ProjectJson updatedProjectJson = Auxiliaries.MockProjectJson;
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.ReplaceProjectJson(It.IsAny<ProjectJson>())).Verifiable();
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Put(updatedProjectJson);

            Assert.IsType<OkResult>(result);
            mockRepo.Verify();
        }

        [Fact]
        public void PatchTest()
        {
            JsonPatchDocument<ProjectJson> projectJsonPatch = new JsonPatchDocument<ProjectJson>();
            projectJsonPatch.Replace(pJ => pJ.Budget, "100000$");
            projectJsonPatch.Test(pj => pj.Domain, "Medicine");
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo
                .Setup(repo =>repo
                    .UpdateProjectJson(It.IsAny<string>(), It.IsAny<JsonPatchDocument<ProjectJson>>()))
                .Verifiable();
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Patch("4",projectJsonPatch);

            Assert.IsType<OkResult>(result);
            mockRepo.Verify();
        }

        [Fact]
        public void DeleteTest()
        {
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.DeleteProjectJson(It.IsAny<string>())).Verifiable();
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Delete("3");

            Assert.IsType<OkResult>(result);
            mockRepo.Verify();
        }

    }
}