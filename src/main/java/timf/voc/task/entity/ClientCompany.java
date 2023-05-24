package timf.voc.task.entity;

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
import timf.voc.task.entity.auditEntity.BaseEntity;
import timf.voc.task.entity.voc.Voc;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCompany extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String companyName;

	private String contacts;

	private String description;

	private Long compensationPayment;

	private boolean isCompensationPaid;

	@OneToMany(mappedBy = "clientCompany", cascade = CascadeType.ALL)
	private List<Voc> vocList;

	public ClientCompany mappedWith(Voc voc) {
		this.vocList.add(voc);
		return this;
	}
}
