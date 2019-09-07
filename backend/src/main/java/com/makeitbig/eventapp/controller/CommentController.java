package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public Comment saveComment(Comment comment) {
        commentService.saveComment(comment);
        return comment;
    }
}
