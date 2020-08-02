package com.graphql.blog.service;

import com.graphql.blog.model.Post;
import com.graphql.blog.model.User;
import com.graphql.blog.repository.PostRepository;
import com.graphql.blog.repository.UserRepository;
import com.graphql.blog.service.dataFetcher.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class GraphQLService {

    @Value("classpath:blog.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllUsers allUsers;

    @Autowired
    private AllPost allPost;

    @Autowired
    private UserData userData ;

    @Autowired
    private PostData postData;

    @Autowired
    private PostMutation postMutation;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void loadSchema() throws IOException{
        File schemaFile = resource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);

        RuntimeWiring writing = buidRuntime();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,writing);
        graphQL =  GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buidRuntime() {
        return RuntimeWiring.newRuntimeWiring().type("Query",typewriter ->{
            return typewriter
                    .dataFetcher("allUsers",allUsers)
                    .dataFetcher("allPost",allPost)
                    .dataFetcher("post",postData)
                    .dataFetcher("user",userData);

        }).type("Mutation", typeWiring ->
                typeWiring.dataFetcher("createPost", postMutation))
                .build();
    }


    public GraphQL getGraphQL(){
        return graphQL;
    }

    public List<Post>  getAllPost(){
       return postRepository.findAll();
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public void deletePost(String postId){
         postRepository.deleteById(postId);
    }

    public List<User>  getAllUser(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
