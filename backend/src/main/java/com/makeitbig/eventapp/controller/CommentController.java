package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.CommentNotFoundException;
import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public Comment saveComment(Comment comment) {
        commentService.saveComment(comment);
        return comment;
    }

    @PutMapping("/id={id}")
    public Comment updateComment(Comment comment, @PathVariable long id) {
        commentService.update(Long.valueOf(id), comment);
        Comment updatedComment = commentService.getById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id: " + id + " doesn't exist"));
        return updatedComment;
    }

    @GetMapping()
    public List<Comment> getCommentsByEvent(Event event) {
        return commentService.getByEvent(event);
    }
}
