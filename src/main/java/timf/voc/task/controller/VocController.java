package timf.voc.task.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.CompensationResponse;
import timf.voc.task.dto.response.VocResponse;
import timf.voc.task.service.VocService;

@Controller
@RequestMapping("/voc")
@RequiredArgsConstructor
public class VocController {

	private final VocService vocService;


	@GetMapping("/register")
	public String registerVoc(Model model, @RequestParam(required = false, name = "claimId") Long claimId){
		model.addAttribute("voc", VocRequest.builder().claimId(claimId).build());
		return "/voc/register";
	}


	@PostMapping("/register")
	public String registerVoc(VocRequest vocRequest){
		vocService.registerVoc(vocRequest);
		return "redirect:/voc/list";
	}


	@GetMapping("/list")
	public String getVocs(Model model){
		List<VocResponse> vocs = vocService.getVocs();

		model.addAttribute("vocs",vocs );
		return "/voc/list";
	}

	/**
	 * 배상(Compensation) API를 처리하는 컨트롤러를 별도로 만들지 않고 Voc 컨트롤러에서 처리하도록 하였다.
	 * 그 이유는 배상 엔티티의 경우 독립된 엔티티이긴 하지만
	 * 개념적으로 Voc aggregate의 종속되므로 root인 Voc의 관리하에 두어도 좋다고 보았기 때문이다.
	 * 마찬가지 맥락에서 Service 관련 로직 역시 vocService에서 담당하도록 하였다.
	 */
	@GetMapping("/compensation/list")
	public String getCompensations(Model model){
		List<CompensationResponse> compensations = vocService.getCompensations();
		model.addAttribute("compensations", compensations);
		return "/voc/compensation/list";
	}
}