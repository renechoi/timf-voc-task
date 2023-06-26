package timf.voc.task.domain.claim;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.config.exception.ClaimNotFoundException;
import timf.voc.task.domain.voc.VocCommand;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimpleClaimService implements ClaimService{

	private final ClaimPersister claimPersister;
	private final ClaimReader claimReader;

	@Override
	public void registerClaim(ClaimCommand.ClaimRequest claimRequest) {
		claimPersister.save(Claim.from(claimRequest));
	}

	@Override
	public List<ClaimInfo> retrieveClaims() {
		return claimReader.get().stream().map(ClaimInfo::from).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void updateStatusTrue(VocCommand.VocRegisterRequest vocRequest) {
		if (vocRequest.isClaimReceived()) {
			claimReader.get(vocRequest.getClaimId()).orElseThrow(ClaimNotFoundException::new).updateStatusTrue();
		}
	}
}
