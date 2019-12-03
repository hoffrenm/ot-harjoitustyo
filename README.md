# Ohjelmistotekniikka, harjoitustyö

Tämä sudokusovellus on harjoitustyö HY:n ohjelmistotekniikan kurssille. Sovelluksen ohjelmointikielenä toimii Java ja käyttöliittymä toteutetaan JavaFX:n avulla.

## Sudoku

Sudoku on logiikkapeli, jossa pelaaja pyrkii asettelemaan numerot 1-9 9x9 ruudukkoon siten, että jokaisessa rivissä, sarakkeessa ja 3x3 aliruudukossa esiintyy tietty numero vain kerran. Jotkin numerot ovat asetettu valmiiksi ja peli päättyy kun ruudukko on täytetty ehtojen mukaisesti.

## Dokumentaatio

[vaatimusmäärittely](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[tuntikirjanpito](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[arkkitehtuuri](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

## Komentorivikäskyt
Käskyt tulee suorittaa projektin juurihakemistossa.

#### Projektin suorittaminen
```
mvn compile exec:java -Dexec.mainClass=sudoku.Main
```
#### Testien suorittaminen
```
mvn test
```
#### Testien Jacoco-raportti
```
mvn test jacoco:report
```
#### Checkstyle-tarkastus
```
mvn jxr:jxr checkstyle:checkstyle
```
