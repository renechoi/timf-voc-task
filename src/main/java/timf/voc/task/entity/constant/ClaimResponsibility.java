package timf.voc.task.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClaimResponsibility {
	TRANSPORT_COMPANY("운송사"),
	CLIENT_COMPANY("고객사");

	private final String description;

}
