package pl.akademiakodu.miniblog.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiakodu.miniblog.model.dtos.PostCommentDto;
import pl.akademiakodu.miniblog.model.dtos.PostDto;
import pl.akademiakodu.miniblog.model.entities.Post;
import pl.akademiakodu.miniblog.model.entities.PostComment;
import pl.akademiakodu.miniblog.model.entities.User;
import pl.akademiakodu.miniblog.model.repositories.PostRepository;
import pl.akademiakodu.miniblog.model.repositories.UserRepository;
import pl.akademiakodu.miniblog.services.PostService;

import java.util.Optional;

@RestController
public class PostRestController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Autowired
    ModelMapper modelMapper;

//    @Autowired
//    UserRepository userRepository;

    @PostMapping("/api/post/{postId}/comment")
    public ResponseEntity<PostCommentDto> addPostComment(@PathVariable Long postId, @RequestParam String comment){
        Optional<Post> postOptional = postRepository.findById(postId);
        PostComment postComment = new PostComment();
        postComment.setComment(comment);

        Post post = postOptional.get();

//        postComment.setPost(post);
//        postCRepo.save()

        post.addComment(postComment);
        post = postRepository.save(post);

        PostComment savedComment = post.getComments().get(post.getComments().size() - 1);


        modelMapper.createTypeMap(PostComment.class, PostCommentDto.class)
                .addMapping(pc -> pc.getAudit().getAdded(), PostCommentDto::setAdded);

        return ResponseEntity.ok()
                .body(modelMapper.map(savedComment, PostCommentDto.class));

    }

    @PostMapping("/api/post")
    public ResponseEntity<PostDto> addPost(@RequestParam String title
            , @RequestParam String content, @RequestParam Long userId){

        PostDto postDto = postService.createPost(title, content, userId);
        return ResponseEntity.ok().body(postDto);
    }

}
