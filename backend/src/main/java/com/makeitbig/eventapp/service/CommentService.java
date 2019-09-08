package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    public Optional<Comment> getById(long id) {
        return commentRepository.findById(id);
    }

    public Comment update(long id, Comment comment) {
        Comment commentDB = getById(id).orElseThrow(() -> new EventNotFoundException("Event with id: " + id + " doesn't exist!"));
        commentDB.setDescription(comment.getDescription());
        commentRepository.save(commentDB);
        return commentDB;
    }

    public List<Comment> getByEvent(Event event) {
        return commentRepository.findByEvent(event);
    }
}
