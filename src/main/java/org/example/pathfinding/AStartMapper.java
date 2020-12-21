package org.example.pathfinding;

public interface AStartMapper<AStartObject, DomainObject> {
    AStartObject mapTo(DomainObject domainObject);

    DomainObject mapFrom(AStartObject AStartObject);
}
