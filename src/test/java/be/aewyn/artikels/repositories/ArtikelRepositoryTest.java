package be.aewyn.artikels.repositories;

import be.aewyn.artikels.domain.Artikelgroep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@Sql({"/insertArtikelgroepen.sql","/insertArtikels.sql"})
class ArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ARTIKELS = "artikels";
    private final ArtikelRepository repository;

    public ArtikelRepositoryTest(ArtikelRepository repository){
        this.repository = repository;
    }

    long foodId;
    long nonfoodId;

    @BeforeEach
    void beforeEach(){
        foodId = jdbcTemplate.queryForObject("select id from artikelgroepen where naam = 'food'", Long.class);
        nonfoodId = jdbcTemplate.queryForObject("select id from artikelgroepen where naam = 'non-food'", Long.class);
    }

    @Test
    void findArtikelsByArtikelgroep(){
        assertThat(repository.findByPrijsBetween(BigDecimal.valueOf(2),BigDecimal.valueOf(2.5)))
                .hasSize(countRowsInTableWhere(ARTIKELS, "prijs between 2 and 2.5"));
    }

    @Test
    void findHighestPrice(){
        assertThat(repository.findHighestPrice())
                .isEqualTo(jdbcTemplate.queryForObject("select max(prijs) from artikels", BigDecimal.class));
    }

    @Test
    void findByArtikelgroep(){
        assertThat(repository.findByArtikelgroepNaam("food"))
                .hasSize(countRowsInTableWhere(ARTIKELS, "artikelGroepId = " + foodId));
        assertThat(repository.findByArtikelgroepNaam("non-food"))
                .hasSize(countRowsInTableWhere(ARTIKELS, "artikelGroepId = " + nonfoodId));
    }
}