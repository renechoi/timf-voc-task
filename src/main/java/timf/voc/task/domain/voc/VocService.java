package timf.voc.task.domain.voc;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface VocService {
	void registerVoc(VocCommand.VocRegisterRequest request);

	List<VocInfo> retrieveVocs();

	List<CompensationInfo> retrieveCompensations();

	void handleDriverApproval(VocCommand.VocProcessRequest request);
}
