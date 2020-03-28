package es.pmg.viseras.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import es.pmg.viseras.web.rest.TestUtil;

public class DonanteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Donante.class);
        Donante donante1 = new Donante();
        donante1.setId(1L);
        Donante donante2 = new Donante();
        donante2.setId(donante1.getId());
        assertThat(donante1).isEqualTo(donante2);
        donante2.setId(2L);
        assertThat(donante1).isNotEqualTo(donante2);
        donante1.setId(null);
        assertThat(donante1).isNotEqualTo(donante2);
    }
}
