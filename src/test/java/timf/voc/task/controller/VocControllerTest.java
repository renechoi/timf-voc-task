package timf.voc.task.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import timf.voc.task.dto.request.DeliveryDriverPenaltyRequest;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.fixture.DeliveryDriverPenaltyRequestFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.service.VocService;

@SpringBootTest
@AutoConfigureMockMvc
class VocControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private VocService vocService;

	@Autowired
	VocControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void registerVoc_Success() throws Exception {
		// given
		VocRequest vocRequest = createVocRequest();
		willDoNothing().given(vocService).registerVoc(vocRequest);

		// when
		/**
		 * then 부분의 verify를 검증하기 위해선 voc 인스턴스를 동일한 인스턴스로 사용해야 한다.
		 * 이를 위해 vocService.registerVoc를 호출할 때 사용하는 vocRequest 객체를
		 * flash attribute로 전달하여 검증시에 동일한 객체를 사용하도록 한다.
		 */
		mockMvc.perform(post("/voc/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.flashAttr("vocRequest", vocRequest))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/voc/list"));

		// then
		verify(vocService).registerVoc(vocRequest);
	}

	@Test
	void handleDriverPenaltyApproval_RedirectsToDriverPage() throws Exception {
		// given
		DeliveryDriverPenaltyRequest penaltyRequest = createPenaltyRequest();

		// when
		mockMvc.perform(post("/voc/penalty/driver/approval").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.flashAttr("deliveryDriverPenaltyRequest", penaltyRequest))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/delivery-driver/my-page?id=" + penaltyRequest.getDeliveryDriverId()));

		// then
		verify(vocService).handleDriverPenalty(penaltyRequest);
	}

	@NotNull
	private DeliveryDriverPenaltyRequest createPenaltyRequest() {
		return DeliveryDriverPenaltyRequestFixture.create_Approved();
	}

	@NotNull
	private VocRequest createVocRequest() {
		return VocRequestFixture.create("voc request1");
	}
}
