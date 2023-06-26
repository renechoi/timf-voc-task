package timf.voc.task.infrastructure.voc;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.voc.aggregate.Voc;

public interface VocRepository extends JpaRepository<Voc, Long> {
}
