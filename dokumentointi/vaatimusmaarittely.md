# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on sudoku-peli. Sudoku on logiikkapeli, jossa pelaaja pyrkii asettelemaan neliön muotoiseen ruudukkoon numerot 1-9 siten, että jokaisessa rivissä, sarakkeessa ja osaruudukossa esiintyy tietty numero vain kerran.

## Käyttäjät

Sovelluksella on vain yhden tyyppinen käyttäjä: normaali käyttäjä, ts. pelaaja.

## Toiminnallisuudet

- pelaajalle generoidaan peliruudukko, johon numeroita voi täyttää. Osa numeroista on asetettu valmiiksi. Ruudukko on 3x3 osaruudukoista koostuva.
- perusversiossa sovellus ei anna tehdä vääriä siirtoja
- pelaaja pelaa kelloa vastaan
- Onnistuneesta ratkaisusta pelaajaa pyydetään syöttämään nimimerkki, joka talletetaan ajan kanssa tilastoihin.

## kehitysideoita

- vaikeusasteen valinta eli valmiiksi asetettujen numeroiden määrä
- eri tyyppisiä sudokuja, 2x2, 4x4 osaruudukoista
- vaikeusasteen myötä sallii virheelliset siirrot
