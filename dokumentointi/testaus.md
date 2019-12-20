# Sovelluksen testaus

Sovelluksen luokkia, logiikkaa ja tiedostonkäsittely on testattu Junit-kirjaston tarjoamilla työkaluilla. Testit koostuvat 
sovelluslogiikan komponenttien yksikkötesteistä, sovelluslogiikan ja komponenttien yhteisistä integraatiotesteistä sekä tiedostonkäsittelyyn 
liittyvistä testeistä.

## Testauskattavuus

Testien rivikattavuus on 93% ja haaraumakattavuus 84%.

![Testikattavuus](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/testCoverageFinal.png)

## Sovelluslogiikka

### Yksikkötestit
Sovelluksen komponenteista eli 
[sudoku.domain](https://github.com/hoffrenm/ot-harjoitustyo/tree/master/Sudoku/src/main/java/sudoku/domain) 
pakkauksen luokista, joilla ei ole merkittävästi logiikkaa, on ollut suoraviivaisinta testata yksikkötestein.

Sovelluksen pääkomponenteista 
[Board](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Board.java)
ja 
[Cell](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Cell.java) 
on testattu kattavasti yksikkötesteillä 
[BoardTest](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/test/java/domain/BoardTest.java) 
ja 
[CellTest](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/test/java/domain/CellTest.java).

Pelin lopussa talletettavaa tulosta kuvaavaa 
[Scorea](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Score.java) 
on testattu yksikkötestein 
[ScoreTest](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/test/java/domain/ScoreTest.java) 
siten, että tallennus tapahtuu oikeassa muodossa ja pisteitä vertaileva compareTo-metodi toimii oikein.

Tiedostoon kirjoittavaa luokkaa 
[FileScore](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/dao/FileScore.java) 
on testattu Junitin tarjoamalla väliaikaisella TemporaryFolder-säännön avulla siten, että tiedoston lukeminen ja kirjoittaminen 
onnistuu.

### Integraatiotestit
Edellytys ruudukon oikeanlaiselle toiminnalle on 
[BoardHelper](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/logics/BoardHelper.java) 
joka tarjoaa apumetodeja lukujen sijoittamiseen ruudukkoon. Ominaisuuksia on testattu integraatiotesteillä 
[BoardHelperTest](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/test/java/logics/BoardHelperTest.java), 
jotka testaavat 
[Boardin](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Board.java), 
[Cellin](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Cell.java) ja 
[BoardHelperin](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/logics/BoardHelper.java) 
yhteistoimintaa.

Tulosten tallettamista on testattu 
[FileScoren](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/dao/FileScore.java) ja 
[ScoreServicen](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/logics/ScoreService.java) ja 
[Scoren](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/main/java/sudoku/domain/Score.java) 
yhdistävillä integraatiotesteillä 
[FileScoreTest](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/test/java/dao/FileScoreTest.java) ja 
[ScoreServiceTest](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/Sudoku/src/test/java/logics/ScoreServiceTest.java). 
Testit varmistavat, että tulosten kirjoittaminen ja lukeminen onnistuvat ja ne ovat oikeassa formaatissa.

## Manuaalinen testaus

Sovelluksen järjestelmätestausta on suoritettu käsin Windows ja Linux-ympäristössä. Sovellus olettaa, että käyttäjällä on kirjoitus- ja lukuoikeus sovelluksen suoritussijainnissa.

