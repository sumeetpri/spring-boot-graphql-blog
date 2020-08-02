package com.graphql.blog.service.dataFetcher;

import com.graphql.blog.model.Post;
import com.graphql.blog.model.User;
import com.graphql.blog.repository.PostRepository;
import com.graphql.blog.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostData implements DataFetcher<Post> {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post get(DataFetchingEnvironment environment) {
        String postId = environment.getArgument("id");
        return postRepository.findById(postId).orElse(null);
    }
}
