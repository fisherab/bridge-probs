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
			throw new Exception("Hand definition must have 17 characters");
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

	public void setPBN(String h) throws Exception {
		if (h.length() != 16) {
			throw new Exception("PBN Hand definition must have 16 characters");
		}
		Suit s = Suit.SPADES;
		for (char c : h.toCharArray()) {
			if (c == '.') {
				s = Suit.values()[s.ordinal() - 1];
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

	public boolean isBalanced() {
		init();
		return balanced;
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

	public String getResponse(String opening) {
		init();

		String resp = null;

		if ("1N".equals(opening)) {
			if (suitCards.get(Suit.SPADES).size() >= 5) {
				resp = "2H";
			} else if (suitCards.get(Suit.HEARTS).size() >= 5) {
				resp = "2D";
			} else if (suitCards.get(Suit.DIAMONDS).size() >= 6) {
				resp = "2N";
			} else if (suitCards.get(Suit.CLUBS).size() >= 6) {
				resp = "2S";
			} else if (hcp >= 8 && hcp <= 9) {
				resp = "2C";
			} else if (hcp >= 16) {
				resp = "4N";
			} else if (hcp >= 10) {
				if (has4CM()) {
					resp = "2C";
				} else {
					resp = "3N";
				}
			}
		} else if ("1C".equals(opening)) {

			if (has4CM()) { // 4CM preferred to preempt
				if (hcp >= 6) {
					if (hcp < 12) {
						if (suitCards.get(Suit.HEARTS).size() >= suitCards.get(Suit.SPADES).size()) {
							resp = "1H";
						} else {
							resp = "1S";
						}
					} else {
						if (longest == Suit.CLUBS) {
							resp = "2C";
						} else {
							resp = "1" + longest.name().charAt(0);
						}
					}
				}

			} else { // No 4CM

				if (hcp >= 5 && hcp <= 9) { // Preempt
					if (len >= 7) {
						resp = "3" + longest.name().charAt(0);
					} else if (len >= 6) {
						if (longest == Suit.CLUBS) {
							resp = "3C";
						} else {
							resp = "2" + longest.name().charAt(0);
						}
					}
				}

				if (resp == null && hcp >= 11 && suitCards.get(Suit.CLUBS).size() >= 4) {
					resp = "2C"; // Inverted minor
				} else if (balanced && hcp >= 6) {
					if (hcp <= 10) {
						resp = "1N";
					} else if (hcp <= 12) {
						resp = "2N";
					} else if (hcp <= 15) {
						resp = "3N";
					}
				}
			}

		} else if ("1D".equals(opening)) {

			if (has4CM()) {
				if (hcp >= 6) {
					if (suitCards.get(Suit.HEARTS).size() >= suitCards.get(Suit.SPADES).size()) {
						resp = "1H";
					} else {
						resp = "1S";
					}
					if (hcp >= 12 && !Set.of(Suit.HEARTS, Suit.SPADES).contains(longest)) {
						resp = null;
					}
				}

			}

			if (resp == null && hcp > 5 && hcp <= 9) { // Preempt
				if (len >= 7) {
					resp = "3" + longest.name().charAt(0);
				} else if (len >= 6) {
					if (longest == Suit.DIAMONDS) {
						resp = "3D";
					} else {
						resp = "2" + longest.name().charAt(0);
					}
				}
			}

			if (resp == null && hcp >= 12 && longest == Suit.CLUBS) {
				resp = "2C"; // Two over one
			}

			if (resp == null && suitCards.get(Suit.DIAMONDS).size() >= 4) {
				if (hcp >= 11) {
					resp = "2D"; // Inverted minor
				} else {
					resp = "3D";
				}
			} else if (balanced && hcp >= 6) {
				if (hcp <= 10) {
					resp = "1N";
				} else if (hcp <= 12) {
					resp = "2N";
				} else if (hcp <= 15) {
					resp = "3N";
				}
			}

		} else {
			// 1 of a Major
			if (hcp < 4) {
				resp = "PASS";
			}
			char sc = opening.charAt(1);
			Suit s = getSuit(sc);
			int length = suitCards.get(s).size();

			if (resp == null && length == 3) {
				if (hcp >= 5 && hcp <= 9) {
					resp = "2" + sc;
				}
				if (hcp >= 10 && hcp <= 40) { // TODO change 40
					resp = "3C";
				}
				resp = "PASS"; // TODO
			} else if (resp == null && length == 4) {
				if (hcp >= 4 && hcp <= 6) {
					resp = "3" + sc;
				} else if (hcp >= 7 && hcp <= 9) {
					resp = s == Suit.SPADES ? "3H" : "2S";
				} else if (hcp >= 10 && hcp <= 12) {
					resp = "3D";
				} else if (hcp >= 40) { // TO DO change 13
					resp = "2N";
				}
				resp = "PASS"; // TODO
			} else if (resp == null & length == 5) {
				if (hcp >= 4 && hcp <= 9) {
					resp = "4" + sc;
				}
			}
			if (resp == null && s == Suit.HEARTS && suitCards.get(Suit.SPADES).size() >= 4 && hcp >= 6) {
				resp = "1S";
			}
			if (resp == null && hcp >= 12) { // Two over one
				resp = "2" + longest.name().charAt(0);
			}

		}
		if (resp == null) {
			resp = "PASS";
		}
		logger.debug("Response for {} to {} {} hcp {} with {}{}s => {}", getDisplay(), opening,
				balanced ? " Bal" : "!Bal", hcp, len, longest.name().charAt(0), resp);
		return resp;
	}

	private Suit getSuit(char sc) {
		for (Suit suit : Suit.values()) {
			if (suit.name().charAt(0) == sc)
				return suit;
		}
		return null;
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

	public String getOpen5CM(int minHCP) {
		init();

		if (hcp >= 5 && hcp <= 9 & len == 6) {
			Set<Card> suit = suitCards.get(longest);
			int top3 = 0;
			int top5 = 0;
			if (suit.contains(new Card('A', longest))) {
				top3++;
				top5++;
			}
			if (suit.contains(new Card('K', longest))) {
				top3++;
				top5++;
			}
			if (suit.contains(new Card('Q', longest))) {
				top3++;
				top5++;
			}
			if (suit.contains(new Card('J', longest))) {
				top5++;
			}
			if (suit.contains(new Card('T', longest))) {
				top5++;
			}
			if (top3 >= 2 || top5 >= 3) {
				return "2" + longest.name().charAt(0);
			}
		}

		if (balanced && hcp >= 15 && hcp <= 17) {
			return "1N";
		}

		if (hcp > 20) {
			// TODO encode 2C and 2N
			return "BIG";
		}

		if (hcp < minHCP && hcp + len + len2 < 20) {
			// TODO Encode preempts at 3 or higher level
			return "PASS";
		}

		int lH = suitCards.get(Suit.HEARTS).size();
		int lS = suitCards.get(Suit.SPADES).size();
		boolean mH = lH >= 5;
		boolean mS = lS >= 5;
		if (mH && mS) {
			return lH > lS ? "1H" : "1S";
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

		String result = null;
		int ls = suitCards.get(Suit.SPADES).size();
		int lh = suitCards.get(Suit.HEARTS).size();
		int lc = suitCards.get(Suit.CLUBS).size();
		int ld = suitCards.get(Suit.DIAMONDS).size();
		if ("1N".equals(open)) {

			if (hcp >= 16) {
				result = "X";
			}
			if (result == null && hcp >= 8 && ls >= 5 && lh >= 5) {
				result = "2C";
			}
			if (result == null && hcp >= 9 && ls + lh >= 9 && ls >= 4 && lh >= 4) {
				result = "2C";
			}
			if (result == null && hcp < 10) {
				result = "PASS";
			}
			if (result == null && (ls >= 6 || lh >= 6) && len2 <= 4) {
				result = "2D";
			}
			boolean fourCardMinor = lc >= 4 || ld >= 4;
			if (result == null && ls >= 5 && fourCardMinor) {
				result = "2S";
			}
			if (result == null && lh >= 5 && fourCardMinor) {
				result = "2H";
			}
			if (result == null && lc >= 5 && ld >= 5) {
				result = "2N";
			}
			if (result == null) {
				result = "PASS";
			}
			logger.debug("Intervention over {} on {} (hcp = {}, len = {},{},{},{} is {}", open, getDisplay(), hcp, ls,
					lh, ld, lc, result);
			return result;
		}

		Suit suitOpened = getSuitFromBid(open);

		char level = open.charAt(0);
		if (level == '1') {
			if (hcp < 5) {
				result = "PASS";
			} else if (hcp >= 17) {
				result = "X";
			}

			// Consider overcall
			if (result == null) {
				for (Suit s : Suit.values()) {
					if (s != suitOpened && s == longest) {
						int sqNeeded = s.compareTo(suitOpened) < 0 ? 8 : 7;
						if (getSQ(s) >= sqNeeded && len >= 5) {
							result = "" + (sqNeeded - 6) + s.name().charAt(0);
							break;
						}
					}
				}
			}
			// Consider double
			if (result == null) {
				if (hcp >= 12 && suitCards.get(suitOpened).size() < 3) {
					for (Suit s : Suit.values()) {
						if (s != suitOpened) {
							if (suitCards.get(s).size() < 3) {
								result = "PASS";
								break;
							}
						}
					}

					if (result == null) {
						result = "X";
					}
				}
			}
		} else if (level == '2') {
			if (result == null && hcp < 12) {
				result = "PASS";
			}

			// Consider overcall
			if (result == null) {
				for (Suit s : Suit.values()) { // This is selecting the wrong suit!
					if (s != suitOpened && s == longest) {
						int levelOf = s.compareTo(suitOpened) < 0 ? 3 : 2;
						if (len >= 6 || has5CM()) {
							if (levelOf == 2) {
								result = "2" + s.name().charAt(0);
								break;
							}
							if (hcp >= 15) { // Level 3 needed
								result = "3" + s.name().charAt(0);
								break;
							}
						}
					}
				}
			}
			// Consider double
			if (result == null) {
				if (suitCards.get(suitOpened).size() < 3) {
					for (Suit s : Suit.values()) {
						if (s != suitOpened) {
							if (suitCards.get(s).size() < 3) {
								result = "PASS";
								break;
							}
						}
					}
					if (result == null) {
						return "X";
					}
				}
			}
		}
		if (result == null) {
			result = "PASS";
		}
		logger.debug("Intervention over {} on {} (hcp = {}, len = {},{},{},{} is {}", open, getDisplay(), hcp, ls, lh,
				ld, lc, result);
		return result;

	}

	private Suit getSuitFromBid(String bid) {
		return getSuit(bid.charAt(1));
	}

	public int getSQ(Suit s) {
		init();
		int sq = suitCards.get(s).size();
		for (Card c : suitCards.get(s)) {
			if (c.getDenomination() >= 10 || c.getDenomination() == 1) {
				sq++;
			}
		}
		return sq;
	}

	public boolean has4CM() {
		init();
		return suitCards.get(Suit.SPADES).size() >= 4 || suitCards.get(Suit.HEARTS).size() >= 4;
	}

	public int getCount(String bid) {
		init();
		Suit suit = getSuitFromBid(bid);
		return suit == null ? -1 : suitCards.get(suit).size();
	}

	/**
	 * This is just for the special case following 1M where you don't have 3 or 4
	 * card support
	 */
	public String get1NPeterResponse(String opening) {
		init();

		String resp = null;

		// 1 of a Major
		if (hcp < 5) {
			resp = "PASS";
		}
		if (hcp > 11) {
			resp = "OTHER";
		}
		char sc = opening.charAt(1);
		Suit s = getSuit(sc);
		int length = suitCards.get(s).size();

		if (resp == null && length >= 3) {
			resp = "OTHER";
		} else if (s == Suit.HEARTS && suitCards.get(Suit.SPADES).size() >= 4) {
			resp = "1S";
		}

		if (resp == null) {
			resp = "1N";
		}
		logger.debug("Response for {} to {} {} hcp {} with {}{}s => {}", getDisplay(), opening,
				balanced ? " Bal" : "!Bal", hcp, len, longest.name().charAt(0), resp);
		return resp;
	}

	public String getCards(Suit s) {
		init();
		String result = "";
		cards = suitCards.get(s);

		if (cards.contains(new Card('T', s))) {
			if (!result.isEmpty()) {
				result += " ";
			}
			result += "10";
		}
		for (int i = 13; i > 0; i--) {
			if (i != 10) {
				if (cards.contains(new Card(i, s))) {
					if (!result.isEmpty()) {
						result += " ";
					}
					result += Card.nameOf(i);
				}
			}
		}
		return result;
	}

	public String getOpen5CM() {
		return getOpen5CM(12);
	}

}
