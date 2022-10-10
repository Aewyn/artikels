insert into Artikels(naam, prijs, artikelGroepId)
values ('appel', 2.5, (SELECT id from artikelgroepen where naam = 'food')),
       ('peer', 2, (SELECT id from artikelgroepen where naam = 'food')),
       ('banaan', 1, (SELECT id from artikelgroepen where naam = 'food')),
       ('wasmiddel', 3.0, (SELECT id from artikelgroepen where naam = 'non-food')),
       ('wasverzachter', 4, (SELECT id from artikelgroepen where naam = 'non-food')),
       ('parfum', 5,(SELECT id from artikelgroepen where naam = 'non-food'));