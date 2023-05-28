package timf.voc.task.service;

import static org.assertj.core.api.BDDAssumptions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.entity.Claim;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.exception.ClaimNotFoundException;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.repository.ClaimRepository;
import timf.voc.task.repository.VocRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VocServiceTest {

	@InjectMocks VocService vocService;

	@Mock ClientCompanyService clientCompanyService;

	@Mock TransportCompanyService transportCompanyService;

	@Mock ClaimService claimService;

	@Mock NotificationService notificationService;

	@Mock VocRepository vocRepository;

	@Test
	void shouldSave_Success() {
		// given
		VocRequest vocRequest = VocRequestFixture.create("voc request1");

		DeliveryDriver deliveryDriver = DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(), VocFixture.createEmptyAsList(), false,
			TransportCompanyFixture.create());
		when(transportCompanyService.searchDeliveryDriverEntity(vocRequest.getDeliveryDriverId()))
			.thenReturn(deliveryDriver);

		ClientCompany clientCompany = ClientCompanyFixture.create(vocRequest.getClientCompanyId(), VocFixture.createEmptyAsList(), 1000L, false);
		when(clientCompanyService.searchClientCompanyEntity(vocRequest.getClientCompanyId()))
			.thenReturn(clientCompany);

		willDoNothing().given(claimService).handleStatus(vocRequest,true);
		willDoNothing().given(notificationService).notifyVocUpdate();
		// when
		vocService.registerVoc(vocRequest);

		// then
		ArgumentCaptor<Voc> vocCaptor = ArgumentCaptor.forClass(Voc.class);
		verify(vocRepository).save(vocCaptor.capture());
		Voc savedVoc = vocCaptor.getValue();

		assertNotNull(savedVoc);
		assertEquals(vocRequest.getDescription(), savedVoc.getDescription());
		assertEquals(vocRequest.isClaimReceived(), savedVoc.isClaimReceived());
		assertEquals(vocRequest.isCompensationRequested(), savedVoc.isCompensationRequested());
		assertEquals(vocRequest.getClaimResponsibility(), savedVoc.getClaimResponsibility());
		assertEquals(deliveryDriver, savedVoc.getDeliveryDriver());
		assertEquals(clientCompany, savedVoc.getClientCompany());
	}
}


