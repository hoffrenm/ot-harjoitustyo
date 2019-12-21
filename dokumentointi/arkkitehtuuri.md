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

Käyttöliittymä käyttää sekä 
[sudoku.domainin](https://github.com/hoffrenm/ot-harjoitustyo/tree/master/Sudoku/src/main/java/sudoku/domain) sekä 
[sudoku.logics](https://github.com/hoffrenm/ot-harjoitustyo/tree/master/Sudoku/src/main/java/sudoku/logics) pakkauksien luokkia. Käyttöliittymän ruudukon tila on sidoksissa sovelluslogiikan [Boardin](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Board.java) tilaan, jonka pohjalta se renderöidään. Lisäksi käyttöliittymä käyttää [ScoreServicea](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/logics/ScoreService.java) pelin lopputuloksen tallettamiseen.

### Luokkakaavio

![Luokkakaavio](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/packageClassDiagram.png)

### Sekvenssikaaviot päätoiminnallisuuksista

## Tietojen tallennus

Sovellus tallettaa pistetilastot tekstitiedostoon. Tiedosto sijaitsee oletusarvoisesti projektin suoritushakemistossa ja mikäli tiedostoa ei ole olemassa, luo sovellus sen automaattisesti nimellä *scores.txt*

Pelin lopputulokset tallentuvat comma separated values-formaatissa seuraavasti
```
name1;0:21;easy
name2;12:34;hard
name3;59:59;medium
...
```
