using System.Collections.Generic;
using BackofficeComponent.Models;

namespace UnitTesting
{
    public static class Auxiliaries
    {
        public static ProjectJson MockProjectJson = new ProjectJson
        {
            Id = "4",
            ActivePeriod = "4 months",
            Domain = "Domain4",
            EligibleApplicants = "EA4",
            Purpose = "Purpose4",
            MoreInfo = "no more info",
            Budget = "4000$",
            EligibleActivities = "Any",
            Finances = "All"
        };
        
        public static IEnumerable<ProjectJson> GetMockData()
        {
            var mockData = new List<ProjectJson>
            {
                new()
                {
                    Id = "1",
                    ActivePeriod = "1 month",
                    Domain = "Domain1",
                    EligibleApplicants = "EA1",
                    Purpose = "Purpose1",
                    MoreInfo = "no more info",
                    Budget = "1000$",
                    EligibleActivities = "Any",
                    Finances = "All"
                },
                new()
                {
                    Id = "2",
                    ActivePeriod = "2 months",
                    Domain = "Domain2",
                    EligibleApplicants = "EA2",
                    Purpose = "Purpose2",
                    MoreInfo = "no more info",
                    Budget = "2000$",
                    EligibleActivities = "Any",
                    Finances = "All"
                },
                new()
                {
                    Id = "3",
                    ActivePeriod = "3 months",
                    Domain = "Domain3",
                    EligibleApplicants = "EA3",
                    Purpose = "Purpose3",
                    MoreInfo = "no more info",
                    Budget = "3000$",
                    EligibleActivities = "Any",
                    Finances = "All"
                }
            };
            return mockData;
        }
    }
}