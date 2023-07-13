package timf.voc.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import timf.voc.task.domain.notification.NotificationService;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

	@InjectMocks
	NotificationService notificationService;

	@Test
	void shouldSubscribe_Success() {
		// given
		String id = "driver1";

		// when
		SseEmitter result = notificationService.subscribe(id);

		// then
		assertNotNull(result);
		assertTrue(notificationService.getEmittersMap().containsKey(id));
		assertEquals(result, notificationService.getEmittersMap().get(id));
	}

	@Test
	void shouldNotifyVocUpdate_Success() throws IOException {
		// given
		List<String> emitterKeys = new ArrayList<>();
		emitterKeys.add("driver1");
		emitterKeys.add("driver2");
		notificationService.getEmittersMap().putAll(emitterKeys.stream()
			.collect(Collectors.toMap(Function.identity(), key -> {
				SseEmitter emitter = mock(SseEmitter.class);
				try {
					willDoNothing().given(emitter).send("vocUpdated");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return emitter;
			})));

		// when
		notificationService.notifyNewVoc();

		// then
		// Verify the interactions with the emitters directly
		verify(notificationService.getEmittersMap().get("driver1")).send("vocUpdated");
		verify(notificationService.getEmittersMap().get("driver2")).send("vocUpdated");
		assertEquals(2, notificationService.getEmittersMap().size());
	}

	@Test
	void shouldProcessHeartbeat_Success() throws IOException {
		// given
		String id = "driver1";
		SseEmitter emitter = mock(SseEmitter.class);
		notificationService.getEmittersMap().put(id, emitter);

		// when
		notificationService.processHeartbeat(id);

		// then
		verify(emitter).send("heartbeat");
		assertTrue(notificationService.getEmittersMap().containsKey(id));
	}
}
