package uk.org.stevefisher.bridge.probs;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import uk.org.stevefisher.bridge.probs.Card.Suit;

public class Stock {

	private Set<Card> cards = new HashSet<Card>();
	private Card[] cardArray = new Card[52];
	private Random rand = new Random();

	public Stock() {
		for (int denomination = 1; denomination <= 13; denomination++) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(denomination, suit));
			}
		}
	}

	public Hand dealHand() {
		Hand hand = new Hand();
		try {
			for (int i = 0; i < 13; i++) {
				hand.add(getRandomCard());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hand;
	}

	private Card getRandomCard() {
		int pos = rand.nextInt(cards.size());
		cardArray = cards.toArray(cardArray);
		Card card = cardArray[pos];
		cards.remove(card);
		return card;
	}

}
