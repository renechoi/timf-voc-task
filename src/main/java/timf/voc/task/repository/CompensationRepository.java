package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.voc.aggregate.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {
}
