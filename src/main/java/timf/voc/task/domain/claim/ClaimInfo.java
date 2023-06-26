package timf.voc.task.domain.claim;

import lombok.Data;
import timf.voc.task.config.converter.EntityAndDtoConverter;

@Data
public class ClaimInfo {
	private Long id;
	private String claimToken;
	private String personName;
	private String content;
	private boolean handled;

	public static ClaimInfo from(Claim claim){
		return EntityAndDtoConverter.convert(claim, ClaimInfo.class);
	}
}
