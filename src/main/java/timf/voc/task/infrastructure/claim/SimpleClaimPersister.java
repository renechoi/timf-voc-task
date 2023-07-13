package timf.voc.task.infrastructure.claim;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.claim.Claim;
import timf.voc.task.domain.claim.ClaimPersister;

@Component
@RequiredArgsConstructor
public class SimpleClaimPersister implements ClaimPersister {

	private final ClaimRepository claimRepository;

	@Override
	public void save(Claim claim) {
		claimRepository.save(claim);
	}
}
