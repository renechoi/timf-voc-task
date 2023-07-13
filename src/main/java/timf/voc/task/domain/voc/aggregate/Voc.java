package timf.voc.task.domain.voc.aggregate;

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
import lombok.RequiredArgsConstructor;
import timf.voc.task.config.util.TokenGenerator;
import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.auditEntity.BaseEntity;

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



	public Voc(VocCommand.VocRegisterRequest vocRequest, DeliveryDriver deliveryDriver, ClientCompany clientCompany,
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

	public static Voc of(VocCommand.VocRegisterRequest vocRequest, ClientCompany clientCompany, DeliveryDriver deliveryDriver, Compensation compensation,Penalty penalty ) {
		return new Voc(vocRequest, deliveryDriver, clientCompany, compensation, penalty);
	}

	private Compensation mapWith(Compensation compensation) {
		return compensation == null ? null : compensation.mappedWith(this);
	}

	private Penalty mapWith(Penalty penalty) {
		return penalty == null ? null : penalty.mappedWith(this);
	}

	private static VocStatus judgeStatus(VocCommand.VocRegisterRequest vocRequest) {
		return vocRequest.isCompensationRequested() ? VocStatus.IN_PROGRESS : VocStatus.END;
	}

	public void updatePenaltyStatusApproved() {
		this.penalty.updateApproval();
	}

	public void imposePenalty() {
		deliveryDriver.updatePenaltyAmount(penalty.getAmount());
	}

	public void compensate() {
		clientCompany.updateCompensation(compensation.getAmount());
	}

	public void updateStatusEnd() {
		this.status = VocStatus.END;
	}

	@Getter
	@RequiredArgsConstructor
	public enum VocStatus {

		ACCEPTED("접수"),
		IN_PROGRESS("진행중"),
		END("종료");
		private final String description;
	}

	@Getter
	@RequiredArgsConstructor
	public enum ClaimResponsibility {

		TRANSPORT_COMPANY("운송사"),
		CLIENT_COMPANY("고객사");

		private final String description;
	}
}
