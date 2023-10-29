package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * Comment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class Comment {

  private Integer author;

  private String authorImage;

  private String authorFirstName;

  private Long createdAt;

  private Integer pk;

  private String text;

  public Comment author(Integer author) {
    this.author = author;
    return this;
  }

  /**
   * id автора комментария
   * @return author
  */
  
  @Schema(name = "author", description = "id автора комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("author")
  public Integer getAuthor() {
    return author;
  }

  public void setAuthor(Integer author) {
    this.author = author;
  }

  public Comment authorImage(String authorImage) {
    this.authorImage = authorImage;
    return this;
  }

  /**
   * ссылка на аватар автора комментария
   * @return authorImage
  */
  
  @Schema(name = "authorImage", description = "ссылка на аватар автора комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorImage")
  public String getAuthorImage() {
    return authorImage;
  }

  public void setAuthorImage(String authorImage) {
    this.authorImage = authorImage;
  }

  public Comment authorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
    return this;
  }

  /**
   * имя создателя комментария
   * @return authorFirstName
  */
  
  @Schema(name = "authorFirstName", description = "имя создателя комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorFirstName")
  public String getAuthorFirstName() {
    return authorFirstName;
  }

  public void setAuthorFirstName(String authorFirstName) {
    this.authorFirstName = authorFirstName;
  }

  public Comment createdAt(Long createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
   * @return createdAt
  */
  
  @Schema(name = "createdAt", description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public Comment pk(Integer pk) {
    this.pk = pk;
    return this;
  }

  /**
   * id комментария
   * @return pk
  */
  
  @Schema(name = "pk", description = "id комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pk")
  public Integer getPk() {
    return pk;
  }

  public void setPk(Integer pk) {
    this.pk = pk;
  }

  public Comment text(String text) {
    this.text = text;
    return this;
  }

  /**
   * текст комментария
   * @return text
  */
  
  @Schema(name = "text", description = "текст комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(this.author, comment.author) &&
        Objects.equals(this.authorImage, comment.authorImage) &&
        Objects.equals(this.authorFirstName, comment.authorFirstName) &&
        Objects.equals(this.createdAt, comment.createdAt) &&
        Objects.equals(this.pk, comment.pk) &&
        Objects.equals(this.text, comment.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    authorImage: ").append(toIndentedString(authorImage)).append("\n");
    sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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
}

