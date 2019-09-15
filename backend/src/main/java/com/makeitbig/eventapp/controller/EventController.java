package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.EventService;
import com.makeitbig.eventapp.wrapper.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/events")
@CrossOrigin("*")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event, Authentication authentication) {
        eventService.save(event, ((UserDetailsWrapper) authentication.getPrincipal()).getUser().getUserName());
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
        //eventService.save(event);
        return event;
    }

    @GetMapping("/admin/all-events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping()
    public List<Event> getEventsForLoggedUser(Authentication authentication) {
        return eventService.getByUser(((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId());
    }
}
