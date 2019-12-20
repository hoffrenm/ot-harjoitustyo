# Arkkitehtuurikuvaus

## Sovelluksen rakenne

Sovellus on jaoteltu pakkauksiin, jotka kuvaavat luokkien ominaisuuksia

|Pakkaus|Kuvaus|Sisältää|
|:------|:-----|:-------|
|sudoku.ui|Käyttöliittymän komponentit|SudokuUi, Timer|
|sudoku.domain|Entiteettejä kuvaavat luokat, joilla ei juuri ole omaa logiikkaa|Board, Cell, Score|
|sudoku.logics|Luokat, joita käytetään tiedon ja sovelluksen tilan manipulointiin|BoardHelper, ScoreService|
|sudoku.dao|Tiedon pysyväistallennus|FileScore|


Pakkausrakenne kuvana

![Pakkausrakenne](https://github.com/hoffrenm/ot-harjoitustyo/blob/master/dokumentointi/pictures/package.png)
