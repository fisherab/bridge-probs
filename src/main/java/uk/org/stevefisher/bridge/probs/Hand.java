package uk.org.stevefisher.bridge.probs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.org.stevefisher.bridge.probs.Card.Suit;

public class Hand {

	private static final Logger logger = LogManager.getLogger(Hand.class);

	private Set<Card> cards = new HashSet<Card>();

	private boolean inited;

	private int hcp;

	private int fours;

	private HashMap<Suit, Set<Card>> suitCards;

	private boolean balanced;

	private Suit longest;

	private Suit longest2;

	private int len;

	private int len2;

	private static Map<Suit, String> bumrubMap = Map.ofEntries(Map.entry(Suit.CLUBS, "1H"),
			Map.entry(Suit.DIAMONDS, "1C"), Map.entry(Suit.HEARTS, "1D"), Map.entry(Suit.SPADES, "1D"));

	private static Map<String, Integer> loserMap = Map.ofEntries(Map.entry("AKQ", 0), Map.entry("AKx", 1),
			Map.entry("AK", 0), Map.entry("AQx", 1), Map.entry("AQ", 1), Map.entry("Axx", 2), Map.entry("Ax", 1),
			Map.entry("A", 0), Map.entry("KQx", 1), Map.entry("KQ", 1), Map.entry("Kxx", 2), Map.entry("Kx", 1),
			Map.entry("K", 1), Map.entry("Qxx", 2), Map.entry("Qx", 2), Map.entry("Q", 1), Map.entry("xxx", 3),
			Map.entry("xx", 2), Map.entry("x", 1));

	public Hand(String h) throws Exception {
		if (h.length() != 17) {
			throw new Exception("Hand defintion must have 17 characters");
		}
		Suit s = null;
		for (char c : h.toCharArray()) {
			if (c == 'S') {
				s = Suit.SPADES;
			} else if (c == 'H') {
				s = Suit.HEARTS;
			} else if (c == 'D') {
				s = Suit.DIAMONDS;
			} else if (c == 'C') {
				s = Suit.CLUBS;
			} else {
				add(new Card(c, s));
			}
		}
	}

	public Hand() {
	}

