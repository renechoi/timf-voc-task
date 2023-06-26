package timf.voc.task.domain.claim;

import lombok.Data;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.interfaces.claim.ClaimDto;

public class ClaimCommand {

	@Data
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

		public static ClaimDto.ClaimResponse from(Claim claim) {
			return EntityAndDtoConverter.convert(claim, ClaimDto.ClaimResponse.class);
		}
	}
}
