package uk.org.stevefisher.bridge.probs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class App {

	Random rand = new Random();

	public static void main(String[] args) throws IOException {
		App app = new App();
		app.run();
	}

	void run() throws IOException {
		System.out.println("Starting");
		int numHands = 32;
		int handNum = 0;
		try (PrintWriter lin = new PrintWriter(new FileWriter("minorOpeningsNI.lin"))) {

			while (handNum < numHands) {
				Stock stock = new Stock();
				Hand hand1 = stock.dealHand();
				String open = hand1.getOpen5CM();
				if (!"1D".equals(open) && !"1C".equals(open)) {
					continue;
				}
				Hand hand2 = stock.dealHand();
				if (!"PASS".equals(hand2.intervention(open))) {
					continue;
				}
				Hand hand3 = stock.dealHand();
				if (hand3.getHcp() < 6) {
					continue;
				}
				if (hand3.has4CM() && rand.nextInt(100) < 75) {
					// Reject 4CM 75% of the time
					continue;
				}
				Hand hand4 = stock.dealHand();
				if (!"PASS".equals(hand4.intervention(open))) {
					continue;
				}

				handNum++;
				int dealer = (handNum) % 4 + 1;
				if (handNum % 4 == 0) {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), hand1, hand2, hand3);
				} else if (handNum % 4 == 1) {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), hand4, hand1, hand2);
				} else if (handNum % 4 == 2) {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), hand3, hand4, hand1);
				} else {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), hand2, hand3, hand4);
				}
			}
		}

	}

	private void addLine(PrintWriter lin, int handNum, int dealer, char vulnerability, Hand hand1, Hand hand2,
			Hand hand3) {
		lin.print("qx|o" + handNum + ",Bd " + handNum);
		lin.print("|rh||ah|Bd " + handNum + "|md|" + dealer);
		lin.print(hand1.getDisplay() + "," + hand2.getDisplay() + "," + hand3.getDisplay());
		lin.print("|sv|" + vulnerability + "|sk||pg||");
		lin.println();
	}

}
