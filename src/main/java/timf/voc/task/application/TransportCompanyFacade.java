package timf.voc.task.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.transportcompany.DeliveryDriverInfo;
import timf.voc.task.domain.transportcompany.SimpleTransportCompanyService;

@RequiredArgsConstructor
@Service
public class TransportCompanyFacade {
	private final SimpleTransportCompanyService simpleTransportCompanyService;

	public DeliveryDriverInfo getMyPage(String token) {
		return simpleTransportCompanyService.retrieveDeliveryDriver(token);
	}

	public void registerDriver() {
		simpleTransportCompanyService.registerDriver();
	}
}
