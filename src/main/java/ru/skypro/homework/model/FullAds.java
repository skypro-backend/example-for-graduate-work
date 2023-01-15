/*
 * Api Documentation
 * Api Documentation
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package ru.skypro.homework.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ru.skypro.homework.JSON;

/**
 * FullAds
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-01-15T17:15:49.540122300+03:00[Europe/Moscow]")
public class FullAds {
  public static final String SERIALIZED_NAME_AUTHOR_FIRST_NAME = "authorFirstName";
  @SerializedName(SERIALIZED_NAME_AUTHOR_FIRST_NAME)
  private String authorFirstName;

  public static final String SERIALIZED_NAME_AUTHOR_LAST_NAME = "authorLastName";
  @SerializedName(SERIALIZED_NAME_AUTHOR_LAST_NAME)
  private String authorLastName;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_IMAGE = "image";
  @SerializedName(SERIALIZED_NAME_IMAGE)
  private List<String> image = null;

  public static final String SERIALIZED_NAME_PHONE = "phone";
  @SerializedName(SERIALIZED_NAME_PHONE)
  private String phone;

  public static final String SERIALIZED_NAME_PK = "pk";
  @SerializedName(SERIALIZED_NAME_PK)
  private Integer pk;

  public static final String SERIALIZED_NAME_PRICE = "price";
  @SerializedName(SERIALIZED_NAME_PRICE)
  private Integer price;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;

  public FullAds() {
  }

  public FullAds authorFirstName(String authorFirstName) {
    
    this.authorFirstName = authorFirstName;
    return this;
  }

   /**
   * Get authorFirstName
   * @return authorFirstName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getAuthorFirstName() {
    return authorFirstName;
  }


  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }


  public FullAds authorLastName(String authorLastName) {
    
    this.authorLastName = authorLastName;
    return this;
  }

   /**
   * Get authorLastName
   * @return authorLastName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getAuthorLastName() {
    return authorLastName;
  }


  public void setAuthorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
  }


  public FullAds description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public FullAds email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public FullAds image(List<String> image) {
    
    this.image = image;
    return this;
  }

  public FullAds addImageItem(String imageItem) {
    if (this.image == null) {
      this.image = new ArrayList<>();
    }
    this.image.add(imageItem);
    return this;
  }

   /**
   * Get image
   * @return image
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getImage() {
    return image;
  }


  public void setImage(List<String> image) {
    this.image = image;
  }


  public FullAds phone(String phone) {
    
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getPhone() {
    return phone;
  }


  public void setPhone(String phone) {
    this.phone = phone;
  }


  public FullAds pk(Integer pk) {
    
    this.pk = pk;
    return this;
  }

   /**
   * Get pk
   * @return pk
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getPk() {
    return pk;
  }


  public void setPk(Integer pk) {
    this.pk = pk;
  }


  public FullAds price(Integer price) {
    
    this.price = price;
    return this;
  }

   /**
   * Get price
   * @return price
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getPrice() {
    return price;
  }


  public void setPrice(Integer price) {
    this.price = price;
  }


  public FullAds title(String title) {
    
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FullAds fullAds = (FullAds) o;
    return Objects.equals(this.authorFirstName, fullAds.authorFirstName) &&
        Objects.equals(this.authorLastName, fullAds.authorLastName) &&
        Objects.equals(this.description, fullAds.description) &&
        Objects.equals(this.email, fullAds.email) &&
        Objects.equals(this.image, fullAds.image) &&
        Objects.equals(this.phone, fullAds.phone) &&
        Objects.equals(this.pk, fullAds.pk) &&
        Objects.equals(this.price, fullAds.price) &&
        Objects.equals(this.title, fullAds.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorFirstName, authorLastName, description, email, image, phone, pk, price, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FullAds {\n");
    sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
    sb.append("    authorLastName: ").append(toIndentedString(authorLastName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("authorFirstName");
    openapiFields.add("authorLastName");
    openapiFields.add("description");
    openapiFields.add("email");
    openapiFields.add("image");
    openapiFields.add("phone");
    openapiFields.add("pk");
    openapiFields.add("price");
    openapiFields.add("title");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to FullAds
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (!FullAds.openapiRequiredFields.isEmpty()) { // has required fields but JSON object is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in FullAds is not found in the empty JSON string", FullAds.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!FullAds.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `FullAds` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if ((jsonObj.get("authorFirstName") != null && !jsonObj.get("authorFirstName").isJsonNull()) && !jsonObj.get("authorFirstName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `authorFirstName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("authorFirstName").toString()));
      }
      if ((jsonObj.get("authorLastName") != null && !jsonObj.get("authorLastName").isJsonNull()) && !jsonObj.get("authorLastName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `authorLastName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("authorLastName").toString()));
      }
      if ((jsonObj.get("description") != null && !jsonObj.get("description").isJsonNull()) && !jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      if ((jsonObj.get("email") != null && !jsonObj.get("email").isJsonNull()) && !jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("image") != null && !jsonObj.get("image").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `image` to be an array in the JSON string but got `%s`", jsonObj.get("image").toString()));
      }
      if ((jsonObj.get("phone") != null && !jsonObj.get("phone").isJsonNull()) && !jsonObj.get("phone").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `phone` to be a primitive type in the JSON string but got `%s`", jsonObj.get("phone").toString()));
      }
      if ((jsonObj.get("title") != null && !jsonObj.get("title").isJsonNull()) && !jsonObj.get("title").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `title` to be a primitive type in the JSON string but got `%s`", jsonObj.get("title").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!FullAds.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'FullAds' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<FullAds> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(FullAds.class));

       return (TypeAdapter<T>) new TypeAdapter<FullAds>() {
           @Override
           public void write(JsonWriter out, FullAds value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public FullAds read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of FullAds given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of FullAds
  * @throws IOException if the JSON string is invalid with respect to FullAds
  */
  public static FullAds fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, FullAds.class);
  }

 /**
  * Convert an instance of FullAds to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

