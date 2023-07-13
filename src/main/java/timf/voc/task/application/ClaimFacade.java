package timf.voc.task.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.claim.ClaimInfo;
import timf.voc.task.domain.claim.SimpleClaimService;

@RequiredArgsConstructor
@Service
public class ClaimFacade {
	private final SimpleClaimService simpleClaimService;

	public List<ClaimInfo> getClaims() {
		return simpleClaimService.retrieveClaims();
	}
}
