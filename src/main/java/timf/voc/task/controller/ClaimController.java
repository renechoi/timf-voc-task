package timf.voc.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import timf.voc.task.service.ClaimService;

@Controller
@RequiredArgsConstructor
public class ClaimController {

	private final ClaimService claimService;

	@GetMapping("/claims")
	public String getClaims(Model model) {
		model.addAttribute("claims", claimService.getClaims());
		return "/claim/list";
	}

}
