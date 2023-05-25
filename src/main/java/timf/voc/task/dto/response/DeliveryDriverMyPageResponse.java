package timf.voc.task.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.dto.DeliveryDriverDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverMyPageResponse {

	private DeliveryDriverDto deliveryDriver;

	private List<VocResponse> vcos;
}
