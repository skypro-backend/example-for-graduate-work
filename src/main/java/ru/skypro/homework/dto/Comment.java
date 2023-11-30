package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int authorId;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private int pkIdComment;
    private String text;

//    public Comment(int authorId, String authorImage, String authorFirstName, Long createdAt, int pkIdComment, String text) {
//        this.authorId = authorId;
//        this.authorImage = authorImage;
//        this.authorFirstName = authorFirstName;
//        this.createdAt = createdAt;
//        this.pkIdComment = pkIdComment;
//        this.text = text;
//    }
//
//    public int getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(int authorId) {
//        this.authorId = authorId;
//    }
//
//    public String getAuthorImage() {
//        return authorImage;
//    }
//
//    public void setAuthorImage(String authorImage) {
//        this.authorImage = authorImage;
//    }
//
//    public String getAuthorFirstName() {
//        return authorFirstName;
//    }
//
//    public void setAuthorFirstName(String authorFirstName) {
//        this.authorFirstName = authorFirstName;
//    }
//
//    public Long getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Long createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public int getPkIdComment() {
//        return pkIdComment;
//    }
//
//    public void setPkIdComment(int pkIdComment) {
//        this.pkIdComment = pkIdComment;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
}
