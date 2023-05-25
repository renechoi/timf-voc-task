package timf.voc.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.request.ClaimRequest;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.ClaimResponse;
import timf.voc.task.entity.Claim;
import timf.voc.task.exception.ClaimNotFoundException;
import timf.voc.task.repository.ClaimRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClaimService {

	private final ClaimRepository claimRepository;

	public void registerClaim(ClaimRequest claimRequest) {
		claimRepository.save(Claim.from(claimRequest));
	}

	public List<ClaimResponse> getClaims() {
		List<Claim> claims = claimRepository.findAll();

		return claimRepository.findAll().stream().map(ClaimResponse::from).collect(Collectors.toList());
	}

	public void handleStatus(VocRequest vocRequest, boolean status) {
		if (vocRequest.isClaimReceived()) {
			Claim claim = claimRepository.findById(vocRequest.getClaimId()).orElseThrow(ClaimNotFoundException::new);
			claim.updateStatus(status);
		}
	}
}
