package timf.voc.task.fixture;

import java.time.LocalDateTime;

import timf.voc.task.domain.transportcompany.aggregate.TransportCompany;

public class TransportCompanyFixture {

	public static TransportCompany create(){
		TransportCompany company = TransportCompany.builder().companyName("company1").build();
		company.setCreatedAt(LocalDateTime.now());
		return company;
	}
}
