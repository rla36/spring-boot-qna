package com.codessquad.qna.repository;

import com.codessquad.qna.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    public void save(Post post);

    public Optional<Post> getPost(int postId);

    public List<Post> getPosts();

    public int size();

}
