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

import timf.voc.task.domain.claim.Claim;
import timf.voc.task.config.exception.ClaimNotFoundException;
import timf.voc.task.domain.claim.SimpleClaimService;
import timf.voc.task.fixture.ClaimFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.interfaces.claim.ClaimDto;
import timf.voc.task.infrastructure.claim.ClaimRepository;


@ExtendWith(MockitoExtension.class)
class SimpleClaimServiceTest {

	@InjectMocks
	SimpleClaimService simpleClaimService;

	@Mock ClaimRepository claimRepository;

	@Test
	void shouldRegisterClaim_Success() {
		// given
		ClaimDto.ClaimRequest claimRequest = createClaimRequest();

		// when
		simpleClaimService.registerClaim(claimRequest);

		// then
		ArgumentCaptor<Claim> claimCaptor = ArgumentCaptor.forClass(Claim.class);
		verify(claimRepository).save(claimCaptor.capture());
		Claim savedClaim = claimCaptor.getValue();

		assertNotNull(savedClaim);
	}

	@Test
	void shouldGetClaims_Success() {
		// given
		List<Claim> claims = ClaimFixture.create_ListAsHandled();
		when(claimRepository.findAll()).thenReturn(claims);

		// when
		List<ClaimResponse> claimResponses = simpleClaimService.retrieveClaims();

		// then
		assertEquals(claims.size(), claimResponses.size());
	}

	@Test
	void shouldHandleStatus_WhenClaimReceivedAndStatusIsTrue() {
		// given
		VocRequest vocRequest = createVocRequest();
		Claim claim = ClaimFixture.create_withVocRequest(vocRequest);
		when(claimRepository.findById(vocRequest.getClaimId())).thenReturn(Optional.of(claim));

		// when
		simpleClaimService.updateStatusTrue(vocRequest, true);

		// then
		verify(claimRepository).findById(vocRequest.getClaimId());
		assertTrue(claim.isHandled());
	}

	@Test
	void shouldHandleStatus_WhenClaimReceivedAndStatusIsFalse() {
		// given
		VocRequest vocRequest = createVocRequest();
		Claim claim = ClaimFixture.create_withVocRequest(vocRequest);
		when(claimRepository.findById(vocRequest.getClaimId())).thenReturn(Optional.of(claim));

		// when
		simpleClaimService.updateStatusTrue(vocRequest, false);

		// then
		verify(claimRepository).findById(vocRequest.getClaimId());
		assertFalse(claim.isHandled());
	}

	@Test
	void shouldNotHandleStatus_WhenClaimNotReceived() {
		// given & when
		VocRequest vocRequest = createVocRequest();

		// then
		assertThrows(ClaimNotFoundException.class, () -> simpleClaimService.updateStatusTrue(vocRequest, true));
	}

	@NotNull
	private ClaimRequest createClaimRequest() {
		return new ClaimRequest("type", "description");
	}

	@NotNull
	private VocRequest createVocRequest() {
		return VocRequestFixture.create("voc request1");
	}
}
