package com.makeitbig.eventapp.controller;

import com.makeitbig.eventapp.model.Participation;
import com.makeitbig.eventapp.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParticipationController {
    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @PostMapping("/participate")
    public Participation saveParticipation(Participation participation) {
        participationService.saveParticipation(participation);
        return participation;
    }

}
