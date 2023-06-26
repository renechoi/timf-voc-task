package timf.voc.task.fixture;

import java.util.Arrays;
import java.util.List;

import timf.voc.task.domain.claim.Claim;

public class ClaimFixture {

	public static Claim create_asHandled(){
		return Claim.builder().id(1L).content("content").handled(true).personName("name").build();
	}

	public static Claim create_asNotYetHandled(){
		return Claim.builder().id(1L).content("content").handled(false).personName("name").build();
	}

	public static Claim create_withVocRequest(VocRequest vocRequest){
		return Claim.builder().id(vocRequest.getClaimId()).content("content").handled(false).personName("name").build();
	}

	public static List<Claim> create_ListAsHandled(){
		return Arrays.asList(create_asHandled(), create_asHandled());
	}

	public static List<Claim> create_ListNotYetHandled(){
		return Arrays.asList(create_asNotYetHandled(), create_asNotYetHandled());
	}
}
