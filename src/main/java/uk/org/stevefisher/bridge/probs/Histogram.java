package uk.org.stevefisher.bridge.probs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Histogram {

	private static final Logger logger = LogManager.getLogger(Histogram.class);

	private static final int MAXSTARS = 20;

	private long[] hist;

	private double minValue;
	private int nbins;
	private long entries;
	private long overflow;
	private long underflow;
	private double binWidth;
	private String name;

	public Histogram(String name, int nbins, double minValue, double binWidth) {
		this.name = name;
		this.nbins = nbins;
		this.minValue = minValue;
		this.binWidth = binWidth;
		this.hist = new long[nbins];
		this.underflow = 0;
		this.overflow = 0;
		logger.info("Created histogram with {} bins from {} of width {}", nbins, minValue, binWidth);
	}

	public void add(double x) {
		if (x < minValue) {
			underflow++;
		} else {
			int index = (int) ((x - minValue) / binWidth);
			if (index >= nbins) {
				overflow++;
			} else {
				hist[index]++;
			}
		}
		entries++;
	}

	public double mean() {
		double sum = 0;
		for (int i = 0; i < nbins; i++) {
			double binCentreValue = minValue + (i + 0.5) * binWidth;
			sum += hist[i] * binCentreValue;
		}
		return sum / (entries - overflow - underflow);
	}

	@Override
	public String toString() {
		long max = 0;
		for (int i = 0; i < nbins; i++) {
			max = Math.max(max, hist[i]);
		}

		StringBuilder sb = new StringBuilder("Histogram '" + name + "'" + " has " + entries + " entries with "
				+ underflow + " underflow and " + overflow + " overflow\n");
		for (int i = 0; i < nbins; i++) {
			sb.append(String.format("%10.4f", minValue + i * binWidth));
			sb.append(String.format("%10d", hist[i]));
			sb.append(" ");
			long numStars;
			if (max > MAXSTARS) {
				numStars = MAXSTARS * hist[i] / max;
			} else {
				numStars = hist[i];
			}
			for (long s = 0; s < numStars; s++) {
				sb.append("*");
			}
			if (i != nbins - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public void add(int i) {
		add((double) i);
	}

}