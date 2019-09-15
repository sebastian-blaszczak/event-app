package com.makeitbig.eventapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne
    private User organizer;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> participators;
    private AccesType accesType;
}
