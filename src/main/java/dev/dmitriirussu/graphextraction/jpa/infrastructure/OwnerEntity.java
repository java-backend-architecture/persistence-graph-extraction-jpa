package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owners")
class OwnerEntity {

    @Id String id;
    String name;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    List<PetEntity> pets = new ArrayList<>();
}
