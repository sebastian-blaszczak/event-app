package com.makeitbig.eventapp.repository;

import com.makeitbig.eventapp.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
