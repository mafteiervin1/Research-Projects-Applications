using System.Collections.Generic;
using System.Linq;
using System.Threading;
using BackofficeComponent.Models;
using BackofficeComponent.Repositories;
using MongoDB.Driver;
using MongoDB.Driver.Linq;
using Moq;
using Xunit;

namespace UnitTesting
{
    public class RepositoryTests
    {
        [Fact]
        public void GetProjectJsonByIdTest()
        {
            // IEnumerable<ProjectJson> mockData = Auxiliaries.GetMockData();
            var mockCollection = new Mock<IMongoCollection<ProjectJson>>();
            mockCollection
                .Setup(mC => mC.FindSync(It.IsAny<FilterDefinition<ProjectJson>>(),
                It.IsAny<FindOptions<ProjectJson, ProjectJson>>(),
                It.IsAny<CancellationToken>()));
                // .Returns(Auxiliaries.MockProjectJson);
            var repoObject = new ProjectJsonRepository(mockCollection.Object);
            
            var result = repoObject.GetProjectJsons();

            var projectJsons = Assert.IsAssignableFrom<IEnumerable<ProjectJson>>(result);
            Assert.Equal(3, projectJsons.Count());
        }
    }
}