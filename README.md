# loganalyser

Application for analysing default [Presentation](http://www.neurobs.com) logfiles into more manageable form.

## Dokumentaatio
[aiheen kuvaus](dokumentaatio/aiheenKuvausJaRakenne.md)

[tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[pit-raportti](https://htmlpreview.github.io/?https://github.com/Mahtis/loganalyser/blob/master/dokumentaatio/pit/report/index.html)

[checkstyle-raportti](https://htmlpreview.github.io/?https://github.com/Mahtis/loganalyser/blob/master/dokumentaatio/checkstyle.html)

### Ohjelman käytöstä
Ohjelman kokeilua varten src/main/resources/ -kansiosta löytyy subj1_exp1.log-tiedosto, jota voi käyttää testidatana. Samasta kansiosta löytyy myös kaksi valmista experiment-määrittelytiedostoa. Experiment2.json määrittelee testidatalle oikean koerakenteen, Experiment1.json väärän. Tällä hetkellä sovellukseen on toteutettu graafinen käyttöliittymä, jossa on mahdollista ladata lokitiedostoja, sekä asettaa koetietoja, jonka jälkeen lokitiedostoja on mahdollista parsia. Parsitut lokitiedostot ilmestyvät käyttöliittymässä samaan näkymään kuin ladatut lokitiedostot, mutta ohjelma parsii tällä hetkellä aina listan ensimmäisen lokitiedoston, ja parsittu data on näkyy listassa hascode-nimenä. Sovelluksessa on myös alustavaa lokitiedostojen analysointia, mutta se ei vielä tee käytännössä mitään.
