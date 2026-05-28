package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visits")
class VisitEntity {

    @Id String id;
    LocalDate date;

    @JoinColumn(name = "pet_id")
    @ManyToOne(fetch = FetchType.LAZY)
    PetEntity pet;
}
