package uk.org.stevefisher.bridge.probs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.org.stevefisher.bridge.probs.Card.Suit;

public class App {

	Random rand = new Random();
	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		App app = new App();
		app.run();
	}

	void run() {
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

		Histogram hist1N = new Histogram("Intervention after weak 2 (P23X)", 1, 0.0, 1.0);
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
			generate1NT(deals, 7, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalled(deals, 0, 2, 0, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalled(deals, 3, 3, 8, 9);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalled(deals, 3, 3, 10, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalled(deals, 4, 13, 0, 3);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalled(deals, 4, 13, 4, 9);
		}

		for (int i = 0; i < 0; i++) {
			generate1MOvercalled(deals, 4, 13, 10, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1MDoubled(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1mOvercalled(deals, 0, 13, 4, 40);
		}

		for (int i = 0; i < 0; i++) {
			generate1mDoubled(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1NCheckback(deals, 11, 16);
		}

		for (int i = 0; i < 0; i++) {
			generate2NCheckback(deals, 6, 12);
		}

		for (int i = 0; i < 0; i++) {
			generate1M1NPeter(deals);
		}

		for (int i = 0; i < 0; i++) {
			generate1MThirdHand(deals, 6, 11);
		}

		for (int i = 0; i < 0; i++) {
			generate2SuitedOvercall(deals);
		}

		for (int i = 0; i < 0; i++) {
			generateMultiLandy(deals, "2C");
		}

		for (int i = 0; i < 0; i++) {
			generateMultiLandy(deals, "2D");
		}

		for (int i = 0; i < 0; i++) {
			generateMultiLandy(deals, "2H");
		}

		for (int i = 0; i < 0; i++) {
			generateMultiLandy(deals, "2S");
		}

		for (int i = 0; i < 0; i++) {
			generateMultiLandy(deals, "2N");
		}

		for (int i = 0; i < 0; i++) {
			generateDrury(deals);
		}

		for (int i = 0; i < 0; i++) {
			generateOvercall(deals, "NT");
		}

		for (int i = 0; i < 0; i++) {
			generateOvercall(deals, "New suit");
		}

		for (int i = 0; i < 0; i++) {
			generateOvercall(deals, "Fit");
		}

		for (int i = 0; i < 0; i++) {
			generateOvercall(deals, "UCB");
		}

		for (int i = 0; i < 0; i++) {
			generateNegativeDouble(deals, 4);
		}

		for (int i = 0; i < 0; i++) {
			generateNegativeDouble(deals, 5);
		}

		for (int i = 0; i < 0; i++) {
			generateSplintersR(deals);
		}

		for (int i = 0; i < 0; i++) {
			generateSplintersJ(deals);
		}

		for (int i = 0; i < 0; i++) {
			generateSplinters2(deals);
		}

		for (int i = 0; i < 6; i++) {
			generateMinorResponseToN(deals);
		}

		generateContractFilteredMinorResponseToN(deals, Suit.CLUBS, 11, 1);
		generateContractFilteredMinorResponseToN(deals, Suit.DIAMONDS, 11, 1);
		generateContractFilteredMinorResponseToN(deals, Suit.CLUBS, 12, 1);
		generateContractFilteredMinorResponseToN(deals, Suit.DIAMONDS, 12, 1);
		generateContractFilteredMinorResponseToN(deals, Suit.CLUBS, 13, 1);
		generateContractFilteredMinorResponseToN(deals, Suit.DIAMONDS, 13, 1);

		generateFromPBN("/home/fisher/PJL/wk4.txt", 0, deals);

		Collections.shuffle(deals);

		int handNum = 0;
		try (PrintWriter lin = new PrintWriter(new FileWriter("mixed.lin"))) {
			for (Deal deal : deals) {
				handNum++;
				int dealer = (handNum + 1) % 4 + 1;
				if (handNum % 4 == 3) {
					addLine(lin, handNum, dealer, deal.getHand1(), deal.getHand2(), deal.getHand3());
				} else if (handNum % 4 == 0) {
					addLine(lin, handNum, dealer, deal.getHand4(), deal.getHand1(), deal.getHand2());
				} else if (handNum % 4 == 1) {
					addLine(lin, handNum, dealer, deal.getHand3(), deal.getHand4(), deal.getHand1());
				} else {
					addLine(lin, handNum, dealer, deal.getHand2(), deal.getHand3(), deal.getHand4());
				}
				System.out.println(deal);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done " + deals.size());
		logger.info("Done " + deals.size());

	}

	private class StreamGobbler extends Thread {
		InputStream is;
		List<String> data = new ArrayList<String>();

		public List<String> getData() {
			return data;
		}

		private StreamGobbler(InputStream is) {
			this.is = is;
		}

		@Override
		public void run() {
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null)
					data.add(line);

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private List<Deal> filterContracts(List<Deal> tdeal, Suit suit, int tricks) {
		List<Deal> newDeals = new ArrayList<Deal>();
		Process p;
		int skip = 0;
		if (suit == Suit.SPADES) {
			skip = 4;
		} else if (suit == Suit.HEARTS) {
			skip = 8;
		} else if (suit == Suit.DIAMONDS) {
			skip = 12;
		} else if (suit == Suit.CLUBS) {
			skip = 16;
		}
		try {
			p = new ProcessBuilder("/home/fisher/dds290complete/apps/CalcAllTablesPBN").start();
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream());
			StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream());
			outputGobbler.start();
			errorGobbler.start();
			PrintStream ps = new PrintStream(p.getOutputStream());
			for (Deal deal : tdeal) {
				ps.println("N:" + deal.getHand1().getDDSPBN() + " " + deal.getHand2().getDDSPBN() + " "
						+ deal.getHand3().getDDSPBN() + " " + deal.getHand4().getDDSPBN());
			}
			ps.close();
			p.waitFor();
			List<String> data = outputGobbler.getData();
			for (int i = 0; i < data.size(); i++) {
				String datum = data.get(i);
				Scanner sc = new Scanner(datum);
				for (int j = 0; j < skip; j++) {
					sc.nextInt();
				}
				int n = sc.nextInt();
				int s = sc.nextInt();
				if (n == tricks || s == tricks) {
					System.out.println(datum);
					newDeals.add(tdeal.get(i));
				}
				sc.close();
			}
			List<String> errors = errorGobbler.getData();
			if (!errors.isEmpty())
				System.out.println("Errors " + errorGobbler.getData());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return newDeals;

	}

	private void generateContractFilteredMinorResponseToN(List<Deal> deals, Suit suit, int tricks, int n) {
		List<Deal> tdeal = new ArrayList<Deal>();
		while (n > 0) {
			for (int i = 0; i < 32; i++) {
				generateMinorResponseToN(tdeal);
			}
			List<Deal> newDeals = filterContracts(tdeal, suit, tricks);
			while (newDeals.size() > n)
				newDeals.remove(0);
			deals.addAll(newDeals);
			n -= newDeals.size();
		}
	}

	private void generateMinorResponseToN(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!"1N".equals(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();

			Hand hand3 = stock.dealHand();
			int c = hand3.getCount(Suit.CLUBS);
			int d = hand3.getCount(Suit.DIAMONDS);
			if (c < 6 && d < 6 && c + d < 10) {
				continue;
			}

			Hand hand4 = stock.dealHand();

			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, null, null, null));
		}

	}

	private void generateSplintersR(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!"1H".equals(open) && !"1S".equals(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			if (!hand3.hasVoidorSingleton()) {
				continue;
			}
			if (hand3.getHcp() < 13) {
				continue;
			}
			if (hand3.getCount(open) < 4) {
				continue;
			}
			String response = hand3.getResponse(open);
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, response, null));
		}
	}

	private void generateSplintersJ(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			if (!hand1.hasVoidorSingleton()) {
				continue;
			}
			String open = hand1.getOpen5CM();
			if (!"1H".equals(open) && !"1S".equals(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			if (hand3.getHcp() < 13) {
				continue;
			}
			if (hand3.getCount(open) < 4) {
				continue;
			}
			String response = hand3.getResponse(open);
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, response, null));
		}
	}

	private void generateSplinters2(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			if (!hand1.hasVoidorSingleton()) {
				continue;
			}
			String open = hand1.getOpen5CM();
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, resp, null));
		}
	}

	private void generateOvercall(List<Deal> deals, String bidR) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM(); // Just want one of a suit opening
			if ("PASS".equals(open)) {
				continue;
			}
			if (open.charAt(1) == 'N') {
				continue;
			}
			if (open.charAt(0) != '1') {
				continue;
			}

			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (Set.of("X", "PASS").contains(intervention)) {
				continue;
			}
			if (intervention.charAt(1) == 'N') {
				continue;
			}
			if (intervention.charAt(1) == open.charAt(1)) { // Michaels
				continue;
			}

			Hand hand3 = stock.dealHand();
			if (hand3.getHcp() > 6)
				continue;

			Hand hand4 = stock.dealHand();
			int support = hand4.getCount(intervention);
			int hcp = hand4.getHcp();
			String bid = null;
			String bidT = null;
			if (support >= 3 && hcp >= 10) { // UCB
				bid = "2" + open.charAt(1);
				bidT = "UCB";
			}
			if (bid == null && support >= 3) { // Level of fit
				int maxLevel = (intervention.charAt(1) == 'c' || intervention.charAt(1) == 'd') ? 5 : 4;
				int level = Math.min(support - 1, maxLevel);
				bid = String.valueOf(level) + intervention.charAt(1);
				bidT = "Fit";
			}
			if (bid == null) {
				String consider = hand4.intervention(open);
				if (!Set.of("X", "PASS").contains(consider) && consider.charAt(1) != 'N'
						&& consider.charAt(1) != open.charAt(1)) {
					if (consider.charAt(0) < intervention.charAt(0)) {
						continue; // Could be better
					}
					if (consider.charAt(0) == intervention.charAt(0) && consider.charAt(1) < intervention.charAt(1)) {
						continue; // Could be better
					}
					bid = consider;
					bidT = "New suit";
				}
			}
			if (bid == null) {
				int nt = Math.min(3, (hcp - 7) / 3);
				if (nt >= Character.getNumericValue(intervention.charAt(0))) {
					bid = String.valueOf(nt) + "N";
					bidT = "NT";
				}
			}

			if (bid == null) {
				continue;
			}

			if (!bidT.equals(bidR)) {
				continue;
			}

			System.out.println(bidT + " " + bid + " " + support + " " + hcp);

			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, "PASS", bid));
		}

	}

	private void generateDrury(List<Deal> deals) {
		boolean found = false;
		while (!found) {

			Stock stock = new Stock();

			Hand hand1 = stock.dealHand();
			if (hand1.getHcp() < 8) {
				continue;
			}
			String bid1 = hand1.getOpen5CM();
			if (!bid1.equals("PASS")) {
				continue;
			}

			Hand hand2 = stock.dealHand();
			String bid2 = hand2.getOpen5CM();
			if (!bid2.equals("PASS")) {
				continue;
			}

			Hand hand3 = stock.dealHand();
			String open = hand3.getOpen5CM(11);
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}

			Hand hand4 = stock.dealHand();
			String intervention = hand4.intervention(open);
			if (!intervention.equals("PASS")) {
				continue;
			}

			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, "PASS", "PASS", open, intervention));
		}

	}

	private void generateMultiLandy(List<Deal> deals, String bid) {
		boolean found = false;
		while (!found) {

			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();

			String open = hand1.getOpen5CM();
			if (!open.equals("1N")) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);

			if (!intervention.equals(bid)) {
				continue;
			}

			Hand hand3 = stock.dealHand();

			Hand hand4 = stock.dealHand();

			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}

	}

	private void generate2SuitedOvercall(List<Deal> deals) {
		boolean found = false;
		while (!found) {

			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();

			String open = hand1.getOpen5CM();
			if (open.charAt(0) != '1') {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);

			if (intervention.charAt(0) != '2') {
				continue;
			}
			char strain = intervention.charAt(1);
			if (strain != 'N' && strain != open.charAt(1)) {
				continue;
			}
			Hand hand3 = stock.dealHand();

			Hand hand4 = stock.dealHand();

			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}

	}

	private void generate1MThirdHand(List<Deal> deals, int minHCP, int maxHCP) {

		boolean found = false;
		while (!found) {

			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();

			if (hand1.getHcp() < minHCP || hand1.getHcp() > maxHCP) {
				continue;
			}
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
			open = hand3.getOpen5CM(11);
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, "PASS", "PASS", open, null));
		}

	}

	private void generateFromPBN(String fname, int maxCount, List<Deal> deals) {
		if (maxCount == 0) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fname)))) {
			String st;
			int count = 0;
			Integer dealer = null;
			Hand[] hands = null;
			Integer from = null;
			while ((st = br.readLine()) != null) {
				if (st.contains("Dealer ")) {
					int q1 = st.indexOf('"');
					int q2 = st.indexOf('"', q1 + 1);
					dealer = "NESW".indexOf(st.substring(q1 + 1, q2));
				}
				if (st.contains("[Deal ")) {
					int q1 = st.indexOf('"');
					int q2 = st.indexOf('"', q1 + 1);
					int i = 0;
					hands = new Hand[4];
					from = "NESW".indexOf(st.substring(q1 + 1, q1 + 2));
					for (String hand : st.substring(q1 + 3, q2).split(" ")) {
						Hand h = new Hand();
						hands[i++] = h;
						h.setPBN(hand);
					}
				}
				if (dealer != null & hands != null) {
					count++;
					int rotate = dealer - from;
					deals.add(new Deal(hands[(rotate + 4) % 4], hands[(rotate + 5) % 4], hands[(rotate + 6) % 4],
							hands[(rotate + 7) % 4], null, null, null, null));
					if (count >= maxCount) {
						break;
					}
					dealer = null;
					hands = null;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void generate1NCheckback(List<Deal> deals, int minHCP, int maxHCP) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			if (hand1.getHcp() < 12 || hand1.getHcp() > 14 || !hand1.isBalanced()) {
				continue;
			}
			String open = hand1.getOpen5CM();
			if (!Set.of("1C", "1D", "1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			String response = hand3.getResponse(open);
			if (response.equals("1N") || response.charAt(0) != '1') {
				continue;
			}
			if (hand3.getHcp() < minHCP || hand3.getHcp() > maxHCP) {
				continue;
			}
			if (hand1.getCount(response) > 3) {
				continue;
			}

			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) { // Not good code for fourth hand
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, response, null));
		}

	}

	private void generate2NCheckback(List<Deal> deals, int minHCP, int maxHCP) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			if (hand1.getHcp() < 18 || hand1.getHcp() > 19 || !hand1.isBalanced()) {
				continue;
			}
			String open = hand1.getOpen5CM();
			if (!Set.of("1C", "1D", "1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			String response = hand3.getResponse(open);
			if (Set.of("PASS", "1N", "2N").contains(response)) {
				continue;
			}
			if (hand3.getHcp() < minHCP || hand3.getHcp() > maxHCP) {
				continue;
			}
			if (hand1.getCount(response) > 3) {
				continue;
			}
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) { // Not good code for fourth hand
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, response, null));
		}

	}

	private void generate1mDoubled(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1C", "1D").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"X".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();

			if (hand3.getHcp() < 4) {
				continue;
			}

			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}

	}

	private void generateNegativeDouble(List<Deal> deals, int len) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1C", "1D", "1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (Set.of("PASS", "X").contains(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			int n = hand3.getCount(open);
			if (n >= 3) {
				continue;
			}
			if (hand3.getLen() != len) {
				continue;
			}
			int hcp = hand3.getHcp();
			if (hcp < 6) {
				continue;
			}

			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}

	}

	private void generate1mOvercalled(List<Deal> deals, int minSupport, int maxSupport, int minHcp, int maxHcp) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!Set.of("1C", "1D").contains(open)) {
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}

	}

	private void generate1MDoubled(List<Deal> deals) {
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
			if (!"X".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();

			if (hand3.getHcp() < 4) {
				continue;
			}

			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
		}
	}

	private void generate1NT(List<Deal> deals, int minHcp, int maxHcp) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			String open = hand1.getOpen5CM();
			if (!"1N".equals(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();

			Hand hand3 = stock.dealHand();
			if (hand3.getHcp() < minHcp || hand3.getHcp() > maxHcp) {
				continue;
			}
			String resp = hand3.getResponse(open);

			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, null, resp, null));
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
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, resp, null));
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
			if (hist != null) {
				hist.add(offset);
			}
			if (interv != letter && interv != ' ') {
				continue;
			}
			Hand hand3 = stock.dealHand();
			Hand hand4 = stock.dealHand();
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, resp, null));
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
			String intervention = hand4.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, null, null));
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
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, resp, null));
		}

	}

	private void generate1M1NPeter(List<Deal> deals) {
		boolean found = false;
		while (!found) {
			Stock stock = new Stock();
			Hand hand1 = stock.dealHand();
			int hcp = hand1.getHcp();
			if (!hand1.isBalanced() || hcp < 12 || hcp > 14) {
				continue;
			}
			String open = hand1.getOpen5CM();
			if (!Set.of("1H", "1S").contains(open)) {
				continue;
			}
			Hand hand2 = stock.dealHand();
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
				continue;
			}
			Hand hand3 = stock.dealHand();
			String resp = hand3.get1NPeterResponse(open);
			if (!"1N".equals(resp)) {
				continue;
			}
			logger.debug("{} and {}", open, resp);
			Hand hand4 = stock.dealHand();
			if (!"PASS".equals(hand4.intervention(open))) {
				continue;
			}
			found = true;
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, resp, null));

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
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, resp, null));
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
			String intervention = hand2.intervention(open);
			if (!"PASS".equals(intervention)) {
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
			deals.add(new Deal(hand1, hand2, hand3, hand4, open, intervention, response, null));
		}
	}

	private String bone = "onebneboebonbone";

	private void addLine(PrintWriter lin, int handNum, int dealer, Hand hand1, Hand hand2, Hand hand3) {
		lin.print("qx|o" + handNum + ",Bd " + handNum);
		lin.print("|rh||ah|Bd " + handNum + "|md|" + dealer);
		lin.print(hand1.getDisplay() + "," + hand2.getDisplay() + "," + hand3.getDisplay());
		lin.print("|sv|" + bone.charAt((handNum - 1) % 16) + "|sk||pg||");
		lin.println();
	}

}
