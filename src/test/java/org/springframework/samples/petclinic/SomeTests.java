package org.springframework.samples.petclinic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SomeTests {

	@Test
	public void testMe() {
		assertThat(3).isEqualTo(3);
	}

	@Test
	public void testThis() {
		assertThat(3).isEqualTo(3);
	}

	@Test
	public void testThat() {
		assertThat(3).isEqualTo(4);
	}

}
