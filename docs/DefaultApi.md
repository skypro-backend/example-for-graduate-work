# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addAds**](DefaultApi.md#addAds) | **POST** /ads | addAds |
| [**addComments**](DefaultApi.md#addComments) | **POST** /ads/{ad_pk}/comments | addComments |
| [**deleteComments**](DefaultApi.md#deleteComments) | **DELETE** /ads/{ad_pk}/comments/{id} | deleteComments |
| [**getALLAds**](DefaultApi.md#getALLAds) | **GET** /ads |  |
| [**getAds**](DefaultApi.md#getAds) | **GET** /ads/{id} | getFullAd |
| [**getAdsMeUsingGET**](DefaultApi.md#getAdsMeUsingGET) | **GET** /ads/me | getAdsMe |
| [**getComments**](DefaultApi.md#getComments) | **GET** /ads/{ad_pk}/comments | getComments |
| [**getComments1**](DefaultApi.md#getComments1) | **GET** /ads/{ad_pk}/comments/{id} | getComments |
| [**getUser1**](DefaultApi.md#getUser1) | **GET** /users/me | getUser |
| [**login**](DefaultApi.md#login) | **POST** /login | login |
| [**register**](DefaultApi.md#register) | **POST** /register | register |
| [**removeAds**](DefaultApi.md#removeAds) | **DELETE** /ads/{id} | removeAds |
| [**setPassword**](DefaultApi.md#setPassword) | **POST** /users/set_password | setPassword |
| [**updateAds**](DefaultApi.md#updateAds) | **PATCH** /ads/{id} | updateAds |
| [**updateComments**](DefaultApi.md#updateComments) | **PATCH** /ads/{ad_pk}/comments/{id} | updateComments |
| [**updateImage**](DefaultApi.md#updateImage) | **PATCH** /image/{id} | updateAdsImage |
| [**updateUser**](DefaultApi.md#updateUser) | **PATCH** /users/me | updateUser |
| [**updateUserImage**](DefaultApi.md#updateUserImage) | **PATCH** /users/me/image | updateUserImage |


<a name="addAds"></a>
# **addAds**
> Ads addAds(properties, image)

addAds

### Example
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

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **properties** | [**CreateAds**](CreateAds.md)|  | |
| **image** | **File**|  | |

### Return type

[**Ads**](Ads.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |
| **404** | Not Found |  -  |
| **403** | Forbidden |  -  |
| **401** | Unauthorized |  -  |

<a name="addComments"></a>
# **addComments**
> Comment addComments(adPk, comment)

addComments

### Example
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
    String adPk = "adPk_example"; // String | 
    Comment comment = new Comment(); // Comment | 
    try {
      Comment result = apiInstance.addComments(adPk, comment);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#addComments");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **adPk** | **String**|  | |
| **comment** | [**Comment**](Comment.md)|  | |

### Return type

[**Comment**](Comment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |
| **403** | Forbidden |  -  |
| **401** | Unauthorized |  -  |

<a name="deleteComments"></a>
# **deleteComments**
> deleteComments(adPk, id)

deleteComments

### Example
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
    String adPk = "adPk_example"; // String | 
    Integer id = 56; // Integer | 
    try {
      apiInstance.deleteComments(adPk, id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#deleteComments");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **adPk** | **String**|  | |
| **id** | **Integer**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |
| **403** | Forbidden |  -  |
| **401** | Unauthorized |  -  |

<a name="getALLAds"></a>
# **getALLAds**
> ResponseWrapperAds getALLAds()



### Example
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
    try {
      ResponseWrapperAds result = apiInstance.getALLAds();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getALLAds");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ResponseWrapperAds**](ResponseWrapperAds.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a name="getAds"></a>
# **getAds**
> FullAds getAds(id)

getFullAd

### Example
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
    Integer id = 56; // Integer | 
    try {
      FullAds result = apiInstance.getAds(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getAds");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |

### Return type

[**FullAds**](FullAds.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

<a name="getAdsMeUsingGET"></a>
# **getAdsMeUsingGET**
> ResponseWrapperAds getAdsMeUsingGET(authenticated, authorities0Authority, credentials, details, principal)

getAdsMe

### Example
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
    Boolean authenticated = true; // Boolean | 
    String authorities0Authority = "authorities0Authority_example"; // String | 
    Object credentials = null; // Object | 
    Object details = null; // Object | 
    Object principal = null; // Object | 
    try {
      ResponseWrapperAds result = apiInstance.getAdsMeUsingGET(authenticated, authorities0Authority, credentials, details, principal);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getAdsMeUsingGET");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **authenticated** | **Boolean**|  | [optional] |
| **authorities0Authority** | **String**|  | [optional] |
| **credentials** | [**Object**](.md)|  | [optional] |
| **details** | [**Object**](.md)|  | [optional] |
| **principal** | [**Object**](.md)|  | [optional] |

### Return type

[**ResponseWrapperAds**](ResponseWrapperAds.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not Found |  -  |

<a name="getComments"></a>
# **getComments**
> ResponseWrapperComment getComments(adPk)

getComments

### Example
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
    String adPk = "adPk_example"; // String | 
    try {
      ResponseWrapperComment result = apiInstance.getComments(adPk);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getComments");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **adPk** | **String**|  | |

### Return type

[**ResponseWrapperComment**](ResponseWrapperComment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

<a name="getComments1"></a>
# **getComments1**
> Comment getComments1(adPk, id)

getComments

### Example
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
    String adPk = "adPk_example"; // String | 
    Integer id = 56; // Integer | 
    try {
      Comment result = apiInstance.getComments1(adPk, id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getComments1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **adPk** | **String**|  | |
| **id** | **Integer**|  | |

### Return type

[**Comment**](Comment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

<a name="getUser1"></a>
# **getUser1**
> User getUser1()

getUser

### Example
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
    try {
      User result = apiInstance.getUser1();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getUser1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not Found |  -  |

<a name="login"></a>
# **login**
> Object login(loginReq)

login

### Example
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
    LoginReq loginReq = new LoginReq(); // LoginReq | 
    try {
      Object result = apiInstance.login(loginReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#login");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **loginReq** | [**LoginReq**](LoginReq.md)|  | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |

<a name="register"></a>
# **register**
> register(registerReq)

register

### Example
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
    RegisterReq registerReq = new RegisterReq(); // RegisterReq | 
    try {
      apiInstance.register(registerReq);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#register");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **registerReq** | [**RegisterReq**](RegisterReq.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Not Found |  -  |
| **201** | Created |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |

<a name="removeAds"></a>
# **removeAds**
> removeAds(id)

removeAds

### Example
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
    Integer id = 56; // Integer | 
    try {
      apiInstance.removeAds(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#removeAds");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | No Content |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |

<a name="setPassword"></a>
# **setPassword**
> NewPassword setPassword(newPassword)

setPassword

### Example
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
    NewPassword newPassword = new NewPassword(); // NewPassword | 
    try {
      NewPassword result = apiInstance.setPassword(newPassword);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#setPassword");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **newPassword** | [**NewPassword**](NewPassword.md)|  | |

### Return type

[**NewPassword**](NewPassword.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not Found |  -  |

<a name="updateAds"></a>
# **updateAds**
> Ads updateAds(id, createAds)

updateAds

### Example
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
    Integer id = 56; // Integer | 
    CreateAds createAds = new CreateAds(); // CreateAds | 
    try {
      Ads result = apiInstance.updateAds(id, createAds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#updateAds");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |
| **createAds** | [**CreateAds**](CreateAds.md)|  | |

### Return type

[**Ads**](Ads.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |
| **403** | Forbidden |  -  |
| **401** | Unauthorized |  -  |

<a name="updateComments"></a>
# **updateComments**
> Comment updateComments(adPk, id, comment)

updateComments

### Example
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
    String adPk = "adPk_example"; // String | 
    Integer id = 56; // Integer | 
    Comment comment = new Comment(); // Comment | 
    try {
      Comment result = apiInstance.updateComments(adPk, id, comment);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#updateComments");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **adPk** | **String**|  | |
| **id** | **Integer**|  | |
| **comment** | [**Comment**](Comment.md)|  | |

### Return type

[**Comment**](Comment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |
| **403** | Forbidden |  -  |
| **401** | Unauthorized |  -  |

<a name="updateImage"></a>
# **updateImage**
> List&lt;byte[]&gt; updateImage(id, image)

updateAdsImage

### Example
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
    Integer id = 56; // Integer | 
    File image = new File("/path/to/file"); // File | 
    try {
      List<byte[]> result = apiInstance.updateImage(id, image);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#updateImage");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Integer**|  | |
| **image** | **File**|  | |

### Return type

**List&lt;byte[]&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/octet-stream

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

<a name="updateUser"></a>
# **updateUser**
> User updateUser(user)

updateUser

### Example
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
    User user = new User(); // User | 
    try {
      User result = apiInstance.updateUser(user);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#updateUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **user** | [**User**](User.md)|  | |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **204** | No Content |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |
| **404** | Not Found |  -  |

<a name="updateUserImage"></a>
# **updateUserImage**
> updateUserImage(image)

updateUserImage

UpdateUserImage

### Example
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
    File image = new File("/path/to/file"); // File | 
    try {
      apiInstance.updateUserImage(image);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#updateUserImage");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **image** | **File**|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

