package timf.voc.task.domain.claim;

import java.util.List;
import java.util.Optional;

public interface ClaimReader {
	List<Claim> get();

	Optional<Claim> get(Long claimId);
}
