package dev.haguel.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringWithNumberComparatorTest {
    @Test
    public void shouldMakeCorrectComparing() {
        StringWithNumberComparator comparator = new StringWithNumberComparator();

        assertEquals(1, comparator.compare("10", "9"));
        assertEquals(1, comparator.compare("10", "1"));
        assertEquals(-1, comparator.compare("5", "40"));
        assertEquals(-1, comparator.compare("sometext4", "sometext50"));
        assertEquals(0, comparator.compare("sometext55", "sometext55"));
    }
}