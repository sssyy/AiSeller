package pers.utils;

import java.util.UUID;

public class LongUUIDGenerator {
    public static long generateLongUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits() ^ uuid.getLeastSignificantBits();
    }

    public static void main(String[] args) {
        System.out.println("Generated long UUID: " + generateLongUUID());
    }
}
