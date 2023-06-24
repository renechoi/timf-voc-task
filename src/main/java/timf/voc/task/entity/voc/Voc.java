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
import timf.voc.task.config.util.TokenGenerator;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.auditEntity.BaseEntity;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;
import timf.voc.task.entity.voc.aggregate.Compensation;
import timf.voc.task.entity.voc.aggregate.Penalty;
import timf.voc.task.entity.voc.aggregate.PenaltyApproval;
import timf.voc.task.entity.voc.aggregate.VocStatus;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Voc extends BaseEntity {

	private static final String VOC_PREFIX = "voc_";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String vocToken;

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

	public Voc(VocRequest vocRequest, DeliveryDriver deliveryDriver, ClientCompany clientCompany,
		Compensation compensation, Penalty penalty) {
		this.vocToken = TokenGenerator.randomCharacterWithPrefix(VOC_PREFIX);
		this.description = vocRequest.getDescription();
		this.claimReceived = vocRequest.isClaimReceived();
		this.compensationRequested = vocRequest.isCompensationRequested();
		this.claimResponsibility = vocRequest.getClaimResponsibility();
		this.deliveryDriver = deliveryDriver.mappedWith(this);
		this.clientCompany = clientCompany.mappedWith(this);
		this.compensation = mapWith(compensation);
		this.penalty = mapWith(penalty);
		this.status = judgeStatus(vocRequest);
	}

	public static Voc createVoc(VocRequest vocRequest, ClientCompany clientCompany, DeliveryDriver deliveryDriver) {
		Compensation compensation = createCompensation(vocRequest);
		Penalty penalty = createPenalty(vocRequest);

		return new Voc(vocRequest, deliveryDriver, clientCompany, compensation, penalty);
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

	public void updatePenaltyStatus(PenaltyApproval penaltyApproval, String content) {
		this.penalty.updateApproval(penaltyApproval, content);
	}

	public void imposePenalty() {
		deliveryDriver.updatePenaltyAmount(penalty.getAmount());
	}

	public void compensate() {
		clientCompany.updateCompensation(compensation.getAmount());
	}

	public void updateStatus(VocStatus status) {
		this.status = status;
	}
}
