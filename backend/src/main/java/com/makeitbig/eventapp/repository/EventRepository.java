package com.makeitbig.eventapp.repository;

import com.makeitbig.eventapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select p.event from Participation p where p.user.id = ?1")
    List<Event> findByUser(long userId);
}
