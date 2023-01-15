# openapi-java-client

Api Documentation
- API version: 1.0
  - Build date: 2023-01-15T17:15:49.540122300+03:00[Europe/Moscow]

Api Documentation


*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-java-client</artifactId>
  <version>1.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'openapi-java-client' jar has been published to maven central.
    mavenLocal()       // Needed if the 'openapi-java-client' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "org.openapitools:openapi-java-client:1.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/openapi-java-client-1.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import ru.skypro.homework.ApiClient;
import ru.skypro.homework.ApiException;
import ru.skypro.homework.Configuration;
import ru.skypro.homework.models.*;
import ru.skypro.homework.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    CreateAds properties = new CreateAds(); // CreateAds | 
    File image = new File("/path/to/file"); // File | 
    try {
      Ads result = apiInstance.addAds(properties, image);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#addAds");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**addAds**](docs/DefaultApi.md#addAds) | **POST** /ads | addAds
*DefaultApi* | [**addComments**](docs/DefaultApi.md#addComments) | **POST** /ads/{ad_pk}/comments | addComments
*DefaultApi* | [**deleteComments**](docs/DefaultApi.md#deleteComments) | **DELETE** /ads/{ad_pk}/comments/{id} | deleteComments
*DefaultApi* | [**getALLAds**](docs/DefaultApi.md#getALLAds) | **GET** /ads | 
*DefaultApi* | [**getAds**](docs/DefaultApi.md#getAds) | **GET** /ads/{id} | getFullAd
*DefaultApi* | [**getAdsMeUsingGET**](docs/DefaultApi.md#getAdsMeUsingGET) | **GET** /ads/me | getAdsMe
*DefaultApi* | [**getComments**](docs/DefaultApi.md#getComments) | **GET** /ads/{ad_pk}/comments | getComments
*DefaultApi* | [**getComments1**](docs/DefaultApi.md#getComments1) | **GET** /ads/{ad_pk}/comments/{id} | getComments
*DefaultApi* | [**getUser1**](docs/DefaultApi.md#getUser1) | **GET** /users/me | getUser
*DefaultApi* | [**login**](docs/DefaultApi.md#login) | **POST** /login | login
*DefaultApi* | [**register**](docs/DefaultApi.md#register) | **POST** /register | register
*DefaultApi* | [**removeAds**](docs/DefaultApi.md#removeAds) | **DELETE** /ads/{id} | removeAds
*DefaultApi* | [**setPassword**](docs/DefaultApi.md#setPassword) | **POST** /users/set_password | setPassword
*DefaultApi* | [**updateAds**](docs/DefaultApi.md#updateAds) | **PATCH** /ads/{id} | updateAds
*DefaultApi* | [**updateComments**](docs/DefaultApi.md#updateComments) | **PATCH** /ads/{ad_pk}/comments/{id} | updateComments
*DefaultApi* | [**updateImage**](docs/DefaultApi.md#updateImage) | **PATCH** /image/{id} | updateAdsImage
*DefaultApi* | [**updateUser**](docs/DefaultApi.md#updateUser) | **PATCH** /users/me | updateUser
*DefaultApi* | [**updateUserImage**](docs/DefaultApi.md#updateUserImage) | **PATCH** /users/me/image | updateUserImage


## Documentation for Models

 - [Ads](docs/Ads.md)
 - [Comment](docs/Comment.md)
 - [CreateAds](docs/CreateAds.md)
 - [FullAds](docs/FullAds.md)
 - [LoginReq](docs/LoginReq.md)
 - [NewPassword](docs/NewPassword.md)
 - [RegisterReq](docs/RegisterReq.md)
 - [ResponseWrapperAds](docs/ResponseWrapperAds.md)
 - [ResponseWrapperComment](docs/ResponseWrapperComment.md)
 - [User](docs/User.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



