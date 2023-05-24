package timf.voc.task.entity.voc.aggregate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VocStatus {


	ACCEPTED("접수"),
	IN_PROGRESS("진행중"),
	END("종료");

	private final String description;
}
