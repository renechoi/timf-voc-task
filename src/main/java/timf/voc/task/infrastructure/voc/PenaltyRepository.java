package timf.voc.task.infrastructure.voc;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.voc.aggregate.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
}
