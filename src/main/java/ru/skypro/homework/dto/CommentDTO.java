package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Comment
 */

public class CommentDTO {
    @JsonProperty("author")
    private Long author;

    @JsonProperty("authorImage")
    private String authorImage;

    @JsonProperty("authorFirstName")
    private String authorFirstName;

    @JsonProperty("createdAt")
    private Long createdAt;

    @JsonProperty("pk")
    private Long pk;

    @JsonProperty("text")
    private String text;

    public CommentDTO author(Long author) {
        this.author = author;
        return this;
    }

    /**
     * id автора комментария
     *
     * @return author
     */
    @ApiModelProperty(value = "id автора комментария")


    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public CommentDTO authorImage(String authorImage) {
        this.authorImage = authorImage;
        return this;
    }

    /**
     * ссылка на аватар автора комментария
     *
     * @return authorImage
     */
    @ApiModelProperty(value = "ссылка на аватар автора комментария")


    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public CommentDTO authorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    /**
     * имя создателя комментария
     *
     * @return authorFirstName
     */
    @ApiModelProperty(value = "имя создателя комментария")


    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public CommentDTO createdAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     *
     * @return createdAt
     */
    @ApiModelProperty(value = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")


    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public CommentDTO pk(Long pk) {
        this.pk = pk;
        return this;
    }

    /**
     * id комментария
     *
     * @return pk
     */
    @ApiModelProperty(value = "id комментария")


    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public CommentDTO text(String text) {
        this.text = text;
        return this;
    }

    /**
     * текст комментария
     *
     * @return text
     */
    @ApiModelProperty(value = "текст комментария")


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
        CommentDTO commentDTO = (CommentDTO) o;
        return Objects.equals(this.author, commentDTO.author) &&
                Objects.equals(this.authorImage, commentDTO.authorImage) &&
                Objects.equals(this.authorFirstName, commentDTO.authorFirstName) &&
                Objects.equals(this.createdAt, commentDTO.createdAt) &&
                Objects.equals(this.pk, commentDTO.pk) &&
                Objects.equals(this.text, commentDTO.text);
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

