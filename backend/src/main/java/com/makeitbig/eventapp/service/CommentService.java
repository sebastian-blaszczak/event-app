package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
}
