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

	@GetMapping("/compensation/list")
	public String getCompensations(Model model){
		List<CompensationResponse> compensations = vocService.getCompensations();
		model.addAttribute("compensations", compensations);
		return "/voc/compensation/list";
	}
}