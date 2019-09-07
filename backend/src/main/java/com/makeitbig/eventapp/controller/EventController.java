package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/user/add-event")
    public Event addEvent(Event event) {
        eventService.save(event);
        return event;
    }

    @GetMapping("/user/event/id={eventId}")
    public Event getEventById(@PathVariable(value = "eventId") String id) {
        Event event = eventService.getEventsById(Long.valueOf(id)).orElseThrow(() -> new EventNotFoundException("Event with id: " + id + " doesn't exist!"));
        eventService.save(event);
        return event;
    }

    @GetMapping("/user/all-events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
