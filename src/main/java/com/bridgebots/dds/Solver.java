package com.bridgebots.dds;

public interface Solver {
    int solve(Deal deal, TrumpSuit trumpSuit, Direction declarer);
}
