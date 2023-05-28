// package timf.voc.task.controller.api;
//
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
// import lombok.RequiredArgsConstructor;
// import timf.voc.task.dto.api.ResponseDTO;
// import timf.voc.task.service.NotificationService;
//
// @RestController
// @RequiredArgsConstructor
// public class NotificationApiController extends ApiController {
//
// 	private final NotificationService notificationService;
//
// 	@GetMapping("/my-page/notifications")
// 	public ResponseDTO<SseEmitter> subscribeToNotifications(
// 		@RequestParam(required = false, name = "authenticatedId") String id) {
// 		return ok(notificationService.subscribe(id));
// 	}
//
// 	@PostMapping("/my-page/heartbeat")
// 	public ResponseDTO<Void> processHeartbeat(@RequestParam("id") String id) {
// 		notificationService.processHeartbeat(id);
// 		return ok();
// 	}
// }
