package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.CommentNotFoundException;
import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add-comment")
    public Comment saveComment(Comment comment) {
        commentService.saveComment(comment);
        return comment;
    }

    @GetMapping("/update-comment/id={commentId}&description={description}")
    public Comment updateComment(@PathVariable(value = "commentId") String id, @PathVariable(value = "description") String description) {
        commentService.update(Long.valueOf(id), description);
        Comment comment = commentService.findById(Long.valueOf(id)).orElseThrow(() -> new CommentNotFoundException("Comment with id: " + id + " doesn't exist"));
        return comment;
    }
}
