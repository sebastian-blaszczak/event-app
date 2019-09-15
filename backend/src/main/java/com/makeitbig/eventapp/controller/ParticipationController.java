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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ParticipationController {
    private final ParticipationService participationService;
    private final EventService eventService;

    @Autowired
    public ParticipationController(ParticipationService participationService, EventService eventService) {
        this.participationService = participationService;
        this.eventService = eventService;
    }

    @PostMapping("/participate")
    public Participation saveParticipation(@RequestBody Long eventId, Authentication authentication) {
        Event event = eventService.getById(eventId).orElseThrow(() -> new EventNotFoundException("Event with id: " + eventId + " doesn't exist!"));
        User user = ((UserDetailsWrapper) authentication.getPrincipal()).getUser();
        Participation participation = Participation.builder().event(event).user(user).build();
        participationService.saveParticipation(participation);
        return participation;
    }

}
