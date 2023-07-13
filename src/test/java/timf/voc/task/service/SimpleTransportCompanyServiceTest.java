package timf.voc.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import timf.voc.task.domain.transportcompany.DeliveryDriverInfo;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.transportcompany.SimpleTransportCompanyService;
import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.config.exception.DeliveryDriverNotFoundException;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.infrastructure.transportcompany.DeliveryDriverRepository;

@ExtendWith(MockitoExtension.class)
class SimpleTransportCompanyServiceTest {

	@InjectMocks
	SimpleTransportCompanyService simpleTransportCompanyService;

	@Mock
	DeliveryDriverRepository deliveryDriverRepository;

	@Test
	void shouldSearchDeliveryDriverEntity_Success() {
		// given
		String driverToken = "abc";
		DeliveryDriver deliveryDriver = createDeliveryDriver(createVocRequest());
		when(deliveryDriverRepository.findDeliveryDriverByDeliveryDriverToken(driverToken)).thenReturn(Optional.of(deliveryDriver));
		;
		// when
		DeliveryDriverInfo result = simpleTransportCompanyService.retrieveDeliveryDriver(driverToken);

		// then
		verify(deliveryDriverRepository.findDeliveryDriverByDeliveryDriverToken(driverToken));
		assertEquals(deliveryDriver, result);
	}

	@Test
	void shouldThrowException_WhenDeliveryDriverNotFound() {
		// given
		String driverToken = "abc";
		DeliveryDriver deliveryDriver = createDeliveryDriver(createVocRequest());
		when(deliveryDriverRepository.findDeliveryDriverByDeliveryDriverToken(driverToken)).thenReturn(Optional.of(deliveryDriver));

		// when, then
		assertThrows(DeliveryDriverNotFoundException.class,
			() -> simpleTransportCompanyService.retrieveDeliveryDriver(driverToken));
		verify(deliveryDriverRepository.findDeliveryDriverByDeliveryDriverToken(driverToken));
	}

	// @Test
	// void shouldGetMyPage_Success() {
	// 	// given
	// 	Long driverId = 1L;
	// 	VocCommand.VocRegisterRequest vocRequest = createVocRequest();
	// 	DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);
	// 	List<Voc> vocs = createVocs();
	// 	when(deliveryDriverRepository.findById(driverId)).thenReturn(Optional.of(deliveryDriver));
	//
	// 	// when
	// 	DeliveryDriverMyPageResponse result = simpleTransportCompanyService.getMyPage(driverId);
	//
	// 	// then
	// 	verify(deliveryDriverRepository).findById(driverId);
	// 	assertEquals(driverId, result.getDeliveryDriver().getId());
	// 	assertEquals(2, result.getVcos().size());
	// }

	@NotNull
	private DeliveryDriver createDeliveryDriver(VocCommand.VocRegisterRequest vocRequest) {
		return DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(), VocFixture.createList(), false,
			TransportCompanyFixture.create());
	}

	@NotNull
	private VocCommand.VocRegisterRequest createVocRequest() {
		return VocRequestFixture.createRegisterRequest("voc request1");
	}

	private List<Voc> createVocs() {
		return VocFixture.createList();
	}
}
