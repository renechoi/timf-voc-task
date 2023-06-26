package timf.voc.task.interfaces.claim;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import timf.voc.task.application.ClaimFacade;
import timf.voc.task.config.converter.ClaimDtoConverter;
import timf.voc.task.config.converter.EntityAndDtoConverter;

@Controller
@RequiredArgsConstructor
public class ClaimController {

	private final ClaimFacade claimFacade;
	private final ClaimDtoConverter claimDtoConverter;

	@GetMapping("/claims")
	public String getClaims(Model model) {
		model.addAttribute("claims", claimDtoConverter.convertToResponse(claimFacade.getClaims()));
		model.addAttribute("claims", EntityAndDtoConverter.convert(claimFacade.getClaims(), ClaimDto.ClaimResponse.class));
		return "/claim/list";
	}

}
