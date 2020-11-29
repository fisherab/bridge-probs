package uk.org.stevefisher.bridge.probs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Card {
	private int denomination;
	private Suit suit;

	private static Map<Integer, String> nameMap = Map.ofEntries(Map.entry(1, "A"), Map.entry(2, "2"), Map.entry(3, "3"),
			Map.entry(4, "4"), Map.entry(5, "5"), Map.entry(6, "6"), Map.entry(7, "7"), Map.entry(8, "8"),
			Map.entry(9, "9"), Map.entry(10, "T"), Map.entry(11, "J"), Map.entry(12, "Q"), Map.entry(13, "K"));

	private static Map<Integer, Integer> hcpMap = Map.ofEntries(Map.entry(1, 4), Map.entry(2, 0), Map.entry(3, 0),
			Map.entry(4, 0), Map.entry(5, 0), Map.entry(6, 0), Map.entry(7, 0), Map.entry(8, 0), Map.entry(9, 0),
			Map.entry(10, 0), Map.entry(11, 1), Map.entry(12, 2), Map.entry(13, 3));

	private static Map<Character, Integer> nameInvMap = new HashMap<Character, Integer>();

	static {
		for (Entry<Integer, String> entry : nameMap.entrySet()) {
			nameInvMap.put(entry.getValue().charAt(0), entry.getKey());
		}
	}

	public Card(int denomination, Suit suit) {
		this.denomination = denomination;
		this.suit = suit;
	}

	public Card(char denomination, Suit suit) {
		this.denomination = nameInvMap.get(denomination);
		this.suit = suit;
	}

	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	public int getDenomination() {
		return denomination;
	}

	public Suit getSuit() {
		return suit;
	};

	public String toString() {
		return nameMap.get(denomination) + suit.name().charAt(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denomination;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (denomination != other.denomination)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	public int getHcp() {
		return hcpMap.get(denomination);
	}

	public static String nameOf(int i) {
		return nameMap.get(i);
	}

}
