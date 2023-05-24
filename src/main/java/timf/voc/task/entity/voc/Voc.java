package timf.voc.task.entity.voc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import timf.voc.task.dto.VocRequest;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.auditEntity.BaseEntity;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;
import timf.voc.task.entity.voc.aggregate.Compensation;
import timf.voc.task.entity.voc.aggregate.Penalty;
import timf.voc.task.entity.voc.aggregate.VocStatus;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voc extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2000)
	private String description;

	private boolean claimReceived;

	private boolean compensationRequested;

	@Enumerated(EnumType.STRING)
	private ClaimResponsibility claimResponsibility;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_driver_id")
	private DeliveryDriver deliveryDriver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_company_id")
	private ClientCompany clientCompany;

	@OneToOne(mappedBy = "voc", cascade = CascadeType.ALL)
	private Compensation compensation;

	@OneToOne(mappedBy = "voc", cascade = CascadeType.ALL)
	private Penalty penalty;

	@Enumerated(EnumType.STRING)
	private VocStatus status;

	public static Voc createVoc(VocRequest vocRequest, ClientCompany clientCompany, DeliveryDriver deliveryDriver) {
		Compensation compensation = createCompensation(vocRequest);
		Penalty penalty = createPenalty(vocRequest);

		return Voc.builder()
			.description(vocRequest.getDescription())
			.claimReceived(vocRequest.isClaimReceived())
			.compensationRequested(vocRequest.isCompensationRequested())
			.claimResponsibility(vocRequest.getClaimResponsibility())
			.deliveryDriver(deliveryDriver)
			.clientCompany(clientCompany)
			.compensation(compensation)
			.penalty(penalty)
			.build();
	}

	private static Penalty createPenalty(VocRequest vocRequest) {
		if (vocRequest.isCompensationRequested()) {
			return Penalty.of(vocRequest.getPenaltyDescription(), vocRequest.getPenaltyAmount());
		}
		return null;
	}

	private static Compensation createCompensation(VocRequest vocRequest) {
		if (vocRequest.isCompensationRequested()) {
			return Compensation.of(vocRequest.getCompensationDescription(), vocRequest.getCompensationAmount());
		}

		return null;
	}
}
