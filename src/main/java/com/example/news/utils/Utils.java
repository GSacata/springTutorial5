package com.example.news.utils;

import java.time.Instant;

public class Utils {
    public static Instant returnFormattedNow() {
        // Clock clock = Clock.system(ZoneId.of("America/Sao_Paulo"));
        // Instant now = Instant.now(clock);
        Instant now = Instant.now();
        return now;
    }   
}
