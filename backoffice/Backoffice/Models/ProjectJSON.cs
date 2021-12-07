using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace BackofficeComponent.Models
{
    public class ProjectJson
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; }
        
        [BsonElement("activePeriod")]
        public string ActivePeriod { get; set; }
        
        [BsonElement("domain")]
        public string Domain { get; set; }
        
        [BsonElement("eligibleApplicants")]
        public string EligibleApplicants { get; set; }
        
        [BsonElement("purpose")]
        public string Purpose { get; set; }
        
        [BsonElement("moreInfo")]
        public string MoreInfo { get; set; }
        
        [BsonElement("budget")]
        public string Budget { get; set; }
        
        [BsonElement("eligibleActivities")]
        public string EligibleActivities { get; set; }
        
        [BsonElement("finances")]
        public string Finances { get; set; }

        public object Clone()
        {
            return this.MemberwiseClone();
        }
    }
}
