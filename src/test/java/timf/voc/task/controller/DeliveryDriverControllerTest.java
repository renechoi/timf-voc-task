package timf.voc.task.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import timf.voc.task.application.TransportCompanyFacade;
import timf.voc.task.domain.transportcompany.DeliveryDriverInfo;
import timf.voc.task.domain.transportcompany.SimpleTransportCompanyService;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryDriverControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private SimpleTransportCompanyService simpleTransportCompanyService;

	@MockBean
	private TransportCompanyFacade transportCompanyFacade;

	@Autowired
	DeliveryDriverControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void getDeliveryDriver_Success() throws Exception {
		// given
		String driverToken = "driver_";
		DeliveryDriver deliveryDriver = createDeliveryDriver(createVocRequest());
		List<Voc> vocs = createVocs();
		DeliveryDriverInfo expectedResponse = DeliveryDriverInfo.from(deliveryDriver);

		given(transportCompanyFacade.getMyPage(driverToken)).willReturn(expectedResponse);
		given(simpleTransportCompanyService.retrieveDeliveryDriver(driverToken)).willReturn(expectedResponse);

		// when
		mockMvc.perform(get("/delivery-driver/my-page").param("id", String.valueOf(driverToken)))
			.andExpect(status().isOk())
			.andExpect(view().name("/delivery-driver/my-page"))
			.andExpect(model().attribute("myPage", expectedResponse));

		// then
		verify(simpleTransportCompanyService).retrieveDeliveryDriver(driverToken);
	}

	@NotNull
	private DeliveryDriver createDeliveryDriver(VocCommand.VocRegisterRequest vocRequest) {
		return DeliveryDriverFixture.create(vocRequest.getDeliveryDriverId(), VocFixture.createList(), false,
			TransportCompanyFixture.create());
	}

	@NotNull
	private VocCommand.VocRegisterRequest createVocRequest() {
		return VocRequestFixture.createRegisterRequest("voc request1");
	}

	private List<Voc> createVocs() {
		return VocFixture.createList();
	}
}
