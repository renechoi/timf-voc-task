package timf.voc.task.domain.claim;

import java.util.List;

import timf.voc.task.domain.voc.VocCommand;

public interface ClaimService {
	void registerClaim(ClaimCommand.ClaimRequest claimRequest);

	List<ClaimInfo> retrieveClaims();

	void updateStatusTrue(VocCommand.VocRegisterRequest vocRequest);
}
