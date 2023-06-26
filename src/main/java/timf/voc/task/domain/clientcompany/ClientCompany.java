package timf.voc.task.domain.clientcompany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import timf.voc.task.config.util.TokenGenerator;
import timf.voc.task.domain.auditEntity.BaseEntity;
import timf.voc.task.domain.voc.aggregate.Voc;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCompany extends BaseEntity {

	private static final String CLIENT_COMPANY_PREFIX = "clientCompany_";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String clientCompanyToken;

	@Column(nullable = false)
	private String companyName;

	private String contacts;

	private String description;

	private Long compensationPayment;

	private boolean isCompensationPaid;

	@OneToMany(mappedBy = "clientCompany", cascade = CascadeType.ALL)
	private List<Voc> vocs;

	public ClientCompany mappedWith(Voc voc) {
		this.vocs.add(voc);
		return this;
	}

	public void updateCompensation(Long amount) {
		this.compensationPayment += amount;
	}

	public void generateToken() {
		this.clientCompanyToken = TokenGenerator.randomCharacterWithPrefix(CLIENT_COMPANY_PREFIX);
	}
}
