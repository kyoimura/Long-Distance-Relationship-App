package com.kyo.vehicleApp.repositories;

import com.kyo.vehicleApp.models.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

    Relationship findByUser1IdOrUser2Id(int user1Id, int user2Id);

}

