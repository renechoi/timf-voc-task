package timf.voc.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.entity.Claim;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimResponse {

	private Long id;
	private String personName;
	private String content;
	private boolean handled;

	public static ClaimResponse from(Claim claim) {
		return EntityAndDtoConverter.convert(claim, ClaimResponse.class);
	}
}
