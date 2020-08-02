package com.graphql.blog.service.dataFetcher;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.blog.model.Post;
import com.graphql.blog.repository.PostRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PostMutation implements DataFetcher<Post> {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(final String title, final String postedBy, final String content){
        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);
        post.setPostedBy(postedBy);
        return postRepository.save(post);
    }

    @Override
    public Post get(DataFetchingEnvironment environment) {
        Post post = new Post();
        post.setContent(environment.getArgument("content"));
        post.setTitle(environment.getArgument("title"));
        post.setPostedBy(environment.getArgument("postedBy"));
        return postRepository.save(post);
    }
}
