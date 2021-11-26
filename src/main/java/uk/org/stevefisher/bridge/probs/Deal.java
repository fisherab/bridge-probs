package uk.org.stevefisher.bridge.probs;

public class Deal {

	private Hand hand1;
	private Hand hand2;
	private Hand hand3;
	private Hand hand4;
	private String open;
	private String intervention;
	private String third;
	private String fourth;

	public Deal(Hand hand1, Hand hand2, Hand hand3, Hand hand4, String open, String intervention, String third,
			String fourth) {
		this.hand1 = hand1;
		this.hand2 = hand2;
		this.hand3 = hand3;
		this.hand4 = hand4;
		this.open = open == "PASS" ? "-" : open;
		this.intervention = intervention == "PASS" ? "-" : intervention;
		this.third = third == "PASS" ? "-" : third;
		this.fourth = fourth == "PASS" ? "-" : fourth;
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

	public String getOpen() {
		return open;
	}

	public String getIntervention() {
		return intervention;
	}

	public String toString() {
		return hand1.getDisplay() + " " + hand2.getDisplay() + " " + hand3.getDisplay() + " " + hand4.getDisplay() + " "
				+ open + "(" + intervention + ")" + third + "(" + fourth + ") " + hand1.getHcp();
	}

}
