package pl.akademiakodu.miniblog.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tagName;

    @Embedded
    private AuditEntity audit = new AuditEntity();

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();


    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public AuditEntity getAudit() {

        return audit;
    }

    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }
}
