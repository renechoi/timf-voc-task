package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
}