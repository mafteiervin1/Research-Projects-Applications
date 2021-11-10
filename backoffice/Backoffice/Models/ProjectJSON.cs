using System;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BackofficeComponent.Models
{
    public class ProjectJson
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public String Id { get; set; }
        
        [BsonElement("activePeriod")]
        public String ActivePeriod { get; set; }
        
        [BsonElement("domain")]
        public String Domain { get; set; }
        
        [BsonElement("eligibleApplicants")]
        public String EligibleApplicants { get; set; }
        
        [BsonElement("purpose")]
        public String Purpose { get; set; }
        
        [BsonElement("moreInfo")]
        public String MoreInfo { get; set; }
        
        [BsonElement("budget")]
        public String Budget { get; set; }
    }
}
