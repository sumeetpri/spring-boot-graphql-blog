package com.graphql.blog.controller.api;

import com.graphql.blog.model.Post;
import com.graphql.blog.model.User;
import com.graphql.blog.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rest/blog")
@RestController
public class BlogResource  {
    @Autowired
    GraphQLService service;

    @PostMapping
    public ResponseEntity<Object> getBlogData(@RequestBody String query){
        ExecutionResult execution = service.getGraphQL().execute(query);
        return new ResponseEntity<>(execution, HttpStatus.OK);
    }


  /*  @GetMapping(value = "/posts")
    public List<Post> getAllPost(){
        return  service.getAllPost();
    }
    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post){
        return  service.createPost(post);
    }
    @DeleteMapping(value="/post/{id}")
    public void deltePost(@PathVariable String postId){
        service.deletePost(postId);
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return service.getAllUser();
    }

    @PostMapping(value="/user")
    public User addUser(@RequestBody User user){
        return service.createUser( user );
    }

    @DeleteMapping(value = "/user/{userId}")
    public void deleteUser(@PathVariable String userId){
        service.deleteUser(userId);
    }
*/
}
