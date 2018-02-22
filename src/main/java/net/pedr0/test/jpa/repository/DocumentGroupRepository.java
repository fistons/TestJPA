package net.pedr0.test.jpa.repository;

import net.pedr0.test.jpa.model.DocumentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentGroupRepository extends JpaRepository<DocumentGroup, Integer> {
}
