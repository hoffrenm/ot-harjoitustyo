# Käyttöohje

## Sovelluksen asentaminen

Lataa uusimman [julkaisun](https://github.com/hoffrenm/ot-harjoitustyo/releases) sudoku.jar tiedosto

Suorita seuraava komento komentorivillä ladatun jar-tiedoston hakemistossa.
```
java -jar sudoku.jar
```

## Sovelluksen käyttö

### Aloitusruutu
Sovellus käynnistyy seuraavanlaiseen ikkunaan.

![Aloitusruutu](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/start1.PNG)

### Pelin aloittaminen
Klikatessa painiketta "New game" aukeaa mahdollisuus valita pelin vaikeusaste. 

![Pelin aloitus](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/start2.PNG)

### Pelitilanne
Riippuen valitusta vaikeusasteesta, on osa ruudukon numeroista asetettu oikeisiin kohtiin ja pelaajan tehtäväksi jää asetella puuttuvat numerot 
sudokun sääntöjen mukaisesti paikalleen. Kello lähtee käyntiin heti vaikeusasteen valinnan myötä.

![Pelitilanne](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/game1.PNG)

### Pelin eteneminen
Peli ei anna pelaajan tehdä sääntöjenvastaisia liikkeitä. On kuitenkin mahdollista, että pelaaja ajautuu umpikujaan, jolloin 
syötettyjä lukuja voi pyyhkiä pois.

![Pelitilanne2](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/game2.PNG)

### Pelin päättyminen
Oikein täytetty ruudukko päättää automaattisesti pelin ja pelaajaa pyydetään syöttämään nimimerkki. Nimimerkki, aika ja pelin vaikeusaste tallettuvat 
tiedostoon automaattisesti.

![Pelin päättyminen](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/end1.PNG)

### Pistelista
Pelaaja voi tarkastella aikaisemmin ratkaistujen pelien tuloksia pistelistalta. Pistelista tulee näkyviin "Scores" 
painikkeen avulla. Pistelistalla on maksimissaan 20 tulosta parhaimmasta ajasta huonoimpaan.

![Pistelista](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/end2.PNG)

### Lopettaminen 
Sovellus sulkeutuu "Exit" painiketta painamalla.

