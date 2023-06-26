package timf.voc.task.domain.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void notifyNewVoc() {
		kafkaTemplate.send("new-voc", "vocCreated");
	}
}
