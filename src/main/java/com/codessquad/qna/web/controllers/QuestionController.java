package com.codessquad.qna.web.controllers;

import com.codessquad.qna.web.domain.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class QuestionController {
    private List<Question> questions = new ArrayList<>();

    @PostMapping("/questions")
    public String postQuestionForm(Question question) {
        question.setId(questions.size()+1);
        question.setDateTime(presentDateTime());

        questions.add(question);
        return "redirect:/";
    }

    private String presentDateTime() {
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
        Calendar time = Calendar.getInstance();
        return format.format(time.getTime());
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("questions", questions);
        return "index";
    }

    @GetMapping( "/questions/{id}")
    public String getQuestionShow(@PathVariable("id") int id, Model model) {

        Question question = questions.get(id-1);
        model.addAttribute("question", question);
        return "qna/show";
    }




}
