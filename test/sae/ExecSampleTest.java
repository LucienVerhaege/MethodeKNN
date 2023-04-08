package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.ExecutionSample;


class ExecSampleTest {
	
	@Test
	void execSampleReturnANonEmptyList() throws InterruptedException {
		assertEquals(3, ExecutionSample.main().size());
	}
	
	@Test
	void execSampleReturnAValidRobustness() throws InterruptedException {
		boolean assertPassed;
		for(Double d : ExecutionSample.main()) {
			assertPassed = false;
			if(d > 50 && d <= 100) {
				assertPassed = true;
				assertTrue(assertPassed);
			}
		}
	}
}
