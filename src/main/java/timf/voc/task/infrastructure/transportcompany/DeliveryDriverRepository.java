package timf.voc.task.infrastructure.transportcompany;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
	Optional<DeliveryDriver> findDeliveryDriverByDeliveryDriverToken(String token);
}
