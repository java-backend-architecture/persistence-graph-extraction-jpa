# jpa-graph-extraction-no-pagination
Demonstrates how to assemble an object graph from a relational database using JPA entities and Blaze Persistence Entity Views without ORM magic or pagination.

## What it shows

* Assembling a three-level object graph (`Owner → Pet → Visit`) using Blaze Persistence Entity Views
* Using `package-private` classes as an encapsulation boundary inside the infrastructure layer
* Two query strategies:
  * full graph — Owner with Pets and Visits (`OwnerView`)
  * flat list — Owner with pet names only (`OwnerListView`)

## Stack

* Java 25
* Spring Boot
* JPA (`EntityManager`)
* Blaze Persistence (`EntityViewManager`, `CriteriaBuilderFactory`)
* H2 (in-memory database)

## Structure

```
application/
    OwnerReadRepository       # port (interface)
    view/                     # read models: OwnerView, OwnerListView, PetView, VisitView

infrastructure/
    BlazeOwnerReadRepository  # Blaze Persistence implementation (package-private)
    OwnerEntity               # @Entity (package-private)
    PetEntity                 # @Entity (package-private)
    VisitEntity               # @Entity (package-private)
    OwnerEntityView           # @EntityView — full graph (package-private)
    PetEntityView             # @EntityView (package-private)
    VisitEntityView           # @EntityView (package-private)
    OwnerListEntityView       # @EntityView — flat list (package-private)
    ViewMapper                # EntityView → View records (package-private)
    BlazeConfig               # CriteriaBuilderFactory + EntityViewManager beans (package-private)
```

## Key idea

Blaze Persistence Entity Views fetch only the data needed — no N+1, no manual JOIN, no deduplication:

```
Owner(jack)
  └── Pet(buddy1)
        ├── Visit(2026-01-10)
        └── Visit(2026-02-15)
  └── Pet(buddy2)
        └── Visit(2026-03-01)
```

`@EntityView` interfaces define the exact shape of the result. Blaze generates optimized queries automatically and maps the result into view instances. `ViewMapper` converts them into application-level view records.

Compare with the JDBC and jOOQ approaches:

|                       | JDBC                                      | jOOQ                  | JPA + Blaze                        |
|-----------------------|-------------------------------------------|-----------------------|------------------------------------|
| SQL result            | flat rows (cartesian JOIN)                | nested collections    | optimized per-view query           |
| Deduplication         | `LinkedHashMap` in projections            | not needed            | not needed                         |
| Intermediate classes  | `OwnerProjection`, `PetProjection`, etc.  | none                  | `@EntityView` interfaces           |
| Mapping               | `ViewMapper`                              | `Records.mapping()`   | `ViewMapper`                       |
| N+1 problem           | no (single JOIN query)                    | no (single query)     | no (Blaze optimizes automatically) |

`BlazeOwnerReadRepository` is `package-private` by design; only `OwnerReadRepository` (the interface) is exposed outside the infrastructure package.

## Tests

Integration tests in `src/test/java` cover graph assembly, flat list extraction, and edge cases.

## Related

* [jdbc-graph-extraction-no-pagination](https://github.com/persistence-graph-extraction/jdbc-graph-extraction-no-pagination) — same approach with plain JDBC
* [jooq-graph-extraction-no-pagination](https://github.com/persistence-graph-extraction/jooq-graph-extraction-no-pagination) — same approach with jOOQ MULTISET
* [jpa-graph-extraction-pagination](https://github.com/persistence-graph-extraction/jpa-graph-extraction-pagination) — same approach with pagination support

## Run

```
./mvnw spring-boot:run
```
