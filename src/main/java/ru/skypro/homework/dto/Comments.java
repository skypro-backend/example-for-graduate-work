package ru.skypro.homework.dto;

import java.util.List;

public class Comments {
    private int count;
    private List<Comment> results;
    public Comments(int count, List<Comment> results) {
        this.count = count;
        this.results = results;
    }

    public Comments() {
    }

    public static Comments of(List<Comment> results) {
        Comments responseWrapperCommentDTO = new Comments();
        if (results == null) {
            return responseWrapperCommentDTO;
        }
        responseWrapperCommentDTO.results = results;
        responseWrapperCommentDTO.count = results.size();
        return responseWrapperCommentDTO;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }
}
