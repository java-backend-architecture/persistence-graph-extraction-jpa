package dev.dmitriirussu.graphextraction.jpa.application.view;

import java.util.List;

/**
 * Application read model.
 */
public record OwnerView(
        String id,
        String name,
        List<PetView> pets
) {
    public OwnerView { pets = List.copyOf(pets); }
}
