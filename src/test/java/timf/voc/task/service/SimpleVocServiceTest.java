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

import timf.voc.task.domain.claim.SimpleClaimService;
import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.clientcompany.ClientCompanyInfo;
import timf.voc.task.domain.clientcompany.SimpleClientCompanyService;
import timf.voc.task.domain.notification.NotificationService;
import timf.voc.task.domain.transportcompany.DeliveryDriverInfo;
import timf.voc.task.domain.transportcompany.SimpleTransportCompanyService;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.CompensationInfo;
import timf.voc.task.domain.voc.SimpleVocService;
import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.voc.VocInfo;
import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.domain.voc.aggregate.Penalty;
import timf.voc.task.domain.voc.aggregate.Penalty.PenaltyApproval;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.CompensationFixture;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.DeliveryDriverPenaltyRequestFixture;
import timf.voc.task.fixture.PenaltyFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.infrastructure.voc.CompensationRepository;
import timf.voc.task.infrastructure.voc.VocRepository;

@ExtendWith(MockitoExtension.class)
class SimpleVocServiceTest {

	@InjectMocks
	SimpleVocService simpleVocService;

	@Mock
	SimpleClientCompanyService simpleClientCompanyService;

	@Mock
	SimpleTransportCompanyService simpleTransportCompanyService;

	@Mock
	SimpleClaimService simpleClaimService;

	@Mock
	NotificationService notificationService;

	@Mock
	VocRepository vocRepository;

	@Mock
	CompensationRepository compensationRepository;

	@Test
	void shouldSave_Success() {
		// given
		VocCommand.VocRegisterRequest vocRequest = createVocRequest();

		DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);
		when(simpleTransportCompanyService.retrieveDeliveryDriver(vocRequest.getDeliveryDriverToken()))
			.thenReturn(DeliveryDriverInfo.from(deliveryDriver));

		ClientCompany clientCompany = createClientCompany(vocRequest);
		when(simpleClientCompanyService.retrieveClientCompany(vocRequest.getClientCompanyId()))
			.thenReturn(ClientCompanyInfo.from(clientCompany));

		willDoNothing().given(simpleClaimService).updateStatusTrue(vocRequest);
		willDoNothing().given(notificationService).notifyNewVoc();

		// when
		simpleVocService.registerVoc(vocRequest);

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
		VocCommand.VocProcessRequest vocProcessRequest = DeliveryDriverPenaltyRequestFixture.create_Approved();

		VocCommand.VocRegisterRequest vocRequest = createVocRequest();
		ClientCompany clientCompany = createClientCompany(vocRequest);
		DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);

		Voc voc = createVoc(clientCompany, deliveryDriver);
		when(vocRepository.findById(vocProcessRequest.getVocId())).thenReturn(Optional.of(voc));

		// when
		simpleVocService.handleDriverApproval(vocProcessRequest);

		// then
		verify(vocRepository).findById(vocProcessRequest.getVocId());
		assertEquals(PenaltyApproval.APPROVED, voc.getPenalty().getPenaltyApproval());
		assertEquals(null, voc.getPenalty().getObjectionContent());
	}

	// @Test
	// void shouldHandleDriverPenalty_WhenDriverDenied() {
	// 	// given
	// 	DeliveryDriverDto.DeliveryDriverVocProcessRequest deliveryDriverPenaltyRequest = DeliveryDriverPenaltyRequestFixture.create_Denied(
	// 		"objection content");
	//
	// 	VocCommand.VocRegisterRequest vocRequest = createVocRequest();
	// 	ClientCompany clientCompany = createClientCompany(vocRequest);
	// 	DeliveryDriver deliveryDriver = createDeliveryDriver(vocRequest);
	//
	// 	Voc voc = createVoc(clientCompany, deliveryDriver);
	// 	when(vocRepository.findById(deliveryDriverPenaltyRequest.getVocId())).thenReturn(Optional.of(voc));
	//
	// 	// when
	// 	simpleVocService.handleDriverApproval(createVocRequest());
	//
	// 	// then
	// 	verify(vocRepository).findById(deliveryDriverPenaltyRequest.getVocId());
	// 	assertEquals(PenaltyApproval.DENIED, voc.getPenalty().getPenaltyApproval());
	// 	assertEquals("objection content", voc.getPenalty().getObjectionContent());
	// }

	@Test
	void shouldGetVocs_Success() {
		// given
		List<Voc> vocs = createVocs();
		when(vocRepository.findAll()).thenReturn(vocs);

		// when
		List<VocInfo> vocResponses = simpleVocService.retrieveVocs();

		// then
		assertEquals(vocs.size(), vocResponses.size());
	}

	@Test
	void shouldGetCompensations_Sucess() {
		// given
		VocCommand.VocRegisterRequest vocRequest = createVocRequest();
		List<Compensation> compensations = List.of(createCompensation(vocRequest));
		when(compensationRepository.findAll()).thenReturn(compensations);

		// when
		List<CompensationInfo> compensationResponses = simpleVocService.retrieveCompensations();

		// then
		assertEquals(compensations.size(), compensationResponses.size());
	}

	@NotNull
	private Compensation createCompensation(VocCommand.VocRegisterRequest vocRequest) {
		return CompensationFixture.create(
			VocFixture.create(vocRequest, createDeliveryDriver(vocRequest), createClientCompany(vocRequest)));
	}

	@NotNull
	private ClientCompany createClientCompany(VocCommand.VocRegisterRequest vocRequest) {
		return ClientCompanyFixture.create(vocRequest.getClientCompanyId(),
			VocFixture.createEmptyAsList(), 1000L, false);
	}

	@NotNull
	private DeliveryDriver createDeliveryDriver(VocCommand.VocRegisterRequest vocRequest) {
		return DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(),
			VocFixture.createList(), false,
			TransportCompanyFixture.create());
	}

	@NotNull
	private VocCommand.VocRegisterRequest createVocRequest() {
		return VocRequestFixture.createRegisterRequest("voc request1");
	}

	@NotNull
	private Voc createVoc(ClientCompany clientCompany, DeliveryDriver deliveryDriver) {
		VocCommand.VocRegisterRequest vocRequest = createVocRequest();
		Compensation compensation = CompensationFixture.create(vocRequest.getCompensationDescription(),
			vocRequest.getCompensationAmount());
		Penalty penalty = PenaltyFixture.create();
		return Voc.of(createVocRequest(), clientCompany, deliveryDriver, compensation, penalty);
	}

	private List<Voc> createVocs() {
		return VocFixture.createList();
	}
}
