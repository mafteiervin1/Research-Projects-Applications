using System.Collections.Generic;
using System.Linq;
using BackofficeComponent.Controllers;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
using Microsoft.AspNetCore.Mvc;
using Moq;
using Xunit;

namespace UnitTesting
{
    public class ControllerTest
    {
        [Fact]
        public void GetAllTest()
        {
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.ProjectJsons).Returns(GetMockData());
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Get().Value;

            var projectJsons = Assert.IsAssignableFrom<IEnumerable<ProjectJson>>(result);
            Assert.Equal(3, projectJsons.Count());
        }

        [Fact]
        public void PostTest()
        {
            ProjectJson newProjectJson = new ProjectJson
            {
                Id = "4",
                ActivePeriod = "4 months",
                Domain = "Domain4",
                EligibleApplicants = "EA4",
                Purpose = "Purpose4"
            };
            var mockRepo = new Mock<IProjectJsonRepository>();
            mockRepo.Setup(repo => repo.InsertProjectJson(It.IsAny<ProjectJson>())).Returns(newProjectJson);
            // mockRepo.Setup(repo => repo.InsertProjectJson(It.IsAny<ProjectJson>())).Verifiable();
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
        
        private IEnumerable<ProjectJson> GetMockData()
        {
            var mockData = new List<ProjectJson>
            {
                new()
                {
                    Id = "1",
                    ActivePeriod = "1 month",
                    Domain = "Domain1",
                    EligibleApplicants = "EA1",
                    Purpose = "Purpose1"
                },
                new()
                {
                    Id = "2",
                    ActivePeriod = "2 months",
                    Domain = "Domain2",
                    EligibleApplicants = "EA2",
                    Purpose = "Purpose2"
                },
                new()
                {
                    Id = "3",
                    ActivePeriod = "3 months",
                    Domain = "Domain3",
                    EligibleApplicants = "EA3",
                    Purpose = "Purpose3"
                }
            };
            return mockData;
        }
    }
}