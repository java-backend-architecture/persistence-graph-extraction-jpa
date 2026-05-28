package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import com.blazebit.persistence.view.Mapping;

import java.util.List;

@EntityView(OwnerEntity.class)
interface OwnerListEntityView {
    @IdMapping
    String getId();
    String getName();
    @Mapping("pets.name")
    List<String> getPetNames();
}
