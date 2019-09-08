package com.makeitbig.eventapp.service;

import com.makeitbig.eventapp.model.Participation;
import com.makeitbig.eventapp.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Participation saveParticipation(Participation participation) {
        participationRepository.save(participation);
        return participation;
    }
}
