package com.makeitbig.eventapp.repository;

import com.makeitbig.eventapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
