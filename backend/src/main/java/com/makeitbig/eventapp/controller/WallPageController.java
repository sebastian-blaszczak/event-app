package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WallPageController {

    @Autowired
    EventService eventService;

    @PostMapping("/user")
    List<Event> getEventsForLoggedUser(User user) {
        return eventService.getEventsByUser(user.getId());
    }
}
