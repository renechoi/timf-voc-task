package timf.voc.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import timf.voc.task.service.NotificationService;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * 인증이 되어 있다면 Authentication Principal을 통해 인증 유저의 id 값을 사용할 것이나,
     * 현재는 인증 기능이 구현되어 있지 않으므로 기존에 get으로 송출한 param driverId를 그대로 받아온다.
     * @param id
     */
    @GetMapping("/my-page/notifications")
    public SseEmitter subscribeToNotifications(@RequestParam(required = false, name = "authenticatedId") String id) {
        return notificationService.subscribe(id);
    }

    /**
     * javascript에서 emitter를 구독하게 하였으나 일정 시간이 지나면 구독이 끊겨 알람을 받을 수 없는 문제를 해결하기 위해
     * 주기적으로 구독을 업데이트해주도록 heartBeat 방식으로 업데이트 하는 로직을 구현하였다.
     * js에서 15초 단위로 페이지를 갱신하여 무제한 구독을 가능하게 설정하였다.
     * @param id
     */
    @PostMapping("/my-page/heartbeat")
    public ResponseEntity<Void> processHeartbeat(@RequestParam("id") String id) {
        notificationService.processHeartbeat(id);
        return ResponseEntity.ok().build();
    }
}
