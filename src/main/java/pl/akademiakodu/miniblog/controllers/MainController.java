package pl.akademiakodu.miniblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.PostComment;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import pl.akademiakodu.miniblog.services.UserSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    private PostRepository postRepository;

    private UserSessionService userSessionService;
    @Autowired
    public MainController(PostRepository postRepository, UserSessionService userSessionService) {
        this.postRepository = postRepository;
        this.userSessionService = userSessionService;
    }
    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("loggedUser", userSessionService.getUserDto());


        if(userSessionService.getUserDto() == null) {
            model.addAttribute("name", "Nie jestes zalogowany");
            return "logout";
        } else{
            model.addAttribute("name", userSessionService.getUserDto().getUserName());
            return "index";
        }
    }

    @PostMapping("/addPost")
    public String addPost(Model model, @RequestParam (value = "title") String titleParam, @RequestParam String content){
        System.out.println("Params: " + titleParam + ", " + content);
        Post post = new Post(titleParam, content);

        PostComment postComment = new PostComment();
        postComment.setComment(titleParam);

        post.addComment(postComment);
        //postComment.setPost(post);

//        List<PostComment> commentList = new ArrayList<>();
//        commentList.add(postComment);

        //post.setComments(commentList);

        postRepository.save(post);
        return "index";
    }

    @GetMapping("/addPost")
    public String addPostPage(){
        return "addPost";
    }

    @GetMapping("/posts")
    public String postsPage(Model model){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAll();

        for (Post post : postIterable) {
            postsList.add(post);
        }

        model.addAttribute("posts", postsList);
        return "posts";
    }

    @GetMapping("/posts/{title}")
    public String postsByTitle(@PathVariable String title, Model model){
        List<Post> postsList = new ArrayList<>();
        Iterable<Post> postIterable = postRepository.findAllByTitleContains(title);
        for (Post post : postIterable) {
            postsList.add(post);
        }
        model.addAttribute("posts", postsList);
        return "posts";
    }

    @GetMapping("/posts/{title}/{sortField}/{sortDirection}")
    public String postsByTitle(@PathVariable String title
            , @PathVariable String sortField,
            @PathVariable String sortDirection
            , Model model){

        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equals(sortDirection)){
            direction = Sort.Direction.DESC;
        }

        List<Post> postsList = postRepository
                .findAllByTitleContains(title, Sort.by(Sort.Direction.fromString(sortDirection), sortField));
        model.addAttribute("posts", postsList);
        return "posts";
    }


}
