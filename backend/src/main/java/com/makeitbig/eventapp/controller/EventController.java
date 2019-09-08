package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add")
    public Event addEvent(Event event) {
        eventService.save(event);
        return event;
    }

    @PutMapping("/id={id}")
    public Event updateComment(Event event, @PathVariable long id) {
        eventService.update(id, event);
        Event updatedEvent = eventService.getById(id).orElseThrow(() -> new EventNotFoundException("Comment with id: " + id + " doesn't exist"));
        return updatedEvent;
    }

    @GetMapping("/id={eventId}")
    public Event getEventById(@PathVariable(value = "eventId") long id) {
        Event event = eventService.getById(id).orElseThrow(() -> new EventNotFoundException("Event with id: " + id + " doesn't exist!"));
        eventService.save(event);
        return event;
    }

    @GetMapping("/admin/all-events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping()
    public List<Event> getEventsForLoggedUser(User user) {
        return eventService.getByUser(user.getId());
    }
}
