package timf.voc.task.fixture;

import timf.voc.task.dto.VocRequest;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;

public class VocRequestFixture {

	public static VocRequest create(String description) {
		return new VocRequest(description,
			true,
			true,
			ClaimResponsibility.TRANSPORT_COMPANY,
			1L,
			1L,
			1000L,
			1000L,
			"보상 설명1",
			"페널티 설명 1");
	}
}
