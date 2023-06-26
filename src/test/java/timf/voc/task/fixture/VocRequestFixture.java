package timf.voc.task.fixture;

public class VocRequestFixture {

	public static VocRequest create(String description) {
		return new VocRequest(1L,description,
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
