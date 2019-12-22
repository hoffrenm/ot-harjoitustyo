# Arkkitehtuurikuvaus

## Sovelluksen rakenne

Sovellus on jaoteltu pakkauksiin, jotka kuvaavat luokkien ominaisuuksia

|Pakkaus|Kuvaus|Sisältää|
|:------|:-----|:-------|
|sudoku.ui|Käyttöliittymän komponentit|SudokuUi, Timer|
|sudoku.domain|Entiteettejä kuvaavat luokat, joilla ei juuri ole omaa logiikkaa|Board, Cell, Score|
|sudoku.logics|Luokat, joita käytetään tiedon ja sovelluksen tilan manipulointiin|BoardHelper, ScoreService|
|sudoku.dao|Tiedon pysyväistallennus|FileScore|

### Pakkausrakenne

![Pakkausrakenne](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/package.png)

### Luokkakaavio

![Luokkakaavio](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/packageClassDiagram.png)

Käyttöliittymä käyttää sekä 
[sudoku.domainin](https://github.com/hoffrenm/ot-harjoitustyo/tree/master/Sudoku/src/main/java/sudoku/domain) sekä 
[sudoku.logics](https://github.com/hoffrenm/ot-harjoitustyo/tree/master/Sudoku/src/main/java/sudoku/logics) pakkauksien luokkia. Käyttöliittymän ruudukon tila on sidoksissa sovelluslogiikan [Boardin](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Board.java) tilaan, jonka pohjalta se renderöidään. Lisäksi käyttöliittymä käyttää [ScoreServicea](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/logics/ScoreService.java) pelin lopputuloksen tallettamiseen sekä pistelistan hakemiseen. Käyttöliittymä itsessään ei säilö mitään ruudukkoon tai pisteisiin liittyvää tietoa vaan renderöi edellä mainittujen luokkien avulla haetun tiedon.

### Sekvenssikaaviot päätoiminnallisuuksista

#### Pelaaja syöttää sallitun luvun kenttään

![Oikeasiirto](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/sequence1.png)

#### Aikaisemmat pisteet luetaan tiedosta sovelluksen käynnistyksessä

![tiedostonluku](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/fileread.png)

### Käyttöliittymä

#### Yleisesti

Käyttöliittymä on toteutettu JavaFX:n avulla. Sovelluksen rungon muodostaa BorderPane, jonka solmuihin on asetettu halutut elementit. Top-solmu on varattu ajan näyttämiseen ja Bottom-solmu hallitsee painikkeita. Ikkunan keskellä sijaitsevaan Center-solmuun on asetettu peliruudukko ja sen tilalle voidaan vaihtaa pistetilasto painiketta painamalla. Peliruudukko itsessään on toteutettu GridPanen sisällä olevista GridPaneista, jotka kuvaavat peliruudukon soluja.

#### Huomio syötteen validoinnista

Käyttäjän antama syöte validoitaan käyttöliittymässä JavaFX TextFieldin muokatun filtterin avulla. Suodatin ei anna asettaa tekstikenttään enempää kuin yhden merkin väliltä 1-9, joka takaa sen, että sovelluslogiikan käsiteltäväksi menee halutun muotoista syötettä. Lisäksi sovelluslogiikka tarkastaa, että haluttu numero on sallittua asettaa kyseiseen ruutuun, eikä peli voi näin edetä virheelliseen tilanteeseen.

## Tietojen tallennus

Sovellus tallettaa pistetilastot tekstitiedostoon. Tiedosto sijaitsee oletusarvoisesti projektin suoritushakemistossa ja mikäli tiedostoa ei ole olemassa, luo sovellus sen automaattisesti nimellä *scores.txt*

Pelin lopputulokset tallentuvat comma separated values-formaatissa.

Erotinmerkki on puolipiste `;`

```
name1;0:21;easy
name2;12:34;hard
name3;59:59;medium
...
```

Tiedosto luetaan kertaalleen sovelluksen käynnistyksessä ja pistetilastoihin lisätyt tulokset kirjoitetaan tiedostoon kerran sovelluksen sulkeutuessa. Koska sovellus kirjoittaa tiedot sovelluksen sulkeutuessa, on mahdollista, että sovelluksen äkillinen kaatuminen hävittää kyseisen session tulokset.

## Sovelluksen rakenteeseen jääneet heikkoudet

### Käyttöliittymä
Koko käyttöliittymän koodi on pitkälti yhdessä tiedostossa. Paljon refaktoroitavaa.

### Sovelluslogiikka
Pelin dimensiot on kovakoodattu, joten eri kokoisten sudokujen tarjoaminen vaatisi luokkien metodien parasoimista. Erityisesti tämä vaatisi käyttöliittymän muuttamista siten, että käyttöliittymän elementit muodostettaisiin perittävien logiikkaluokkien pohjalta.
