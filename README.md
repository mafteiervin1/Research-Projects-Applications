# Research-Projects-Applications

FII's Master students ASET Research and Develop Projects Applications project

Working team

Ervin Maftei (MLC) - emaftei5@gmail.com

Cristi Rusu (MLC) - cristirusu99@gmail.com

Cristi Rosu (MLC) - cristian.rosu453@gmail.com

Balan Maria (MLC) - balanmaria1998@gmail.com

Team coordinator: Adrian Iftene

Google Docs - https://docs.google.com/document/d/1xngJoZXihOY_8WfmkG23SB8DYLjKGaxQAoomkV4o9kc/edit?usp=sharing

Jira Board - https://researchprojectsapplications.atlassian.net/jira/software/projects/RPA/boards/1


==================================================================

## Lab 3:

Ervin: Semantic Analysis component (Java)

Rusu: Backoffice component (.Net) + UI (.Net)

Rosu: Crawling component (Python)
 
### Crawling component:
- a quartz job that will frequently try to crawl sites (stored in a config file)
- an abstract crawler class that will have common methods for all specific crawlers
- concrete crawler for each site that will extend the abstract crawler
- the output of this component will be a list of Strings containing projects

### Semantic analysis component:
- will provide a POST endpoint in order to be called by Crawling component
- output will be a list of JsonProject Objects 
- a service that will extract properties via individual processors from the input (List of strings) and will build the output
- the service will call backoffice in order to persist date in the DB

### Backoffice component:
- will provide a POST endpoint to be call internally by Semantic Analysis Component
- will provide some public GET endpoints in order to be used by UI (will fetch data from DB)
- a service that will contain possible business logic before the data is stored
- a Dao class that will be used to call the DB

### UI component:
- single page app that will display a project list 
- will call the GET endpoints from the DB to get the data
- will provide filtering options
    
==================================================================
