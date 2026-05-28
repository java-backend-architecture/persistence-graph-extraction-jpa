package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;

import java.util.List;

@EntityView(PetEntity.class)
interface PetEntityView {
    @IdMapping
    String getId();
    String getName();
    @Mapping("owner.id")
    String getOwnerId();
    List<? extends VisitEntityView> getVisits();
}
