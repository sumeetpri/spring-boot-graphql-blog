package com.graphql.blog.service.dataFetcher;

import com.graphql.blog.model.User;
import com.graphql.blog.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AllUsers implements DataFetcher<List<User>> {

    @Autowired
     private  UserRepository userRepository;

    @Override
    public List<User> get(DataFetchingEnvironment environment) {
        return userRepository.findAll();
    }
}
