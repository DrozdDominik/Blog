package pl.akademiakodu.miniblog.model.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import pl.akademiakodu.miniblog.model.entities.Post;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TagDto {

    private Long id;
    private String TagName;
    private Date created;

    @JsonBackReference
    private Set<PostDto> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public Set<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostDto> posts) {
        this.posts = posts;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
