package timf.voc.task.domain.voc;

import java.util.List;

public interface VocService {
	void registerVoc(VocCommand.VocRegisterRequest request);

	List<VocInfo> retrieveVocs();

	List<CompensationInfo> retrieveCompensations();

	void handleDriverApproval(VocCommand.VocProcessRequest request);
}
