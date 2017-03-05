# loganalyser

Application for analysing default [Presentation](http://www.neurobs.com) logfiles into more manageable form.

## Dokumentaatio
[aiheen kuvaus](dokumentaatio/aiheenKuvausJaRakenne.md)

[käyttöohjeet](dokumentaatio/käyttöohjeet.md)

[tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[pit-raportti](https://htmlpreview.github.io/?https://github.com/Mahtis/loganalyser/blob/master/dokumentaatio/pit/report/index.html)

[checkstyle-raportti](https://htmlpreview.github.io/?https://github.com/Mahtis/loganalyser/blob/master/dokumentaatio/checkstyle.html)

### Ohjelman käytöstä
Ohjelman kokeilua varten src/main/resources/testdata -kansiosta löytyy kh01_consonant.log-tiedosto, jota voi käyttää testidatana. Samasta kansiosta löytyy myös valmis experiment-määrittelytiedo. Experiment2.json määrittelee testidatalle oikean koerakenteen. Sovelluksessa on graafinen käyttöliittymä, jossa on mahdollista ladata lokitiedostoja, sekä asettaa koetietoja, jonka jälkeen lokitiedostoja on mahdollista parsia. Parsitut lokitiedostot ilmestyvät käyttöliittymässä samaan näkymään kuin ladatut lokitiedostot. Sovelluksessa on myös alustavaa lokitiedostojen analysointia, joka tulostaa datasta kuvia. Parsitun lokitiedoston voi myös tallentaa tietokoneelle haluamaansa kansioon. Tarkempaa infoa löytyy [käyttöohjeista](dokumentaatio/käyttöohjeet.md).
