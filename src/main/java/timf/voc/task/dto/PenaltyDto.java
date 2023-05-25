package timf.voc.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.entity.voc.aggregate.Penalty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyDto {

	private Long id;
	private String description;
	private Long amount;
	private VocDto voc;
	private boolean signed;
	private String objectionContent;

	public static PenaltyDto from(Penalty penalty) {
		return EntityAndDtoConverter.convert(penalty, PenaltyDto.class);
	}
}
