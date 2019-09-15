package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.repository.EventRepository;
import com.makeitbig.eventapp.repository.UserRepository;
import com.makeitbig.eventapp.wrapper.UserDetailsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository, UserService userService) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void save(Event event, Authentication authentication) {
        User organizer = userService.getUserById(((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId());
        ArrayList<User> participators = new ArrayList<>();
        event.setParticipators(participators);
        event.getParticipators().add(organizer);
        event.setOrganizer(organizer);
        eventRepository.save(event);
    }

    @Transactional
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void addParticipator(Long eventId, Authentication authentication) {
        User user = userService.getUserById(((UserDetailsWrapper) authentication.getPrincipal()).getUser().getId());
        Event event = getById(eventId).orElseThrow(() -> new EventNotFoundException("Event with id: " + eventId + " doesn't exist!"));
        event.getParticipators().add(user);
        save(event);
    }

    public List<Event> getByUser(long userId) {
        return eventRepository.findByUser(userId);
    }

    public Optional<Event> getById(long eventId) {
        return eventRepository.findById(eventId);
    }

    public Event update(long id, Event event) {
        Event eventDB = getById(id).orElseThrow(() -> new EventNotFoundException("Event with id: " + id + " doesn't exist!"));
        eventDB.setName(event.getName());
        eventDB.setAddress(event.getAddress());
        eventDB.setDate(event.getDate());
        eventDB.setAccesType(event.getAccesType());
        eventRepository.save(eventDB);
        return eventDB;
    }

    public List<Event> getEventsToParticipateByUser(User user) {
        return eventRepository.getEventsToParticipateByUser(user);
    }

    public List<Event> getEventsByParticipatedUser(User user) {
        return eventRepository.getEventsByParticipateUser(user);
    }
}
