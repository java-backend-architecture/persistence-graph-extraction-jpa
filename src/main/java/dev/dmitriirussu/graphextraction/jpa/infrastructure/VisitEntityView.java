package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;

import java.time.LocalDate;

@EntityView(VisitEntity.class)
interface VisitEntityView {
    @IdMapping
    String getId();
    LocalDate getDate();
    @Mapping("pet.id")
    String getPetId();
}
