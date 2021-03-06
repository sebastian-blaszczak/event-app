package com.makeitbig.eventapp.repository;

import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select p.event from Participation p where p.user.id = ?1")
    List<Event> findByUser(long userId);
    @Query("select p.event from Participation p where not p.user.id = ?1")
    List<Event> findByUserToParticipate(long userId);
    @Query("select e from Event e where ?1 member of e.participators")
    List<Event> getEventsByParticipateUser(User user);
    @Query("select e from Event e where not ?1 member of e.participators")
    List<Event> getEventsToParticipateByUser(User user);
}
