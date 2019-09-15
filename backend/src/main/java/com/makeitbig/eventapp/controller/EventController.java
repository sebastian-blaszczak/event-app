package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.EventService;
import com.makeitbig.eventapp.service.ParticipationService;
import com.makeitbig.eventapp.service.UserService;
import com.makeitbig.eventapp.wrapper.UserDetailsWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/events")
@CrossOrigin("*")
public class EventController {
    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, ParticipationService participationService, UserService userService) {
        this.eventService = eventService;

        this.userService = userService;
    }

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event, Authentication authentication) {
        eventService.save(event, authentication);
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
        return event;
    }

    @GetMapping("/admin/all-events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping()
    public List<Event> getEventsForLoggedUser(Authentication authentication) {
        return eventService.getEventsByParticipatedUser(((UserDetailsWrapper) authentication.getPrincipal()).getUser());
    }

    @GetMapping("/not-participate")
    public List<Event> getEventsToParticipate(Authentication authentication) {
        return eventService.getEventsToParticipateByUser(((UserDetailsWrapper) authentication.getPrincipal()).getUser());
    }

    @PostMapping("/participate")
    public Event participateToEvent(@RequestBody Long eventId, Authentication authentication) {
        eventService.addParticipator(eventId, authentication);
        Event event = eventService.getById(eventId).orElseThrow(() -> new EventNotFoundException("Event with id: " + eventId + " doesn't exist!"));
        return event;
    }
}
