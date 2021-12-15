using BackofficeComponent.Models;
using Xunit;

namespace UnitTesting
{
    public class ModelTests
    {
        [Fact]
        public void CloneEqTest()
        {
            ProjectJson originalProjectJson = Auxiliaries.MockProjectJson;
            ProjectJson cloneProjectJson = (ProjectJson) originalProjectJson.Clone();
            
            
            Assert.Equal(originalProjectJson.Id, cloneProjectJson.Id);
            Assert.Equal(originalProjectJson.ActivePeriod, cloneProjectJson.ActivePeriod);
            Assert.Equal(originalProjectJson.Domain, cloneProjectJson.Domain);
            Assert.Equal(originalProjectJson.EligibleApplicants, cloneProjectJson.EligibleApplicants);
            Assert.Equal(originalProjectJson.Purpose, cloneProjectJson.Purpose);
            Assert.Equal(originalProjectJson.MoreInfo, cloneProjectJson.MoreInfo);
            Assert.Equal(originalProjectJson.Budget, cloneProjectJson.Budget);
            Assert.Equal(originalProjectJson.EligibleActivities, cloneProjectJson.EligibleActivities);
            Assert.Equal(originalProjectJson.Finances, cloneProjectJson.Finances);
        }
        
        [Fact]
        public void CloneDifRefTest()
        {
            ProjectJson originalProjectJson = Auxiliaries.MockProjectJson;
            ProjectJson cloneProjectJson = (ProjectJson) originalProjectJson.Clone();
            cloneProjectJson.Finances = "None";
            
            
            Assert.Equal(originalProjectJson.Id, cloneProjectJson.Id);
            Assert.Equal(originalProjectJson.ActivePeriod, cloneProjectJson.ActivePeriod);
            Assert.Equal(originalProjectJson.Domain, cloneProjectJson.Domain);
            Assert.Equal(originalProjectJson.EligibleApplicants, cloneProjectJson.EligibleApplicants);
            Assert.Equal(originalProjectJson.Purpose, cloneProjectJson.Purpose);
            Assert.Equal(originalProjectJson.MoreInfo, cloneProjectJson.MoreInfo);
            Assert.Equal(originalProjectJson.Budget, cloneProjectJson.Budget);
            Assert.Equal(originalProjectJson.EligibleActivities, cloneProjectJson.EligibleActivities);
            Assert.NotEqual(originalProjectJson.Finances, cloneProjectJson.Finances);
            Assert.Equal("All", originalProjectJson.Finances);
            Assert.Equal("None", cloneProjectJson.Finances);
        }
    }
}