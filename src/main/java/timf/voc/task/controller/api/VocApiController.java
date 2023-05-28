// package timf.voc.task.controller.api;
//
// import java.util.List;
//
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import lombok.RequiredArgsConstructor;
// import timf.voc.task.dto.api.ResponseDTO;
// import timf.voc.task.dto.request.DeliveryDriverPenaltyRequest;
// import timf.voc.task.dto.request.VocRequest;
// import timf.voc.task.dto.response.CompensationResponse;
// import timf.voc.task.dto.response.VocResponse;
// import timf.voc.task.service.VocService;
//
// @RestController
// @RequestMapping("/api/voc")
// @RequiredArgsConstructor
// public class VocApiController extends ApiController {
//
// 	private final VocService vocService;
//
// 	@PostMapping("/register")
// 	public ResponseDTO<Void> registerVoc(@RequestBody VocRequest vocRequest) {
// 		vocService.registerVoc(vocRequest);
// 		return ok();
// 	}
//
// 	@GetMapping("/list")
// 	public ResponseDTO<List<VocResponse>> getVocs() {
// 		return ok(vocService.getVocs());
// 	}
//
// 	@GetMapping("/compensation/list")
// 	public ResponseDTO<List<CompensationResponse>> getCompensations() {
// 		return ok(vocService.getCompensations());
// 	}
//
// 	@PostMapping("/penalty/driver/approval")
// 	public ResponseDTO<Void> handleDriverPenaltyApproval(
// 		@RequestBody DeliveryDriverPenaltyRequest deliveryDriverPenaltyRequest) {
// 		vocService.handleDriverPenalty(deliveryDriverPenaltyRequest);
// 		return ok();
// 	}
// }