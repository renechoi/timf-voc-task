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
import lombok.Getter;
import lombok.NoArgsConstructor;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.auditEntity.BaseEntity;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;
import timf.voc.task.entity.voc.aggregate.Compensation;
import timf.voc.task.entity.voc.aggregate.Penalty;
import timf.voc.task.entity.voc.aggregate.VocStatus;

@Entity
@Builder
@Getter
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

	public Voc(String description, boolean claimReceived, boolean compensationRequested,
		ClaimResponsibility claimResponsibility, DeliveryDriver deliveryDriver, ClientCompany clientCompany,
		Compensation compensation, Penalty penalty, VocStatus vocStatus) {
		this.description = description;
		this.claimReceived = claimReceived;
		this.compensationRequested = compensationRequested;
		this.claimResponsibility = claimResponsibility;
		this.deliveryDriver = deliveryDriver.mappedWith(this);
		this.clientCompany = clientCompany.mappedWith(this);
		this.compensation = mapWith(compensation);
		this.penalty = mapWith(penalty);
		this.status = vocStatus;
	}

	public static Voc createVoc(VocRequest vocRequest, ClientCompany clientCompany, DeliveryDriver deliveryDriver) {
		Compensation compensation = createCompensation(vocRequest);
		Penalty penalty = createPenalty(vocRequest);

		return new Voc(vocRequest.getDescription(), vocRequest.isClaimReceived(), vocRequest.isCompensationRequested(),
			vocRequest.getClaimResponsibility(), deliveryDriver, clientCompany, compensation, penalty,
			judgeStatus(vocRequest));
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

	private Compensation mapWith(Compensation compensation) {
		return compensation == null ? null : compensation.mappedWith(this);
	}

	private Penalty mapWith(Penalty penalty) {
		return penalty == null ? null : penalty.mappedWith(this);
	}

	private static VocStatus judgeStatus(VocRequest vocRequest) {
		return vocRequest.isCompensationRequested() ? VocStatus.IN_PROGRESS : VocStatus.END;
	}
}
