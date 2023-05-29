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

import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.DeliveryDriverMyPageResponse;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.exception.DeliveryDriverNotFoundException;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.repository.DeliveryDriverRepository;

@ExtendWith(MockitoExtension.class)
class TransportCompanyServiceTest {

	@InjectMocks
	TransportCompanyService transportCompanyService;

	@Mock
	DeliveryDriverRepository deliveryDriverRepository;

	@Test
	void shouldSearchDeliveryDriverEntity_Success() {
		// given
		Long driverId = 1L;
		DeliveryDriver deliveryDriver = createDeliveryDriver(createVocRequest());
		when(deliveryDriverRepository.findById(driverId)).thenReturn(Optional.of(deliveryDriver));

		// when
		DeliveryDriver result = transportCompanyService.searchDeliveryDriverEntity(driverId);

		// then
		verify(deliveryDriverRepository).findById(driverId);
		assertEquals(deliveryDriver, result);
	}

	@Test
	void shouldThrowException_WhenDeliveryDriverNotFound() {
		// given
		Long driverId = 1L;
		when(deliveryDriverRepository.findById(driverId)).thenReturn(Optional.empty());

		// when, then
		assertThrows(DeliveryDriverNotFoundException.class,
			() -> transportCompanyService.searchDeliveryDriverEntity(driverId));
		verify(deliveryDriverRepository).findById(driverId);
	}

	@Test
	void shouldGetMyPage_Success() {
		// given
		Long driverId = 1L;
		VocRequest vocRequest = createVocRequest();
		DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);
		List<Voc> vocs = createVocs();
		when(deliveryDriverRepository.findById(driverId)).thenReturn(Optional.of(deliveryDriver));

		// when
		DeliveryDriverMyPageResponse result = transportCompanyService.getMyPage(driverId);

		// then
		verify(deliveryDriverRepository).findById(driverId);
		assertEquals(driverId, result.getDeliveryDriver().getId());
		assertEquals(2, result.getVcos().size());
	}

	@NotNull
	private DeliveryDriver createDeliveryDriver(VocRequest vocRequest) {
		return DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(), VocFixture.createList(), false,
			TransportCompanyFixture.create());
	}

	@NotNull
	private VocRequest createVocRequest() {
		return VocRequestFixture.create("voc request1");
	}

	private List<Voc> createVocs() {
		return VocFixture.createList();
	}
}
