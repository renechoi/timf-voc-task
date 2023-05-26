package timf.voc.task.entity.voc.aggregate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PenaltyApproval {

	UNCHECKED("미확인"),
	APPROVED("승인"),
	DENIED("이의제기");

	private final String description;
}
