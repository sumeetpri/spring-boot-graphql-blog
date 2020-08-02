package com.graphql.blog.service.dataFetcher;

import com.graphql.blog.model.User;
import com.graphql.blog.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserData implements DataFetcher<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(DataFetchingEnvironment environment) {
        String userId = environment.getArgument("id");
        return userRepository.findById(userId).orElse(null);
    }
}
