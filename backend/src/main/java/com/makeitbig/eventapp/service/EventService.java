package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public List<Event> getEventsByUser(long userId) {
        return eventRepository.findByUser(userId);
    }
}
