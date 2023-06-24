package timf.voc.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.request.DeliveryDriverPenaltyRequest;
import timf.voc.task.dto.response.DeliveryDriverMyPageResponse;
import timf.voc.task.service.TransportCompanyService;

@Controller
@RequiredArgsConstructor
public class DeliveryDriverController {

	private final TransportCompanyService transportCompanyService;

	@GetMapping("/delivery-driver/v1/my-page")
	public String getMyPage(Model model, @RequestParam(name = "id") Long id) {
		DeliveryDriverMyPageResponse myPage = transportCompanyService.getMyPage(id);
		model.addAttribute("myPage", myPage);
		model.addAttribute("penalty", DeliveryDriverPenaltyRequest.empty());
		return "/delivery-driver/my-page";
	}

	@GetMapping("/delivery-driver/v2/my-page")
	public String getMyPage(Model model, @RequestParam(name = "token") String token) {
		DeliveryDriverMyPageResponse myPage = transportCompanyService.getMyPage(token);
		model.addAttribute("myPage", myPage);
		model.addAttribute("penalty", DeliveryDriverPenaltyRequest.empty());
		return "/delivery-driver/my-page";
	}

	@PostMapping("/delivery-driver")
	@ResponseBody
	public String register(){
		transportCompanyService.registerDriver();
		return "ok";
	}
}
