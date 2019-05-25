# SpringBootBitbucketRepoClonerApi

First of All thanks to archenroot https://gist.github.com/archenroot/1d11f58d182163ce55bdafcdfe11d411

This is a sample cloner rest api project which uses spring boot ,unirest and jgit api.

## Build

Run 'mvn clean install'

## Run 
After build step run 'mvn spring-boot:run'

# Test

You can post the sample json request to the url: localhost:8080/api/repo/clone


{
  
    "bitbucketServerUrl" :"http://stash.domain.com",
    "localRepoDirectory" :"/Users/enes.acikoglu/Desktop/Projects/Trendyol",
    "userName" : "enes",
    "password" : "password",
    "projectCount" : 61,
    "projectKey" : "MAR"
}

This post request will pull all projects from stash url under the MAR project to '/Users/enes.acikoglu/Desktop/Projects/Trendyol' folder path.
    

Repo welcome to any pr or future.
Please do not hesitate to contact with me.