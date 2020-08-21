package uk.org.stevefisher.bridge.probs;

import java.util.ArrayList;
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

	private static Map<Suit, String> bumrubMap = Map.ofEntries(Map.entry(Suit.CLUBS, "1H"),
			Map.entry(Suit.DIAMONDS, "1C"), Map.entry(Suit.HEARTS, "1D"), Map.entry(Suit.SPADES, "1D"));

	private static Map<String, Integer> loserMap = Map.ofEntries(Map.entry("AKQ", 0), Map.entry("AKx", 1),
			Map.entry("AK", 0), Map.entry("AQx", 1), Map.entry("AQ", 1), Map.entry("Axx", 2), Map.entry("Ax", 1),
			Map.entry("A", 0), Map.entry("KQx", 1), Map.entry("KQ", 1), Map.entry("Kxx", 2), Map.entry("Kx", 1),
			Map.entry("K", 1), Map.entry("Qxx", 2), Map.entry("Qx", 2), Map.entry("Q", 1), Map.entry("xxx", 3),
			Map.entry("xx", 2), Map.entry("x", 1));

	public void add(Card card) throws Exception {
		if (!cards.add(card)) {
			throw new Exception("Card already present");
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
		int hcp = 0;
		for (Card card : cards) {
			hcp += card.getHcp();
		}
		return hcp;
	}

	public String getOpen() {
		Map<Suit, Integer> suitHCP = new HashMap<Suit, Integer>();
		Map<Suit, Set<Card>> suitCards = new HashMap<Suit, Set<Card>>();
		for (Suit suit : Suit.values()) {
			suitHCP.put(suit, 0);
			suitCards.put(suit, new HashSet<Card>());
		}

		for (Card card : cards) {
			Suit suit = card.getSuit();
			suitHCP.put(suit, suitHCP.get(suit) + card.getHcp());
			suitCards.get(suit).add(card);
		}
		boolean balanced = true;
		int doubletons = 0;
		int hcp = 0;
		int fours = 0;
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

		// Find two longest suits
		Suit longest = null;
		int len = 0;
		for (Suit suit : Suit.values()) {
			if (longest == null || suitCards.get(suit).size() > len) {
				longest = suit;
				len = suitCards.get(suit).size();
			}
		}
		Suit longest2 = null;
		int len2 = 0;
		for (Suit suit : Suit.values()) {
			if (suit != longest && (longest2 == null || suitCards.get(suit).size() > len2)) {
				longest2 = suit;
				len2 = suitCards.get(suit).size();
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
		if (! "1N".equals(opening)) {
			return "PASS";
		}
		return "2C";
	}

}
