package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.DeliveryDriver;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
}
