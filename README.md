# SpringBootBitbucketRepoClonerApi


This is a sample cloner rest api project which uses spring boot and activemq.
You can change mail smtp configurations from application.yml file.


# Test

You can post the sample json request to the url: localhost:8080//api/repo/clone


{
  
    "bitbucketServerUrl" :"http://stash.domain.com",
    "localRepoDirectory" :"/Users/enes.acikoglu/Desktop/Projects/Trendyol",
    "userName" : "enes",
    "password" : "password",
    "projectCount" : "61",
    "projectKey" : "MAR"
}

    