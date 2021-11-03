# brainstorm-backend

# Steps to run locally
1. Run the docker-compose file in postgres-devel
2. (Optional) Open pgAdmin on localhost:15432 and login with credentials:
    - admin@pgadmin.com
    - password
3. Create an appsettings.json file in Brainstorm.API which should look like:
```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft": "Warning",
      "Microsoft.Hosting.Lifetime": "Information"
    }
  },
  "AllowedHosts": "*",
  "ConnectionStrings": {
    "SqlConnection": "host=localhost;user id=postgres;password=postgres;database=brainstorm;Pooling=false;Timeout=300;CommandTimeout=300;"
  }
}
```
4. Put in SqlConnection the host from your PC
5. Put the same connection string in Brainstorm.Migrations in DesignTimeContextFactory.cs
6. Install the dotnet sdk and the dotnet-ef package(if not on Visual Studio)
7. **In Brainstorm.Migrations** run the migrations with either `dotnet ef database update`(if on CLI) or `Update-Database' if on Visual Studio in the package manager console
8. Build the project
9. Run Brainstorm.API/Program.cs