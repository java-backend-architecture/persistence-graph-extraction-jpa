package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import dev.dmitriirussu.graphextraction.jpa.application.view.OwnerListView;
import dev.dmitriirussu.graphextraction.jpa.application.view.OwnerView;
import dev.dmitriirussu.graphextraction.jpa.application.view.PetView;
import dev.dmitriirussu.graphextraction.jpa.application.view.VisitView;

import java.util.List;

final class ViewMapper {

    private ViewMapper() {}

    static OwnerView toView(OwnerEntityView v) {
        List<PetView> pets = v.getPets().stream().map(ViewMapper::toView).toList();
        return new OwnerView(v.getId(), v.getName(), pets);
    }

    private static PetView toView(PetEntityView v) {
        List<VisitView> visits = v.getVisits().stream().map(ViewMapper::toView).toList();
        return new PetView(v.getId(), v.getName(), v.getOwnerId(), visits);
    }

    private static VisitView toView(VisitEntityView v) {
        return new VisitView(v.getId(), v.getDate(), v.getPetId());
    }

    static OwnerListView toListView(OwnerListEntityView v) {
        return new OwnerListView(v.getId(), v.getName(), v.getPetNames());
    }
}
