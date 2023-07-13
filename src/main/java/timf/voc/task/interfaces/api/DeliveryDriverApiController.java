// package timf.voc.task.controller.api;
//
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
//
// import lombok.RequiredArgsConstructor;
// import timf.voc.task.dto.api.ResponseDTO;
// import timf.voc.task.interfaces.deliverydriver.DeliveryDriverMyPageResponse;
// import timf.voc.task.domain.transportcompany.SimpleTransportCompanyService;
//
// @RestController
// @RequiredArgsConstructor
// public class DeliveryDriverApiController extends ApiController {
//
// 	private final SimpleTransportCompanyService transportCompanyService;
//
// 	@GetMapping("/delivery-driver/my-page")
// 	public ResponseDTO<DeliveryDriverMyPageResponse> getMyPage(@RequestParam(name = "id") Long id) {
// 		return ok(transportCompanyService.getMyPage(id));
// 	}
// }
