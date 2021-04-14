package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.posts.exeption.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/posts")
@RestController
public class PostController {
    PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    /* create a new post */
    @PostMapping("/")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postRepository.save(post));
    }

    /* return all posts*/
    @GetMapping("/")
    public ResponseEntity <List<Post>>listAllPost() {
            return ResponseEntity.ok(postRepository.findAll());
    }

    /* return a specific post based on the provided id */
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(post);
    }

    /* return should update the post by id */
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Long id, @RequestBody Post updatePostById){
        postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatePostById.setId(id);
        Post post = postRepository.save(updatePostById);
        return ResponseEntity.ok(post);
    }

    /* should delete the post by id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id){
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        postRepository.delete(post);
        return ResponseEntity.ok(post);
    }

}
