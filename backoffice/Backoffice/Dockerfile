FROM mcr.microsoft.com/dotnet/sdk:5.0 AS build
WORKDIR /source
#ENV ASPNETCORE_URLS=http://+:8090
EXPOSE 8090
EXPOSE 27015
EXPOSE 27017

# copy csproj and restore as distinct layers
COPY /*.csproj ./
RUN dotnet restore

# copy everything else and build app
COPY /. ./
WORKDIR /source
RUN dotnet publish -c release -o /app --no-restore

# final stage/image
FROM mcr.microsoft.com/dotnet/aspnet:5.0
WORKDIR /app
COPY --from=build /app ./
ENV ASPNETCORE_URLS=http://+:80
ENTRYPOINT ["dotnet", "BackofficeComponent.dll","--server.urls", "http://+:8090"]