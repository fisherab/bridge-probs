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
		
		for (int i = 0; i < 12; i++) {
			generateSupportXorXX(deals);
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
			if (! Set.of("1H","1S").contains(resp)) {
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
