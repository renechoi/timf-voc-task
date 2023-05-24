package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.voc.Voc;

public interface VocRepository extends JpaRepository<Voc, Long> {
}