	public void add(Card card) throws Exception {
		if (!cards.add(card)) {
			throw new Exception("Card " + card + " already present");
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Card card : cards) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(card);
		}
		return sb.toString();

	}

	public int getHcp() {
		init();
		return hcp;
	}

	private void init() {
		if (!inited) {
			Map<Suit, Integer> suitHCP = new HashMap<Suit, Integer>();
			suitCards = new HashMap<Suit, Set<Card>>();
			for (Suit suit : Suit.values()) {
				suitHCP.put(suit, 0);
				suitCards.put(suit, new HashSet<Card>());
			}

			for (Card card : cards) {
				Suit suit = card.getSuit();
				suitHCP.put(suit, suitHCP.get(suit) + card.getHcp());
				suitCards.get(suit).add(card);
			}
			balanced = true;
			int doubletons = 0;
			hcp = 0;
			fours = 0;
			for (Suit suit : Suit.values()) {
				int n = suitCards.get(suit).size();
				if (n < 2) {
					balanced = false;
				}
				if (n == 2) {
					doubletons++;
				}
				if (n == 4) {
					fours++;
				}
				hcp += suitHCP.get(suit);
			}
			if (doubletons > 1) {
				balanced = false;
			}

			// Find two longest suits
			longest = null;
			len = 0;
			for (Suit suit : Suit.values()) {
				if (longest == null || suitCards.get(suit).size() > len) {
					longest = suit;
					len = suitCards.get(suit).size();
				}
			}
			longest2 = null;
			len2 = 0;
			for (Suit suit : Suit.values()) {
				if (suit != longest && (longest2 == null || suitCards.get(suit).size() > len2)) {
					longest2 = suit;
					len2 = suitCards.get(suit).size();
				}
			}
			inited = true;
		}

	}

	public String getOpen() {
		init();
		logger.debug("HCP " + hcp);
		if (fours == 3) {
			// 4441 hand
			logger.debug("4441");
			if (hcp >= 13 && hcp <= 19) {
				for (Suit suit : Suit.values()) {
					if (suitCards.get(suit).size() == 1) {
						logger.debug("BUMRUB");
						return bumrubMap.get(suit);
					}
				}
			} else if (hcp >= 19) {
				logger.debug(">19");
				return "BIG";
			} else {
				return "PASS";
			}
		}

		// Find suit to bid - though could still be NT
		Suit x = null;
		if (len > len2) {
			x = longest;
		} else if (len == 4 && (longest == Suit.HEARTS || longest == Suit.SPADES)
				&& (longest2 == Suit.HEARTS || longest2 == Suit.SPADES)) {
			x = Suit.HEARTS;
		} else if (longest.ordinal() > longest2.ordinal()) {
			x = longest;
		} else {
			x = longest2;
		}
		logger.debug("Longest suits are " + longest + ":" + len + " " + longest2 + ":" + len2);

		if (balanced && hcp >= 12 && hcp <= 14) {
			return "1N";
		}
		if (!balanced && hcp >= 12 && hcp <= 19) {
			logger.debug("Normal 1 of suit opening");
			return "1" + x.name().charAt(0);
		}
		if (!balanced && hcp + len + len2 >= 20) {
			logger.debug("Rule20");
			return "1" + x.name().charAt(0);
		}
		return "PASS";
	}

	public int getLTC() {
		Map<Suit, Set<Card>> suitCards = new HashMap<Suit, Set<Card>>();
		List<String> denoms = new ArrayList<String>(13);
		for (Suit suit : Suit.values()) {
			suitCards.put(suit, new HashSet<Card>());
		}

		for (Card card : cards) {
			Suit suit = card.getSuit();
			suitCards.get(suit).add(card);
		}

		int LTC = 0;
		for (Suit suit : Suit.values()) {
			denoms.clear();
			for (Card card : suitCards.get(suit)) {
				int n = card.getDenomination();
				if (n == 1) {
					denoms.add("A");
				} else if (n == 12) {
					denoms.add("Q");
				} else if (n == 13) {
					denoms.add("K");
				} else {
					denoms.add("x");
				}
			}
			Collections.sort(denoms);
			int ltc = 0;
			if (denoms.size() > 0) {
				String joined = String.join("", denoms);
				String shortJoined = joined.substring(0, Math.min(3, joined.length()));
				try {
					ltc = loserMap.get(shortJoined);
				} catch (Exception e) {
					logger.error("Unable to map {} to an LTC for a suit", shortJoined);
				}
				logger.debug("{} losers from {} are {}", suit, shortJoined, ltc);
				LTC += ltc;
			}

		}
		logger.debug("LTC is {}", LTC);
		return LTC;
	}

	public String getOvercall(String opening) {
		if (!"1N".equals(opening)) {
			return "PASS";
		}
		return "2C";
	}

	public String getResponse(String opening) {
		init();
		logger.debug("Response for {} to {} {} hcp {} with {}{}s with C quality {}", getDisplay(), opening, balanced?" Bal":"!Bal", hcp, len, longest.name().charAt(0), 
				getSQ(Suit.CLUBS));
		if ("1C".equals(opening)) {

			if (hcp == 5 || (hcp > 5 && hcp <= 9)) {
				if (len >= 7) {
					return "3" + longest.name().charAt(0);
				}
				if (len >= 6) {
					if (longest == Suit.CLUBS) {
						return "3C";
					}
					return "2" + longest.name().charAt(0);
				}
			}

			if (hcp >= 11 && suitCards.get(Suit.CLUBS).size() >= 4 && getSQ(Suit.CLUBS) >= 6 && !has4CM()) {
				return "2C";
			}

			if (hcp >= 6 && has4CM()) {
				if (hcp < 12) {
					if (suitCards.get(Suit.HEARTS).size() >= suitCards.get(Suit.SPADES).size()) {
						return "1H";
					} else {
						return "1S";
					}
				} else {
					return "1" + longest.name().charAt(0);
				}
			}

			if (balanced && hcp >= 6 && !has4CM()) {
				if (hcp <= 10) {
					return "1N";
				}

				if (hcp <= 12) {
					return "2N";
				}

				if (hcp <= 15) {
					return "3N";
				}
			}

		}
		return "PASS";
	}

	public String getDisplay() {
		init();
		String result = "";
		List<Suit> suits = new ArrayList<Suit>(Arrays.asList(Suit.values()));
		Collections.reverse(suits);
		for (Suit s : suits) {
			result += s.name().charAt(0);
			cards = suitCards.get(s);
			if (cards.contains(new Card('A', s))) {
				result += "A";
			}
			for (int i = 13; i > 1; i--) {
				if (cards.contains(new Card(i, s))) {
					result += Card.nameOf(i);
				}
			}
		}
		return result;
	}

	public boolean has5CM() {
		init();
		return suitCards.get(Suit.SPADES).size() >= 5 || suitCards.get(Suit.HEARTS).size() >= 5;
	}

	public String getOpen5CM() {
		init();

		if (balanced && hcp >= 15 && hcp <= 17) {
			return "1N";
		}

		if (hcp > 20) {
			// TODO encode 2C and 2N
			return "BIG";
		}

		if (hcp < 12 && hcp + len + len2 < 20) {
			// TODO Encode preempts
			return "PASS";
		}

		int lH = suitCards.get(Suit.HEARTS).size();
		int lS = suitCards.get(Suit.SPADES).size();
		boolean mH = lH >= 5;
		boolean mS = lS >= 5;
		if (mH && mS) {
			return lH >= lS ? "1H" : "1S";
		} else if (mH) {
			return "1H";
		} else if (mS) {
			return "1S";
		}

		int lC = suitCards.get(Suit.CLUBS).size();
		int lD = suitCards.get(Suit.DIAMONDS).size();
		if (fours == 3 && lD == 4) {
			return "1D";
		}
		if (balanced && lD >= 5) {
			return "1D";
		}
		if (!balanced && lD > lC) {
			return "1D";
		}
		return "1C";
	}

	public String intervention(String open) {
		init();
		if (hcp < 5) {
			return "PASS";
		}
		Suit suitOpened = null;
		for (Suit s : Suit.values()) {
			if (s.name().charAt(0) == open.charAt(1)) {
				suitOpened = s;
				break;
			}
		}

		// Consider overcall
		for (Suit s : Suit.values()) {
			if (s != suitOpened) {
				int sqNeeded = s.compareTo(suitOpened) < 0 ? 8 : 7;
				if (getSQ(s) >= sqNeeded) {
					return "" + (sqNeeded - 6) + s.name().charAt(0);
				}
			}
		}
		// Consider double
		if (hcp >= 12 && suitCards.get(suitOpened).size() < 3) {
			for (Suit s : Suit.values()) {
				if (s != suitOpened) {
					if (suitCards.get(s).size() < 3) {
						return "PASS";
					}
				}
			}
			return "X";
		}

		return "PASS";
	}

	public int getSQ(Suit s) {
		init();
		int sq = suitCards.get(s).size();
		for (Card c : suitCards.get(s)) {
			if (c.getDenomination() > 10 || c.getDenomination() == 1) {
				sq++;
			}
		}
		return sq;
	}

	public boolean has4CM() {
		init();
		return suitCards.get(Suit.SPADES).size() >= 4 || suitCards.get(Suit.HEARTS).size() >= 4;
	}

}
