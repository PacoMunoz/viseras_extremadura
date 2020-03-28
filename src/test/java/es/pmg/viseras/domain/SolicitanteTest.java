package es.pmg.viseras.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import es.pmg.viseras.web.rest.TestUtil;

public class SolicitanteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Solicitante.class);
        Solicitante solicitante1 = new Solicitante();
        solicitante1.setId(1L);
        Solicitante solicitante2 = new Solicitante();
        solicitante2.setId(solicitante1.getId());
        assertThat(solicitante1).isEqualTo(solicitante2);
        solicitante2.setId(2L);
        assertThat(solicitante1).isNotEqualTo(solicitante2);
        solicitante1.setId(null);
        assertThat(solicitante1).isNotEqualTo(solicitante2);
    }
}
