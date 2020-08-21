package uk.org.stevefisher.bridge.probs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import uk.org.stevefisher.bridge.probs.Card.Suit;

class ProbTest {

	private static final Logger logger = LogManager.getLogger(ProbTest.class);

	private Hand createHand1() {
		Hand hand = new Hand();
		try {
			hand.add(new Card('A', Suit.SPADES));
			hand.add(new Card('K', Suit.SPADES));
			hand.add(new Card(10, Suit.SPADES));
			hand.add(new Card(2, Suit.SPADES));
			hand.add(new Card('Q', Suit.HEARTS));
			hand.add(new Card('J', Suit.HEARTS));
			hand.add(new Card(2, Suit.HEARTS));
			hand.add(new Card(3, Suit.HEARTS));
			hand.add(new Card(4, Suit.DIAMONDS));
			hand.add(new Card(5, Suit.DIAMONDS));
			hand.add(new Card(2, Suit.DIAMONDS));
			hand.add(new Card('Q', Suit.DIAMONDS));
			hand.add(new Card(8, Suit.SPADES));
		} catch (Exception e) {
			logger.error(e);
		}
		return hand;
	}

	private Hand createHand2() {
		Hand hand = new Hand();
		try {
			hand.add(new Card('A', Suit.SPADES));
			hand.add(new Card('K', Suit.SPADES));
			hand.add(new Card('J', Suit.SPADES));
			hand.add(new Card(2, Suit.SPADES));
			hand.add(new Card('Q', Suit.HEARTS));
			hand.add(new Card('J', Suit.HEARTS));
			hand.add(new Card(2, Suit.HEARTS));
			hand.add(new Card(3, Suit.HEARTS));
			hand.add(new Card(4, Suit.DIAMONDS));
			hand.add(new Card(5, Suit.DIAMONDS));
			hand.add(new Card(2, Suit.DIAMONDS));
			hand.add(new Card('Q', Suit.CLUBS));
			hand.add(new Card(8, Suit.CLUBS));
		} catch (Exception e) {
			logger.error(e);
		}
		return hand;
	}

	private Hand createHand3() {
		Hand hand = new Hand();
		try {
			hand.add(new Card('A', Suit.SPADES));
			hand.add(new Card('K', Suit.SPADES));
			hand.add(new Card('J', Suit.SPADES));
			hand.add(new Card(2, Suit.SPADES));
			hand.add(new Card('T', Suit.HEARTS));
			hand.add(new Card('J', Suit.HEARTS));
			hand.add(new Card(2, Suit.HEARTS));
			hand.add(new Card(3, Suit.HEARTS));
			hand.add(new Card(4, Suit.HEARTS));
			hand.add(new Card(5, Suit.DIAMONDS));
			hand.add(new Card(2, Suit.DIAMONDS));
			hand.add(new Card('Q', Suit.CLUBS));
			hand.add(new Card(8, Suit.CLUBS));
		} catch (Exception e) {
			logger.error(e);
		}
		return hand;
	}

	private Hand createHand4() {
		Hand hand = new Hand();
		try {
			hand.add(new Card('A', Suit.SPADES));
			hand.add(new Card('K', Suit.SPADES));
			hand.add(new Card('J', Suit.SPADES));
			hand.add(new Card(2, Suit.SPADES));
			hand.add(new Card('Q', Suit.HEARTS));
			hand.add(new Card('J', Suit.HEARTS));
			hand.add(new Card(2, Suit.HEARTS));
			hand.add(new Card(3, Suit.HEARTS));
			hand.add(new Card(4, Suit.DIAMONDS));
			hand.add(new Card(5, Suit.DIAMONDS));
			hand.add(new Card(2, Suit.DIAMONDS));
			hand.add(new Card('Q', Suit.DIAMONDS));
			hand.add(new Card(8, Suit.CLUBS));
		} catch (Exception e) {
			logger.error(e);
		}
		return hand;
	}

