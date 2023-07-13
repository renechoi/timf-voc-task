package timf.voc.task.infrastructure.claim;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.claim.Claim;
import timf.voc.task.domain.claim.ClaimReader;

@Component
@RequiredArgsConstructor
public class SimpleClaimReader implements ClaimReader {

	private final ClaimRepository claimRepository;

	@Override
	public List<Claim> get() {
		return claimRepository.findAll();
	}

	@Override
	public Optional<Claim> get(Long claimId) {
		return claimRepository.findById(claimId);

	}
}
