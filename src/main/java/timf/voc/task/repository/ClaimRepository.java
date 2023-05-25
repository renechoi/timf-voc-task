package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
