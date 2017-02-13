package tests;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Description: When a 5 meter free stretch of parking place is detected, 
 * the car moves to the beginning of the stretch and does a pre-programmed
 * reverse parallel parking maneuver. If no parking space is detected, the
 * car moves to the end of the street until a 5 meter of free parking space is found.
 * Pre-condition: There must be 5 meter of free parking space detected.
 * Post-condition: The car does reverse parallel parking maneuver.
 * Test-cases: 
 *       TC1. The car has detected free parking space.
 *       TC2. The car is already parked.
 *       TC3. Car doesn’t find free parking space.
 */

public class park {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
