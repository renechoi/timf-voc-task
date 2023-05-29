package timf.voc.task.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import timf.voc.task.dto.DeliveryDriverDto;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.DeliveryDriverMyPageResponse;
import timf.voc.task.dto.response.VocResponse;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.service.TransportCompanyService;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryDriverControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private TransportCompanyService transportCompanyService;

	@Autowired
	DeliveryDriverControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void getDeliveryDriver_Success() throws Exception {
		// given
		Long driverId = 1L;
		DeliveryDriver deliveryDriver = createDeliveryDriver(createVocRequest());
		List<Voc> vocs = createVocs();
		DeliveryDriverMyPageResponse expectedResponse = DeliveryDriverMyPageResponse.builder()
			.deliveryDriver(DeliveryDriverDto.from(deliveryDriver))
			.vcos(vocs.stream().map(VocResponse::from).collect(Collectors.toList()))
			.build();

		given(transportCompanyService.getMyPage(driverId)).willReturn(expectedResponse);

		// when
		mockMvc.perform(get("/delivery-driver/my-page").param("id", String.valueOf(driverId)))
			.andExpect(status().isOk())
			.andExpect(view().name("/delivery-driver/my-page"))
			.andExpect(model().attribute("myPage", expectedResponse));

		// then
		verify(transportCompanyService).getMyPage(driverId);
	}

	@NotNull
	private DeliveryDriver createDeliveryDriver(VocRequest vocRequest) {
		return DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(), VocFixture.createList(), false,
			TransportCompanyFixture.create());
	}

	@NotNull
	private VocRequest createVocRequest() {
		return VocRequestFixture.create("voc request1");
	}

	private List<Voc> createVocs() {
		return VocFixture.createList();
	}
}
