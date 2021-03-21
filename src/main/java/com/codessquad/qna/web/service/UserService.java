package com.codessquad.qna.web.service;

import com.codessquad.qna.web.domain.User;
import com.codessquad.qna.web.domain.repository.UserRepository;
import com.codessquad.qna.web.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("해당 id의 사용자가 존재하지 않습니다."));
        return user;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean isCorrectPassword(User user, User newInfoUser) {
        return user.getPassword().equals(newInfoUser.getPassword());
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
