FROM mcr.microsoft.com/dotnet/sdk:5.0 AS build
WORKDIR /app

COPY ./Brainstorm.sln .
COPY ./Brainstorm.API/Brainstorm.API.csproj ./Brainstorm.API/Brainstorm.API.csproj
COPY ./Brainstorm.Business/Brainstorm.Business.csproj ./Brainstorm.Business/Brainstorm.Business.csproj
COPY ./Brainstorm.Data/Brainstorm.Data.csproj ./Brainstorm.Data/Brainstorm.Data.csproj
COPY ./Brainstorm.UnitTests/Brainstorm.UnitTests.csproj ./Brainstorm.UnitTests/Brainstorm.UnitTests.csproj
COPY ./Brainstorm.Migrations/Brainstorm.Migrations.csproj ./Brainstorm.Migrations/Brainstorm.Migrations.csproj

RUN dotnet restore

COPY Brainstorm.API ./Brainstorm.API
COPY Brainstorm.Business ./Brainstorm.Business
COPY Brainstorm.Data ./Brainstorm.Data
COPY Brainstorm.UnitTests ./Brainstorm.UnitTests
COPY Brainstorm.Migrations ./Brainstorm.Migrations


WORKDIR /app
RUN dotnet publish -c Release -o out

FROM mcr.microsoft.com/dotnet/aspnet:5.0 AS runtime
WORKDIR /app
COPY --from=build /app/out/Brainstorm.API.dll ./Brainstorm.API.dll
ENTRYPOINT ["dotnet", "./Brainstorm.API.dll"]