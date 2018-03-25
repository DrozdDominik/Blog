package pl.akademiakodu.miniblog.model.dtos;

import java.util.Date;

public class PostCommentDto {
    private Long id;
    private String comment;
    private Date added;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
}
