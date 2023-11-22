package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * CreateOrUpdateComment
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-05T13:56:03.406359+03:00[Europe/Moscow]")
public class CreateOrUpdateCommentDTO {
  @JsonProperty("text")
  private String text;

  public CreateOrUpdateCommentDTO text(String text) {
    this.text = text;
    return this;
  }

  /**
   * текст комментария
   * @return text
  */
  @ApiModelProperty(required = true, value = "текст комментария")
  @NotNull

@Size(min = 8, max = 64) 
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
    CreateOrUpdateCommentDTO createOrUpdateCommentDTO = (CreateOrUpdateCommentDTO) o;
    return Objects.equals(this.text, createOrUpdateCommentDTO.text);
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

