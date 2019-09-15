package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.exception.EventNotFoundException;
import com.makeitbig.eventapp.model.Event;
import com.makeitbig.eventapp.model.User;
import com.makeitbig.eventapp.repository.EventRepository;
import com.makeitbig.eventapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public void save(Event event, String userName) {
        User organizer = userRepository.findByUserName(userName);
        event.setOrganizer(organizer);
        eventRepository.save(event);
    }
//
//    @Transactional
//    public void save(Event event) {
//        eventRepository.save(event);
//    }

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

//    public List<Event> getEventsToParticipateByUserId(long userId) {
//        return eventRepository.getParticipatorsByEventId(userId);
//    }

    public List<Event> getByUserToParticipate(Long userId) {
        return eventRepository.findByUserToParticipate(userId);
    }
}
