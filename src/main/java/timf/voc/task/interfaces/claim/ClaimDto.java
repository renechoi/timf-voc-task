package timf.voc.task.interfaces.claim;

import lombok.AllArgsConstructor;
import lombok.Data;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.domain.claim.ClaimInfo;

public class ClaimDto {

	@Data
	@AllArgsConstructor
	public static class ClaimRequest {
		private String personName;
		private String content;
	}

	@Data
	public static class ClaimResponse {
		private Long id;
		private String personName;
		private String content;
		private boolean handled;

		public static ClaimResponse from(ClaimInfo claimInfo) {
			return EntityAndDtoConverter.convert(claimInfo, ClaimResponse.class);
		}
	}


}
