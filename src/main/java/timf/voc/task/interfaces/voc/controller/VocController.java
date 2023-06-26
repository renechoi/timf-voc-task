package timf.voc.task.interfaces.voc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import timf.voc.task.application.VocFacade;
import timf.voc.task.config.converter.VocDtoConverter;
import timf.voc.task.interfaces.transportcompany.DeliveryDriverDto.DeliveryDriverVocProcessRequest;
import timf.voc.task.interfaces.voc.VocDto.VocRequest;

@Controller
@RequestMapping("/voc")
@RequiredArgsConstructor
public class VocController {

	private final VocDtoConverter vocDtoConverter;
	private final VocFacade vocFacade;

	@GetMapping()
	public String register(Model model, @RequestParam(required = false, name = "claimId") Long claimId) {
		model.addAttribute("voc", VocRequest.builder().claimId(claimId).build());
		return "/voc/register";
	}

	@PostMapping()
	public String register(VocRequest vocRequest) {
		/**
		 * todo: 쟁점 사항 -> 변환 작업을 실행자 관점으로 할 것인가(to) 변환자 입장에서 할 것인가(from)
		 */
		vocFacade.registerVoc(vocDtoConverter.convertToCommand(vocRequest));
		return "redirect:/voc/list";
	}

	@GetMapping("/list")
	public String retrieve(Model model) {
		model.addAttribute("vocs", vocFacade.retrieveVocs());
		return "/voc/list";
	}

	/**
	 * 배상(Compensation) API를 처리하는 컨트롤러를 별도로 만들지 않고 Voc 컨트롤러에서 처리하도록 하였다.
	 * 그 이유는 배상 엔티티의 경우 독립된 엔티티이긴 하지만
	 * 개념적으로 Voc aggregate의 종속되므로 root인 Voc의 관리하에 두어도 좋다고 보았기 때문이다.
	 * 마찬가지 맥락에서 Service 관련 로직 역시 vocService에서 담당하도록 하였다.
	 */
	@GetMapping("/compensation/list")
	public String retrieveCompensations(Model model) {
		model.addAttribute("compensations", vocFacade.retrieveCompensations());
		return "/voc/compensation/list";
	}

	@PostMapping("/penalty/driver/approval")
	public String handleDriverApproval(DeliveryDriverVocProcessRequest request) {
		vocFacade.handleDriverApproval(vocDtoConverter.convertToCommand(request));
		return "redirect:/delivery-driver/my-page?id=" + request.getDeliveryDriverId();
	}

	@PostMapping("/penalty/driver/denial")
	public String handleDriverDenial(DeliveryDriverVocProcessRequest request) {
		// not yet implemented
		return "redirect:/delivery-driver/my-page?id=" + request.getDeliveryDriverId();
	}
}