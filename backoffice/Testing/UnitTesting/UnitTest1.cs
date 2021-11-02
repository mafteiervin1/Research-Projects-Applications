using System.Collections.Generic;
using System.Linq;
using BackofficeComponent.Controllers;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
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
            mockRepo.Setup(repo => repo.ProjectJsons).Returns(AddMockData());
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Get();

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
            mockRepo.Setup(repo => repo.ProjectJsons).Returns(AddMockData());
            var controller = new ProjectJsonController(mockRepo.Object);

            var result = controller.Post(newProjectJson);

            var projectJson = Assert.IsType<ProjectJson>(result);
            Assert.Equal("4", projectJson.Id);
            Assert.Equal("4 months", projectJson.ActivePeriod);
            Assert.Equal("Domain4", projectJson.Domain);
            Assert.Equal("EA4", projectJson.EligibleApplicants);
            Assert.Equal("Purpose4", projectJson.Purpose);
        }
        private IEnumerable<ProjectJson> AddMockData()
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