	@Test
	void testCard() {
		Card card = new Card(1, Suit.SPADES);
		assertEquals(1, card.getDenomination());
		assertEquals(Suit.SPADES, card.getSuit());
	}

	@Test
	void testCardEquality() {
		assertTrue(new Card(1, Suit.SPADES).equals(new Card(1, Suit.SPADES)));
	}

	@Test
	void testDealHand() {
		Stock stock = new Stock();
		int hcp = 0;
		for (int i = 1; i <= 4; i++) {
			Hand hand = stock.dealHand();
			hcp += hand.getHcp();
		}
		assertEquals(40, hcp);
	}

	@Disabled("Not ready yet")
	@Test
	void testHisto() {
		Histogram hist = new Histogram("Test", 10, 3.0, 1.0);
		hist.add(0);
		hist.add(1);
		hist.add(5);
		hist.add(5);
		hist.add(6);
		hist.add(6);
		hist.add(6);
		hist.add(7);
		hist.add(7);
		hist.add(8);
		hist.add(10);
		hist.add(20);
		assertEquals(
				"Histogram 'Test' has 12 entries with 2 underflow and 1 overflow\r\n" + "    3.0000         0 \r\n"
						+ "    4.0000         0 \r\n" + "    5.0000         2 **\r\n" + "    6.0000         3 ***\r\n"
						+ "    7.0000         2 **\r\n" + "    8.0000         1 *\r\n" + "    9.0000         0 \r\n"
						+ "   10.0000         1 *\r\n" + "   11.0000         0 \r\n" + "   12.0000         0 \r\n" + "",
				hist);
		System.out.println(hist);
	}

	@Test
	void testHCP() {
		assertEquals(12, createHand1().getHcp());
		assertEquals(13, createHand2().getHcp());
		assertEquals(11, createHand3().getHcp());
		assertEquals(13, createHand4().getHcp());
	}

	@Test
	void testLTC() {
		assertEquals(5, createHand1().getLTC());
		assertEquals(8, createHand2().getLTC());
		assertEquals(8, createHand3().getLTC());
		assertEquals(6, createHand4().getLTC());
	}

	@Test
	void testOpen() {
		assertEquals("1S", createHand1().getOpen());
		assertEquals("1N", createHand2().getOpen());
		assertEquals("1H", createHand3().getOpen());
		assertEquals("1H", createHand4().getOpen());
	}

	@Test
	void openingOneOfSuit() {
		Histogram hist1N = new Histogram("1N opener LTC", 10, 3.0, 1.0);
		Histogram hist1X = new Histogram("1 of suit opener LTC", 10, 0, 1.0);
		for (int i = 1; i < 10000; i++) {
			Stock stock = new Stock();
			Hand hand = stock.dealHand();
			String bid = hand.getOpen();
			if ("1N".equals(bid)) {
				hist1N.add(hand.getLTC());
			} else if (bid.charAt(0) == '1') {
				hist1X.add(hand.getLTC());
			}
		}
		logger.info(hist1N);
		logger.info("Mean: " + Double.toString(hist1N.mean() - 0.5));
		assertEquals(7.5, hist1N.mean() - 0.5, 0.5);
		logger.info(hist1X);
		logger.info("Mean: " + Double.toString(hist1X.mean() - 0.5));
		assertEquals(5.7, hist1X.mean() - 0.5, 0.5);
	}

	@Test
	void landy() {
		Histogram landy = new Histogram("Landy ", 10, 3.0, 1.0);
		for (int i = 1; i < 10000; i++) {
			Stock stock = new Stock();
			Hand hand = stock.dealHand();
			String bid = hand.getOpen();
			if ("1N".equals(bid)) {
				Hand defence = stock.dealHand();
				bid = defence.getResponse("1N");
				if ("2C".equals(bid)) {
					landy.add(defence.getLTC());
				}
			}
		}
		logger.info(landy);
		logger.info("Mean: " + Double.toString(landy.mean() - 0.5));
		assertEquals(7.5, landy.mean() - 0.5, 0.000005);
	}
}
