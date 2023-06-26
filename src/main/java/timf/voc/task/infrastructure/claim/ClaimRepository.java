package timf.voc.task.infrastructure.claim;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.claim.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
