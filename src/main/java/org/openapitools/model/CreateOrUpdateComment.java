package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * CreateOrUpdateComment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T13:35:35.083731400+03:00[Europe/Moscow]")
public class CreateOrUpdateComment {

  private String text;

  /**
   * Default constructor
   * @deprecated Use {@link CreateOrUpdateComment#CreateOrUpdateComment(String)}
   */
  @Deprecated
  public CreateOrUpdateComment() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateOrUpdateComment(String text) {
    this.text = text;
  }

  public CreateOrUpdateComment text(String text) {
    this.text = text;
    return this;
  }

  /**
   * текст комментария
   * @return text
  */
  @NotNull @Size(min = 8, max = 64) 
  @Schema(name = "text", description = "текст комментария", requiredMode = Schema.RequiredMode.REQUIRED)
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
    CreateOrUpdateComment createOrUpdateComment = (CreateOrUpdateComment) o;
    return Objects.equals(this.text, createOrUpdateComment.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateOrUpdateComment {\n");
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

