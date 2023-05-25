package timf.voc.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.dto.VocDto;
import timf.voc.task.entity.voc.aggregate.Compensation;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompensationResponse {

	private Long id;
	private String description;
	private Long amount;
	private VocDto voc;

	public static CompensationResponse from(Compensation compensation) {
		return CompensationResponse.builder()
			.id(compensation.getId())
			.description(compensation.getDescription())
			.amount(compensation.getAmount())
			.voc(VocDto.from(compensation.getVoc()))
			.build();
	}
}
