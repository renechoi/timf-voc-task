package timf.voc.task.domain.transportcompany;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.config.exception.DeliveryDriverNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimpleTransportCompanyService implements TransportCompanyService{

	private final DeliveryDriverReader deliveryDriverReader;
	private final DeliveryDriverPersister deliveryDriverPersister;

	public DeliveryDriverInfo retrieveDeliveryDriver(String token) {
		return deliveryDriverReader.get(identifyToken(token)).map(DeliveryDriverInfo::from).orElseThrow(DeliveryDriverNotFoundException::new);
	}

	@Transactional
	public void registerDriver() {
		DeliveryDriver deliveryDriver = new DeliveryDriver("테스트 드라이버", 10000L);
		deliveryDriverPersister.save(deliveryDriver);
	}

	private String identifyToken(String token) {
		return "deliveryDriver_" + token;
	}

}
