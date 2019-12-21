# Ohjelmistotekniikka, harjoitustyö

Tämä sudokusovellus on harjoitustyö HY:n ohjelmistotekniikan kurssille. Sovelluksen ohjelmointikielenä toimii Java ja käyttöliittymä toteutetaan JavaFX:n avulla.

## Sudoku

Sudoku on logiikkapeli, jossa pelaaja pyrkii asettelemaan numerot 1-9 9x9 ruudukkoon siten, että jokaisessa rivissä, sarakkeessa ja 3x3 aliruudukossa esiintyy tietty numero vain kerran. Jotkin numerot ovat asetettu valmiiksi ja peli päättyy kun ruudukko on täytetty ehtojen mukaisesti.

## Uusin release

[Loppupalautus](https://github.com/hoffrenm/ot-harjoitustyo/releases/tag/final)

## Dokumentaatio

[Käyttöohje](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Testausdokumentti](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/testaus.md)

[Arkkitehtuuri](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Komentorivikäskyt
Käskyt tulee suorittaa projektin juurihakemistossa.

#### Projektin suorittaminen
```
mvn compile exec:java -Dexec.mainClass=sudoku.Main
```
#### Suoritettavan jar-tiedoston luonti
```
mvn package
```
Komennon luoma tiedosto sijaitsee *target/Sudoku-1.0-SNAPSHOT.jar*

Tiedoston suorittaminen onnistuu komennolla
```
java -jar Sudoku-1.0-SNAPSHOT.jar
```
#### Testien suorittaminen
```
mvn test
```
#### Testien Jacoco-raportti
```
mvn test jacoco:report
```
Testikattavuusraportti generoituu tiedostoon *target/site/jacoco/index.html*
#### Checkstyle-tarkastus
```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyletarkastusraportti generoituu tiedostoon *target/site/checkstyle.html*

#### Javadocin generointi
```
mvn javadoc:javadoc
```
Javadocia pääsee tarkastelemaan tiedostosta *target/site/apidocs/index.html*

