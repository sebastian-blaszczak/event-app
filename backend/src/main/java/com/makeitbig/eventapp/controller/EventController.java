package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.Participation;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.EventService;
import com.makeitbig.eventapp.service.ParticipationService;
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
    private final ParticipationService participationService;

    public EventController(EventService eventService, ParticipationService participationService) {
        this.eventService = eventService;
        this.participationService = participationService;
    }

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event, Authentication authentication) {
        User organizer = ((UserDetailsWrapper) authentication.getPrincipal()).getUser();
        eventService.save(event, organizer.getUserName());
        Participation participation = Participation.builder().event(event).user(organizer).approvalFromOrganizer(true).build();
        participationService.saveParticipation(participation);
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
        return eventService.getByUser(((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId());
    }

//    @GetMapping("/not-participate")
//    public List<Event> getEventsToParticipate(Authentication authentication) {
//        return eventService.getEventsToParticipateByUserId(((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId());
//    }
    @GetMapping("/not-participate")
    public List<Event> getEventsToParticipate(Authentication authentication) {
        return eventService.getByUserToParticipate(((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId());
    }

//    @PostMapping("/participate")
//    public Event participateToEvent(@RequestBody Long eventId, Authentication authentication) {
//        User user = ((UserDetailsWrapper) authentication.getPrincipal()).getUser();
//        Event event = eventService.getById(eventId).orElseThrow(() -> new EventNotFoundException("Event with id: " + eventId + " doesn't exist!"));
//        event.getParticipators().add(user);
//        eventService.save(event);
//        return event;
//    }
}
