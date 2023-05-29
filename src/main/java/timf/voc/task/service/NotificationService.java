package timf.voc.task.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.Getter;

/**
 * 테스트 코드 작성을 위한 @Getter 추가
 */
@Service
@Getter
public class NotificationService {

    /**
     * 스레드 안정성 보장을 위한 동기화 컬렉션 사용
     */
    private final Map<String, SseEmitter> emittersMap = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String id) {
        SseEmitter emitter = new SseEmitter();
        emittersMap.put(id, emitter);
        emitter.onCompletion(() -> {
            emittersMap.remove(id);
            emitter.complete();
        });
        emitter.onError((ex) -> {
            emittersMap.remove(id);
            emitter.completeWithError(ex);
        });

        return emitter;
    }

    public void notifyVocUpdate() {
        List<String> deadEmitters = new ArrayList<>();
        emittersMap.forEach((key, emitter) -> {
            try {
                emitter.send("vocUpdated");
            } catch (IOException ex) {
                emitter.completeWithError(ex);
                deadEmitters.add(key);
            }
        });

        deadEmitters.forEach(emittersMap::remove);
    }

    public void processHeartbeat(String id) {
        SseEmitter emitter = emittersMap.get(id);
        if (emitter != null) {
            try {
                emitter.send("heartbeat");
            } catch (IOException ex) {
                emitter.completeWithError(ex);
                emittersMap.remove(id);
            }
        }
    }
}
