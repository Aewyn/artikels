package be.aewyn.artikels.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="artikels")
public class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal prijs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artikelGroepId", referencedColumnName = "id")
    private Artikelgroep artikelgroep;

    public Artikel(String naam, BigDecimal prijs, Artikelgroep artikelgroep) {
        this.naam = naam;
        this.prijs = prijs;
        this.artikelgroep = artikelgroep;
    }

    protected Artikel(){}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public Artikelgroep getArtikelgroep() {
        return artikelgroep;
    }
}
