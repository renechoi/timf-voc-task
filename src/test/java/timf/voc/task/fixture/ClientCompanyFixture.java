package timf.voc.task.fixture;

import java.util.List;

import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.Compensation;
import timf.voc.task.entity.Voc;

public class ClientCompanyFixture {

	public static ClientCompany create(List<Voc> vocList, Long compensationPayment, boolean isCompensationPaid) {
		return ClientCompany.builder()
			.companyName("company1")
			.contacts("010-1234-1234")
			.description("client company content1")
			.compensationPayment(compensationPayment)
			.isCompensationPaid(isCompensationPaid)
			.vocList(vocList)
			.build();
	}
}
