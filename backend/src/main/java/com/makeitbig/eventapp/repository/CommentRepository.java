package com.makeitbig.eventapp.repository;

import com.makeitbig.eventapp.model.Comment;
import com.makeitbig.eventapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

    List<Comment> findByEvent(Event event);
}
