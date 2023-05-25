package timf.voc.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.request.DeliveryDriverPenaltyRequest;
import timf.voc.task.dto.response.DeliveryDriverMyPageResponse;
import timf.voc.task.service.TransportCompanyService;

@Controller
@RequiredArgsConstructor
public class DeliveryDriverController {

	private final TransportCompanyService transportCompanyService;

	@GetMapping("/delivery-driver/my-page")
	public String getMyPage(Model model, @RequestParam(name = "id") Long id) {
		DeliveryDriverMyPageResponse myPage = transportCompanyService.getMyPage(id);
		model.addAttribute("myPage", myPage);
		model.addAttribute("penalty", DeliveryDriverPenaltyRequest.empty());
		return "/delivery-driver/my-page";
	}
}
