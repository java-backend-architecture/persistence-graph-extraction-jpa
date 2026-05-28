package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

import java.util.List;

@EntityView(OwnerEntity.class)
interface OwnerEntityView {
    @IdMapping
    String getId();
    String getName();
    List<? extends PetEntityView> getPets();
}
