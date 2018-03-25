package pl.akademiakodu.miniblog.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
//Adnotacja JPA. Klasa taka, którą adnotujemy za pomocą @Entity,
// musi mieć publiczne gettery i settery dla każdego pola.
// Założenia przyjęte przez JPA w takiej sytuacji są następujące:
// tabela nazywa się tak jak klasa.
// kolumny nazywają się tak, jak pola i są odpowiedniego typu z możliwością wpisania null.
@Table(name = "post")
//@Table —
// Dzięki tej adnotacji możemy określić dodatkowe parametry związane z tabelą bazy danych,
// którą ta klasa reprezentuje. Możemy określić np. nazwę.
public class Post {

    @Id //
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    //@Column(name = "contentString")
    private String content;

    @Embedded
    private AuditEntity audit = new AuditEntity();

 //   @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JoinColumn(name = "postId")
            List<PostComment> comments = new ArrayList<>();

 //   @JsonBackReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "mapPostTag",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")})
    private Set<Tag> tags = new HashSet<>();

  //  @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
 //   @Getter
 //   @Setter
    private User user;


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


    public void addComment(PostComment postComment) {
        comments.add(postComment);
        postComment.setPost(this);
    }

    public void removeComment(PostComment postComment) {
        comments.remove(postComment);
        postComment.setPost(null);
    }

    public AuditEntity getAudit() {
        return audit;
    }

    public void setAudit(AuditEntity audit) {
        this.audit = audit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
//                ", added=" + added +
                '}';
    }

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
