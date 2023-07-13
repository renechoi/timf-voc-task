package timf.voc.task.infrastructure.voc;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.voc.aggregate.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {
}
