package timf.voc.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import timf.voc.task.dto.request.DeliveryDriverPenaltyRequest;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.CompensationResponse;
import timf.voc.task.dto.response.VocResponse;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.entity.voc.aggregate.Compensation;
import timf.voc.task.entity.voc.aggregate.PenaltyApproval;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.CompensationFixture;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.DeliveryDriverPenaltyRequestFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.repository.CompensationRepository;
import timf.voc.task.repository.VocRepository;

@ExtendWith(MockitoExtension.class)
class VocServiceTest {

	@InjectMocks
	VocService vocService;

	@Mock
	ClientCompanyService clientCompanyService;

	@Mock
	TransportCompanyService transportCompanyService;

	@Mock
	ClaimService claimService;

	@Mock
	NotificationService notificationService;

	@Mock
	VocRepository vocRepository;

	@Mock
	CompensationRepository compensationRepository;

	@Test
	void shouldSave_Success() {
		// given
		VocRequest vocRequest = createVocRequest();

		DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);
		when(transportCompanyService.searchDeliveryDriverEntity(vocRequest.getDeliveryDriverId()))
			.thenReturn(deliveryDriver);

		ClientCompany clientCompany = createClientCompany(vocRequest);
		when(clientCompanyService.searchClientCompanyEntity(vocRequest.getClientCompanyId()))
			.thenReturn(clientCompany);

		willDoNothing().given(claimService).handleStatus(vocRequest, true);
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

	@Test
	void shouldHandleDriverPenalty_WhenDriverApproved() {
		// given
		DeliveryDriverPenaltyRequest deliveryDriverPenaltyRequest = DeliveryDriverPenaltyRequestFixture.create_Approved();

		VocRequest vocRequest = createVocRequest();
		ClientCompany clientCompany = createClientCompany(vocRequest);
		DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);

		Voc voc = createVoc(clientCompany, deliveryDriver);
		when(vocRepository.findById(deliveryDriverPenaltyRequest.getVocId())).thenReturn(Optional.of(voc));

		// when
		vocService.handleDriverPenalty(deliveryDriverPenaltyRequest);

		// then
		verify(vocRepository).findById(deliveryDriverPenaltyRequest.getVocId());
		assertEquals(PenaltyApproval.APPROVED, voc.getPenalty().getPenaltyApproval());
		assertEquals(null, voc.getPenalty().getObjectionContent());
	}

	@Test
	void shouldHandleDriverPenalty_WhenDriverDenied() {
		// given
		DeliveryDriverPenaltyRequest deliveryDriverPenaltyRequest = DeliveryDriverPenaltyRequestFixture.create_Denied(
			"objection content");

		VocRequest vocRequest = createVocRequest();
		ClientCompany clientCompany = createClientCompany(vocRequest);
		DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);

		Voc voc = createVoc(clientCompany, deliveryDriver);
		when(vocRepository.findById(deliveryDriverPenaltyRequest.getVocId())).thenReturn(Optional.of(voc));

		// when
		vocService.handleDriverPenalty(deliveryDriverPenaltyRequest);

		// then
		verify(vocRepository).findById(deliveryDriverPenaltyRequest.getVocId());
		assertEquals(PenaltyApproval.DENIED, voc.getPenalty().getPenaltyApproval());
		assertEquals("objection content", voc.getPenalty().getObjectionContent());
	}

	@Test
	void shouldGetVocs_Success() {
		// given
		List<Voc> vocs = createVocs();
		when(vocRepository.findAll()).thenReturn(vocs);

		// when
		List<VocResponse> vocResponses = vocService.getVocs();

		// then
		assertEquals(vocs.size(), vocResponses.size());
	}

	@Test
	void shouldGetCompensations_Sucess() {
		// given
		VocRequest vocRequest = createVocRequest();
		List<Compensation> compensations = List.of(createCompensation(vocRequest));
		when(compensationRepository.findAll()).thenReturn(compensations);

		// when
		List<CompensationResponse> compensationResponses = vocService.getCompensations();

		// then
		assertEquals(compensations.size(), compensationResponses.size());
	}

	@NotNull
	private Compensation createCompensation(VocRequest vocRequest) {
		return CompensationFixture.create(
			VocFixture.create(vocRequest, createDeliveryDriver(vocRequest), createClientCompany(vocRequest)));
	}

	@NotNull
	private ClientCompany createClientCompany(VocRequest vocRequest) {
		return ClientCompanyFixture.create(vocRequest.getClientCompanyId(),
			VocFixture.createEmptyAsList(), 1000L, false);
	}

	@NotNull
	private DeliveryDriver createDeliveryDriver(VocRequest vocRequest) {
		return DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(),
			VocFixture.createList(), false,
			TransportCompanyFixture.create());
	}

	@NotNull
	private VocRequest createVocRequest() {
		return VocRequestFixture.create("voc request1");
	}

	@NotNull
	private Voc createVoc(ClientCompany clientCompany, DeliveryDriver deliveryDriver) {
		return Voc.createVoc(createVocRequest(), clientCompany, deliveryDriver);
	}

	private List<Voc> createVocs() {
		return VocFixture.createList();
	}
}
