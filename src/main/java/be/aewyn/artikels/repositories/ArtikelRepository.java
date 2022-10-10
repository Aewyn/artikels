package be.aewyn.artikels.repositories;

import be.aewyn.artikels.domain.Artikel;
import be.aewyn.artikels.domain.Artikelgroep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
public interface ArtikelRepository extends JpaRepository<Artikel,Long> {
    List<Artikel> findByPrijsBetween(BigDecimal van, BigDecimal tot);

    @Query("select max(a.prijs) from Artikel a")
    BigDecimal findHighestPrice();

    List<Artikel> findByArtikelgroepNaam (String naam);
}
