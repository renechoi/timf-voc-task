package timf.voc.task.fixture;

import timf.voc.task.entity.TransportCompany;

public class TransportCompanyFixture {

	public static TransportCompany create(){
		return TransportCompany.builder().companyName("company1").build();
	}
}
