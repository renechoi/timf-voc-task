package timf.voc.task.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import timf.voc.task.application.ClaimFacade;
import timf.voc.task.domain.claim.Claim;
import timf.voc.task.domain.claim.ClaimInfo;
import timf.voc.task.domain.claim.SimpleClaimService;
import timf.voc.task.fixture.ClaimFixture;

@SpringBootTest
@AutoConfigureMockMvc
class ClaimControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private SimpleClaimService simpleClaimService;

	@MockBean
	private ClaimFacade claimFacade;

	@Autowired
	ClaimControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void getClaims_ReturnsClaimListPage() throws Exception {
		// given
		List<Claim> claims = List.of(ClaimFixture.create_asHandled(), ClaimFixture.create_asHandled());

		List<ClaimInfo> claimResponses = List.of(ClaimInfo.from(claims.get(0)));

		when(claimFacade.getClaims()).thenReturn(claimResponses);
		when(simpleClaimService.retrieveClaims()).thenReturn(claimResponses);

		// when
		mockMvc.perform(get("/claims"))
			.andExpect(status().isOk())
			.andExpect(view().name("/claim/list"))
			.andExpect(model().attributeExists("claims"));

		// then
		verify(simpleClaimService).retrieveClaims();
	}
}
