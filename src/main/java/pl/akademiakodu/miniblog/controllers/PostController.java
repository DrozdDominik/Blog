package pl.akademiakodu.miniblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.PostComment;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/{postId}")
    public String post(@PathVariable Long postId, Model model){
        Optional<Post> postOptional = postRepository.findById(postId);

        postOptional.ifPresent(post -> {
            model.addAttribute("post", post);
        });

        return "post";
    }

    @PostMapping("/post/addComment")
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


}
