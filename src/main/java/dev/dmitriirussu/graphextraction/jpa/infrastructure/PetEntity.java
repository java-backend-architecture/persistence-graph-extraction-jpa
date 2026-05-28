package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pets")
class PetEntity {

    @Id String id;
    String name;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    OwnerEntity owner;

    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY)
    List<VisitEntity> visits = new ArrayList<>();
}
