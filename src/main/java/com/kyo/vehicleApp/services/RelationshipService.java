package com.kyo.vehicleApp.services;

import com.kyo.vehicleApp.models.Relationship;
import com.kyo.vehicleApp.models.User;
import com.kyo.vehicleApp.repositories.RelationshipRepository;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {
    private final RelationshipRepository relationshipRepository;

    public RelationshipService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public void createRelationship(User user1, User user2) {
        Relationship relationship = new Relationship(user1, user2);
        relationshipRepository.save(relationship);
    }


}

