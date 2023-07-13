// package timf.voc.task.controller.api;
//
// import java.util.List;
//
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import lombok.RequiredArgsConstructor;
// import timf.voc.task.dto.api.ResponseDTO;
// import timf.voc.task.interfaces.claim.ClaimResponse;
// import timf.voc.task.domain.claim.SimpleClaimService;
//
// @RestController
// @RequiredArgsConstructor
// public class ClaimApiController extends ApiController {
//
// 	private final SimpleClaimService claimService;
//
// 	@GetMapping("/claims")
// 	public ResponseDTO<List<ClaimResponse>> getClaims() {
// 		return ok(claimService.getClaims());
// 	}
// }
