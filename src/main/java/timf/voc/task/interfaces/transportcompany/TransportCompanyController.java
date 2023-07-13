package timf.voc.task.interfaces.transportcompany;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import timf.voc.task.application.TransportCompanyFacade;

@Controller
@RequiredArgsConstructor
public class TransportCompanyController {
	private final TransportCompanyFacade transportCompanyFacade;

	@GetMapping("/delivery-driver/v2/my-page")
	public String getMyPage(Model model, @RequestParam(name = "token") String token) {
		model.addAttribute("myPage", transportCompanyFacade.getMyPage(token));
		model.addAttribute("penalty", DeliveryDriverDto.DeliveryDriverVocProcessRequest.empty());
		return "/delivery-driver/my-page";
	}

	@PostMapping("/delivery-driver")
	@ResponseBody
	public String registerTest(){
		transportCompanyFacade.registerDriver();
		return "ok";
	}
}
