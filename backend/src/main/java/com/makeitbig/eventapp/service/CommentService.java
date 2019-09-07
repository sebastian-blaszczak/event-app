package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    public Comment update(long id, String description) {
        Comment comment = findById(id).orElseThrow(() -> new EventNotFoundException("Event with id: " + id + " doesn't exist!"));
        comment.setDescription(description);
        commentRepository.save(comment);
        return comment;
    }
}
