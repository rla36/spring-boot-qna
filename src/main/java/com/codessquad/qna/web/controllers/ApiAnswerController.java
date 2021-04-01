package com.codessquad.qna.web.controllers;

import com.codessquad.qna.web.domain.Answer;
import com.codessquad.qna.web.domain.Question;
import com.codessquad.qna.web.domain.Result;
import com.codessquad.qna.web.domain.User;
import com.codessquad.qna.web.domain.repository.AnswerRepository;
import com.codessquad.qna.web.domain.repository.QuestionRepository;
import com.codessquad.qna.web.exception.QnaException;
import com.codessquad.qna.web.service.AnswerService;
import com.codessquad.qna.web.service.QuestionService;
import com.codessquad.qna.web.utility.SessionUtility;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {

    private AnswerService answerService;

    private ApiAnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
        User loginUser = SessionUtility.findSessionedUser(session);
        return answerService.save(questionId, loginUser, contents);
    }

    @DeleteMapping("/{id}")
    public Result deleteAnswer(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
        try {
            User user = SessionUtility.findSessionedUser(session);
            answerService.delete(id, user);
        } catch (QnaException e) {
            return Result.fail(e.getMessage());
        }
        return Result.okay();
    }
}
