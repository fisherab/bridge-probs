package uk.org.stevefisher.bridge.probs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

	Random rand = new Random();
	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) throws IOException {
		App app = new App();
		app.run();
	}

	void run() throws IOException {
		logger.info("Starting");
		List<Deal> deals = new ArrayList<Deal>();

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "1S");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "2S");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "3H");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "2C");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "1D");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "3C");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "1N");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "2N");
		}

		for (int i = 0; i < 0; i++) {
			generate1m(deals, "3N");
		}

		for (int i = 0; i < 0; i++) {
			generateSupportXorXX(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1MSupport(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1MPassedSupport(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalledSupport(deals);
		}

		Histogram hist1N = new Histogram("Intervention after weak 2 (P23X)", 10, 0.0, 1.0);
		for (int i = 0; i < 0; i++) {
			generateWeakTwo(deals, 'P', hist1N);
		}
		for (int i = 0; i < 0; i++) {
			generateWeakTwo(deals, '2', hist1N);
		}
		for (int i = 0; i < 0; i++) {
			generateWeakTwo(deals, '3', hist1N);
		}
		for (int i = 0; i < 0; i++) {
			generateWeakTwo(deals, 'X', hist1N);
		}
		System.out.println(hist1N);

		for (int i = 0; i < 0; i++) {
			generate2over1(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1NT(deals, "2C", 8, 9);
		}

		for (int i = 0; i < 0; i++) {
			generate1NT(deals, "2C", 10, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1NT(deals, "3N", 10, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1NT(deals, "2N", 0, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1NT(deals, "2D", 0, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1NT(deals, "4N", 0, 40);
		}

		for (int i = 0; i < 1; i++) {
			generate1MOvercalled(deals, 0, 2, 0, 40);
		}

		for (int i = 0; i < 1; i++) {
			generate1MOvercalled(deals, 3, 3, 8, 9);
		}

		for (int i = 0; i < 2; i++) {
			generate1MOvercalled(deals, 3, 3, 10, 40);
		}
		
		for (int i = 0; i < 1; i++) {
			generate1MOvercalled(deals, 4, 13, 0, 3);
		}
		
		for (int i = 0; i < 2; i++) {
			generate1MOvercalled(deals, 4, 13, 4, 9);
		}
		
		for (int i = 0; i < 5; i++) {
			generate1MOvercalled(deals, 4, 13, 10, 40);
		}

		Collections.shuffle(deals);

		int handNum = 0;
		try (PrintWriter lin = new PrintWriter(new FileWriter("mixed.lin"))) {
			for (Deal deal : deals) {
				handNum++;
				int dealer = (handNum) % 4 + 1;
				if (handNum % 4 == 0) {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), deal.getHand1(), deal.getHand2(),
							deal.getHand3());
				} else if (handNum % 4 == 1) {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), deal.getHand4(), deal.getHand1(),
							deal.getHand2());
				} else if (handNum % 4 == 2) {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), deal.getHand3(), deal.getHand4(),
							deal.getHand1());
				} else {
					addLine(lin, handNum, dealer, "bone".charAt(rand.nextInt(4)), deal.getHand2(), deal.getHand3(),
							deal.getHand4());
				}
			}
		}
		logger.info("Done " + deals.size());

	}

	private void generate1MOvercalled(List<Deal> deals, int minSupport, int maxSupport, int minHcp, int maxHcp) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (Set.of("PASS", "X").contains(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			int n = hand3.getCount(open);
			if (n < minSupport || n > maxSupport) {
				continue;
			}
			int hcp = hand3.getHcp();
			if (hcp < minHcp || hcp > maxHcp) {
				continue;
			}

			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}

	}

	private void generate1NT(List<Deal> deals, String response, int minHcp, int maxHcp) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!"1N".equals(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.getHcp() < 8 ? "PASS" : hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			if (hand3.getHcp() < minHcp || hand3.getHcp() > maxHcp) {
				continue;
			}
			String resp = hand3.getResponse(open);
			if (!resp.equals(response)) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}

	}

	private void generate2over1(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1D", "1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			if (!"PASS".equals(hand2.intervention(open))) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			if (hand3.getHcp() < 12) {
				continue;
			}
			String resp = hand3.getResponse(open);
			if (!Set.of("2C", "2D", "2H", "2S").contains(resp)) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}

	}

	private void generateWeakTwo(List<Deal> deals, char interv, Histogram hist) {

		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (open.charAt(0) != '2') {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			char letter = intervention.charAt(0);
			int offset = "P23X".indexOf(letter);
			hist.add(offset);
			if (interv != letter && interv != ' ') {
				continue;
			}
			Hand hand3 = stock.dealHand();
			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}

	}

	private void generate1MOvercalledSupport(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (Set.of("PASS", "X").contains(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			String resp = hand3.getResponse(open);
			if ("PASS".equals(resp)) {
				continue;
			}
			logger.debug("{} and {}", open, resp);
			Hand hand4 = stock.dealHand();
			if ("PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}

	}

	private void generate1MPassedSupport(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!"PASS".equals(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			open = hand2.getOpen5CM();
			if (!"PASS".equals(open)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			open = hand3.getOpen5CM();
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}
	}

	private void generate1MSupport(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			if (!"PASS".equals(hand2.intervention(open))) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			String resp = hand3.getResponse(open);
			if ("PASS".equals(resp)) {
				continue;
			}
			logger.debug("{} and {}", open, resp);
			Hand hand4 = stock.dealHand();
			if ("PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}

	}

	private void generateSupportXorXX(List<Deal> deals) {
		boolean found = false;
		while (!found) {
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
			String resp = hand3.getResponse(open);
			if (!Set.of("1H", "1S").contains(resp)) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			if ("PASS".equals(hand4.intervention(resp))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
		}
	}

	private void generate1m(List<Deal> deals, String response) {
		boolean found = false;
		while (!found) {
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
			if (!hand3.getResponse(open).equals(response)) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4));
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
