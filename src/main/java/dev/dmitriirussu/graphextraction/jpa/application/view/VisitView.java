package dev.dmitriirussu.graphextraction.jpa.application.view;

import java.time.LocalDate;

/**
 * Application read model.
 */
public record VisitView (String id, LocalDate date, String petId) {}
