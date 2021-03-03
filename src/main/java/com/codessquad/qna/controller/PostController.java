package com.codessquad.qna.controller;

import com.codessquad.qna.entity.Post;
import com.codessquad.qna.exception.CanNotFindPostException;
import com.codessquad.qna.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PostController {

    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    /**
     * 질문 게시글을 게시판에 등록합니다.
     * @param writer
     * @param title
     * @param contents
     * @return
     */
    @PostMapping("/questions")
    public String addPost(@RequestParam String writer, @RequestParam String title, @RequestParam String contents) {
        Post post = new Post(writer, title, contents);
        postService.addPost(post);
        return "redirect:/";
    }

    /**
     * Repository 에 있는 모든 게시물을 불러옵니다.
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "index";
    }

    /**
     * 매개변수로 오는 id 값을 기반으로 해당 포스트를 불러옵니다.
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/questions/{id}")
    public String getPost(@PathVariable int id, Model model) {
        try{
            Post post = postService.getPost(id);
            model.addAttribute("post", post);
        }catch (CanNotFindPostException e){
            logger.error(e.getMessage());
        }
        return "qna/show";
    }

}
