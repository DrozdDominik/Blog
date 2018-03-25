package pl.akademiakodu.miniblog.controllers.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.PostComment;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

@RestController("/")
public class PostRestController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ExampleRepository exampleRepository;

    @GetMapping("api/post/all")
    public List<Post> allPosts(){
        return postRepository.findAll();
    }

    @GetMapping("api/example/all")
    public List<Example> allExample(){
        return exampleRepository.findAll();
    }

/*


    @PostMapping("api/post/addComment")
    public String addComment(@RequestParam String commentBody, @RequestParam Long postId){
        PostComment postComment = new PostComment();
        postComment.setComment(commentBody);

        Optional<Post> postOptional = postRepository.findById(postId);
        postOptional.ifPresent(asd -> {
            asd.addComment(postComment);
            postRepository.save(asd);
        });


        return "redirect:/post/" + postId;
    }
*/


}
