package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Comments
 */

public class CommentsDTO {
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("results")
    @Valid
    private List<CommentDTO> results;

    public CommentsDTO count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * общее количество комментариев
     *
     * @return count
     */
    @ApiModelProperty(value = "общее количество комментариев")


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CommentsDTO results(List<CommentDTO> results) {
        this.results = results;
        return this;
    }

    public CommentsDTO addResultsItem(CommentDTO resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     *
     * @return results
     */
    @ApiModelProperty(value = "")

    @Valid

    public List<CommentDTO> getResults() {
        return results;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentsDTO commentsDTO = (CommentsDTO) o;
        return Objects.equals(this.count, commentsDTO.count) &&
                Objects.equals(this.results, commentsDTO.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Comments {\n");

        sb.append("    count: ").append(toIndentedString(count)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
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

