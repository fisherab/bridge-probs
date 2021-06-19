package uk.org.stevefisher.bridge.probs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	void testCreateHand() throws Exception {
		String h = "SAJ32H876DJ74C652";
		assertEquals(h,(new Hand(h).getDisplay()));
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
	void testResponse() throws Exception {
		assertEquals("1D", new Hand("SA534H9D65432CAKQ").getResponse("1C"));
		assertEquals("1S", new Hand("SA534H9D65432CAK2").getResponse("1C"));
		assertEquals("1S", new Hand("SA534H93D6543CAK2").getResponse("1C"));
		assertEquals("1N", new Hand("SA53H932D6543CAQ2").getResponse("1C"));
		assertEquals("2N", new Hand("SA53H932D6543CAK2").getResponse("1C"));
		assertEquals("2C", new Hand("SA53H932D654CAK32").getResponse("1C"));
		assertEquals("2N", new Hand("SA53HK32D654CA932").getResponse("1C"));
		assertEquals("3C", new Hand("S953HK32D2CA96543").getResponse("1C"));
		assertEquals("2D", new Hand("S953HK32D965432CA").getResponse("1C"));
		assertEquals("3D", new Hand("S953HK32DA965432C").getResponse("1C"));
		assertEquals("2S", new Hand("ST96543H2K3D932CQ").getResponse("1C"));
		assertEquals("3S", new Hand("ST965432HK3D932CQ").getResponse("1C"));
		assertEquals("PASS", new Hand("ST965432HK3D932CJ").getResponse("1C"));
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
	void testSQ() {
		assertEquals(7, createHand1().getSQ(Suit.SPADES));
		assertEquals(6, createHand1().getSQ(Suit.HEARTS));
		assertEquals(5, createHand1().getSQ(Suit.DIAMONDS));
		assertEquals(0, createHand1().getSQ(Suit.CLUBS));
		assertEquals(7, createHand2().getSQ(Suit.SPADES));
		assertEquals(6, createHand2().getSQ(Suit.HEARTS));
		assertEquals(3, createHand2().getSQ(Suit.DIAMONDS));
		assertEquals(3, createHand2().getSQ(Suit.CLUBS));
		assertEquals(7, createHand3().getSQ(Suit.SPADES));
		assertEquals(6, createHand3().getSQ(Suit.HEARTS));
		assertEquals(2, createHand3().getSQ(Suit.DIAMONDS));
		assertEquals(3, createHand3().getSQ(Suit.CLUBS));
		assertEquals(7, createHand4().getSQ(Suit.SPADES));
		assertEquals(6, createHand4().getSQ(Suit.HEARTS));
		assertEquals(5, createHand4().getSQ(Suit.DIAMONDS));
		assertEquals(1, createHand4().getSQ(Suit.CLUBS));
	}

	@Test
	void testDisplay() {
		assertEquals("SAKT82HQJ32DQ542C", createHand1().getDisplay());
		assertEquals("SAKJ2HQJ32D542CQ8", createHand2().getDisplay());
		assertEquals("SAKJ2HJT432D52CQ8", createHand3().getDisplay());
		assertEquals("SAKJ2HQJ32DQ542C8", createHand4().getDisplay());
	}

	@Test
	void testHas5CM() {
		assertTrue(createHand1().has5CM());
		assertFalse(createHand2().has5CM());
		assertTrue(createHand3().has5CM());
		assertFalse(createHand4().has5CM());
	}

	@Test
	void testHasIntervention() {
		assertEquals("1S", createHand1().intervention("1C"));
		assertEquals("1S", createHand2().intervention("1C"));
		assertEquals("1S", createHand3().intervention("1C"));
		assertEquals("1S", createHand4().intervention("1C"));
		assertEquals("1S", createHand1().intervention("1D"));
		assertEquals("1S", createHand2().intervention("1D"));
		assertEquals("1S", createHand3().intervention("1D"));
		assertEquals("1S", createHand4().intervention("1D"));
		assertEquals("1S", createHand1().intervention("1H"));
		assertEquals("1S", createHand2().intervention("1H"));
		assertEquals("1S", createHand3().intervention("1H"));
		assertEquals("1S", createHand4().intervention("1H"));
		assertEquals("PASS", createHand1().intervention("1S"));
		assertEquals("PASS", createHand2().intervention("1S"));
		assertEquals("PASS", createHand3().intervention("1S"));
		assertEquals("PASS", createHand4().intervention("1S"));
		for (int i = 0; i < 10; i++) {
			Stock stock = new Stock();
			Hand hand = stock.dealHand();
			System.out.println(hand.getDisplay() + " " + hand.intervention("1C") + " " + hand.intervention("1D") + " "
					+ hand.intervention("1H") + " " + hand.intervention("1S"));
		}
	}

	@Test
	void testOpen5CM() {
		assertEquals("1S", createHand1().getOpen5CM());
		assertEquals("1C", createHand2().getOpen5CM());
		assertEquals("1H", createHand3().getOpen5CM());
		assertEquals("1D", createHand4().getOpen5CM());
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
	void openingOneOfSuit5CM() {
		Histogram hist1N = new Histogram("1N opener LTC 5CM", 10, 3.0, 1.0);
		Histogram hist1C = new Histogram("1C opener LTC 5CM", 10, 0, 1.0);
		Histogram hist1D = new Histogram("1D opener LTC 5CM", 10, 0, 1.0);
		Histogram hist1H = new Histogram("1H opener LTC 5CM", 10, 0, 1.0);
		Histogram hist1S = new Histogram("1S opener LTC 5CM", 10, 0, 1.0);
		for (int i = 1; i < 100000; i++) {
			Stock stock = new Stock();
			Hand hand = stock.dealHand();
			String bid = hand.getOpen5CM();
			if ("1N".equals(bid)) {
				hist1N.add(hand.getLTC());
			} else if ("1C".equals(bid)) {
				hist1C.add(hand.getLTC());
			} else if ("1D".equals(bid)) {
				hist1D.add(hand.getLTC());
			}else if ("1H".equals(bid)) {
				hist1H.add(hand.getLTC());
			}else if ("1S".equals(bid)) {
				hist1S.add(hand.getLTC());
			}
		}
		logger.info(hist1N);
		logger.info("Mean: " + Double.toString(hist1N.mean() - 0.5));
//		assertEquals(7.5, hist1N.mean() - 0.5, 0.5);
		logger.info(hist1C);
		logger.info("Mean: " + Double.toString(hist1C.mean() - 0.5));
//		assertEquals(5.7, hist1C.mean() - 0.5, 0.5);
		logger.info(hist1D);
		logger.info("Mean: " + Double.toString(hist1D.mean() - 0.5));
//		assertEquals(5.7, hist1D.mean() - 0.5, 0.5);
		logger.info(hist1H);
		logger.info("Mean: " + Double.toString(hist1H.mean() - 0.5));
//		assertEquals(5.7, hist1H.mean() - 0.5, 0.5);
		logger.info(hist1S);
		logger.info("Mean: " + Double.toString(hist1S.mean() - 0.5));
//		assertEquals(5.7, hist1S.mean() - 0.5, 0.5);
	}

	@Disabled
	@Test
	void landy() {
		Histogram landy = new Histogram("Landy ", 10, 3.0, 1.0);
		for (int i = 1; i < 10000; i++) {
			Stock stock = new Stock();
			Hand hand = stock.dealHand();
			String bid = hand.getOpen();
			if ("1N".equals(bid)) {
				Hand defence = stock.dealHand();
//				bid = defence.getOvercall("1N");
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
