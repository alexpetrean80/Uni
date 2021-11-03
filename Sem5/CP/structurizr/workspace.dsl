workspace {

    model {
        user = person "User"
        softwareSystem = softwareSystem "Brainstorm App" {
            frontend = container "Frontend" "" "AngularJS" "Web"{
                
            }
            database = container "Database" "" "PostgreSQL" "DB"
    
            api = container "Brainstorm.API" {
                userCtrl = component "UserController"
                orgCtrl = component "OrganizationController"
                topicCtrl = component "TopicsController"
                projCtrl = component "ProjectController"
                
                
            }
            business = container "Brainstorm.Business"
            entities = container "Brainstorm.Entities"
            testing = container "Brainstorm.Testing"
            migrations = container "Brainstorm.Migrations" "Store database migrations"
                
            api -> business "delegates tasks to be executed" "Mediatr"
            business -> api "returns the result of the task" "Mediatr"
            entities -> database "Gets data" "EF Core"
                
            testing -> business "performs unit tests"
            testing -> api "performs unit tests"
                
            entities -> business "provides data models to"
        
            migrations -> database "applies migrations to"
            
            

        user -> softwareSystem "Uses"
        frontend -> api "Makes requests" "REST/JSON"
        api -> frontend "Responds" "REST/JSON"
        }
    }

    views {
        systemContext softwareSystem {
            include *
            autolayout
        }
        
        container softwareSystem {
            include *
            autolayout lr
        }
        
        component api {
            include *
            autolayout lr
        }
        
        styles {
            element "DB" {
                shape cylinder
            }
            
            element "Web" {
                shape WebBrowser
            }
        }
        theme default
    }
    
}
