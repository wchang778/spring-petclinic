package org.springframework.samples.petclinic;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SomeTests {

	@Test
	public void testMe() {
		assertThat(1).isEqualTo(1);
		assertThat(2).isEqualTo(2);
		assertThat(3).isEqualTo(3);
	}

}
