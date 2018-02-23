package net.pedr0.test.jpa.repository;

import net.pedr0.test.jpa.model.DocGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<DocGroup, Integer> {
}
