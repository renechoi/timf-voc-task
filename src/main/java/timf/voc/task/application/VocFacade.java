package timf.voc.task.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.claim.SimpleClaimService;
import timf.voc.task.domain.notification.KafkaProducerService;
import timf.voc.task.domain.notification.NotificationService;
import timf.voc.task.domain.voc.CompensationInfo;
import timf.voc.task.domain.voc.SimpleVocService;
import timf.voc.task.domain.voc.VocCommand.VocProcessRequest;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.domain.voc.VocInfo;

@RequiredArgsConstructor
@Service
public class VocFacade {
	private final SimpleVocService simpleVocService;
	private final SimpleClaimService simpleClaimService;
	private final NotificationService notificationService;
	private final KafkaProducerService kafkaProducerService;

	public void registerVoc(VocRegisterRequest request) {
		simpleVocService.registerVoc(request);
		simpleClaimService.updateStatusTrue(request);

		notificationService.notifyNewVoc();
		kafkaProducerService.notifyNewVoc();

	}

	public List<VocInfo> retrieveVocs() {
		return simpleVocService.retrieveVocs();
	}

	public List<CompensationInfo> retrieveCompensations() {
		return simpleVocService.retrieveCompensations();
	}

	public void handleDriverApproval(VocProcessRequest request) {
		simpleVocService.handleDriverApproval(request);
	}
}
