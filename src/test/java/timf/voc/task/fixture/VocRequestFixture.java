package timf.voc.task.fixture;

import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.interfaces.voc.VocDto;

public class VocRequestFixture {

	public static VocDto.VocRequest create(String description) {
		return new VocDto.VocRequest(
			1L,
			description,
			true,
			true,
			Voc.ClaimResponsibility.TRANSPORT_COMPANY,
			1L,
			"drvier_",
			1L,
			"clientCompany_",
			1000L,
			1000L,
			"보상 설명1",
			"페널티 설명 1");
	}

	public static VocRegisterRequest createRegisterRequest(String description) {
		return new VocRegisterRequest(
			1L,
			description,
			true,
			true,
			Voc.ClaimResponsibility.TRANSPORT_COMPANY,
			1L,
			"driver_",
			1L,
			"clientCompany_",
			1000L,
			1000L,
			"보상 설명1",
			"페널티 설명 1");
	}

}
