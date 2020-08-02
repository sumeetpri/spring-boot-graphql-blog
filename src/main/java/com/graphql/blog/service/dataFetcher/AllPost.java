package com.graphql.blog.service.dataFetcher;

import com.graphql.blog.model.Post;
import com.graphql.blog.repository.PostRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AllPost implements DataFetcher<List<Post>> {
    @Autowired
    private PostRepository postRepository;
    @Override
    public List<Post> get(DataFetchingEnvironment environment) {
        return postRepository.findAll();
    }
}
