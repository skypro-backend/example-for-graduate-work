package ru.skypro.homework.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentsDTO {

    private Integer count;
    private List<CommentDTO> results;


    public CommentsDTO count(Integer count) {
        this.count = count;
        return this;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CommentDTO> getResults() {
        return results;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentsDTO that = (CommentsDTO) o;
        return Objects.equals(count, that.count) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
