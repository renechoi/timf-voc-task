package timf.voc.task.fixture;

import java.time.LocalDateTime;
import java.util.List;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.voc.aggregate.Voc;

public class ClientCompanyFixture {

	public static ClientCompany create(List<Voc> vocList, Long compensationPayment, boolean isCompensationPaid) {
		ClientCompany clientCompany = ClientCompany.builder()
			.companyName("company1")
			.contacts("010-1234-1234")
			.description("client company content1")
			.compensationPayment(compensationPayment)
			.isCompensationPaid(isCompensationPaid)
			.vocs(vocList)
			.build();

		clientCompany.setCreatedAt(LocalDateTime.now());
		return clientCompany;
	}

	public static ClientCompany create(Long id, List<Voc> vocList, Long compensationPayment, boolean isCompensationPaid) {
		ClientCompany clientCompany = ClientCompany.builder()
			.id(id)
			.companyName("company1")
			.contacts("010-1234-1234")
			.description("client company content1")
			.compensationPayment(compensationPayment)
			.isCompensationPaid(isCompensationPaid)
			.vocs(vocList)
			.build();

		clientCompany.setCreatedAt(LocalDateTime.now());
		return clientCompany;
	}
}
