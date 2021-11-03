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

Test data input - https://www.adrnordest.ro/storage/2021/10/catalog_surse_finantare_nr_27.pdf

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
- the service will call Backoffice in order to persist data in the DB

### Backoffice component:
- will provide a POST endpoint to be called internally by Semantic Analysis Component
- will provide some public GET endpoints in order to be used by UI (will fetch data from DB)
- a service that will contain possible business logic before the data is stored
- a Dao class that will be used to call the DB

### UI component:
- single page app that will display a project list 
- will call the GET endpoints from the Backoffice to get the data
- will provide filtering options
    
==================================================================

## Lab 4:

#### Basic implementation for the Semantic Analysis component:
- Spring framework setup, controller and services setup - Ervin
- Processor interface and concrete processors dummy implementation - Rosu
- Using the Spring framework natively allows us to make use of the Singleton (Spring beans), Decorator (annotations) and Chain of responsability (exception handling) design patterns
- Additionally, we use Builder for creating our JSON objects, Facade for using interfaces and Proxy for exposing our REST API, for now

#### Basic implementation for the Backoffice component - Rusu:
- Uses Repository pattern to model the DAO
- Repository interface and Repository start of implementation for MongoDB database
- Uses Chain of Responsibility pattern to to implement API functionality
- Offers services as Singletons (usual practice for .NET APIs)

==================================================================

## Lab 5:

#### Two iterations of TDD for EligibleApplicantsProcessor and MoreInfoProcessor:
- Ervin Maftei

#### Two iterations of TDD for BudgetProcessor and PurposeProcessor:
- Cristi Rosu

#### Two iterations of TDD for Get and Post endpoints:
- Cristi Rusu

==================================================================
