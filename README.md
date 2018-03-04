# SpringBootBitbucketRepoClonerApi

First of All thanks to archenroot https://gist.github.com/archenroot/1d11f58d182163ce55bdafcdfe11d411

This is a sample cloner rest api project which uses spring boot ,unirest and jgit api.

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

    
