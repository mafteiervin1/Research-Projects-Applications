using System;

namespace BackofficeComponent.Models
{
    public class RpaDatabaseSettings : IRpaDatabaseSettings
    {
        public String ProjectsCollectionName { get; set; }
        public String ConnectionString { get; set; }
        public String DatabaseName { get; set; }
    }
    
    public interface IRpaDatabaseSettings
    {
        String ProjectsCollectionName { get; set; }
        String ConnectionString { get; set; }
        String DatabaseName { get; set; }
    }
}