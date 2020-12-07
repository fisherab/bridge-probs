package uk.org.stevefisher.bridge.probs;

public class Deal {

	private Hand hand1;
	private Hand hand2;
	private Hand hand3;
	private Hand hand4;

	public Deal(Hand hand1, Hand hand2, Hand hand3, Hand hand4) {
		this.hand1 = hand1;
		this.hand2 = hand2;
		this.hand3 = hand3;
		this.hand4 = hand4;
	}

	public Hand getHand1() {
		return hand1;
	}

	public Hand getHand2() {
		return hand2;
	}

	public Hand getHand3() {
		return hand3;
	}

	public Hand getHand4() {
		return hand4;
	}

}
