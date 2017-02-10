# loganalyser

Application for analysing default [Presentation](http://www.neurobs.com) logfiles into more manageable form.

## Dokumentaatio
[aiheen kuvaus](dokumentaatio/aiheenKuvausJaRakenne.md)

[tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

[pit-raportti](https://htmlpreview.github.io/?https://github.com/Mahtis/loganalyser/blob/master/dokumentaatio/pit/report/index.html)

[checkstyle-raportti](https://htmlpreview.github.io/?https://github.com/Mahtis/loganalyser/blob/master/dokumentaatio/checkstyle.html)

### Ohjelman käytöstä
Ohjelman kokeilua varten src/main/resources/ -kansiosta löytyy subj1_exp1.log-tiedosto, jota voi käyttää testidatana. Samasta kansiosta löytyy myös kaksi valmista experiment-määrittelytiedostoa. Experiment2.json määrittelee testidatalle oikean koerakenteen, Experiment1.json väärän.
LogAnalyserApplication-luokan Main-metodi on määritelty niin, että se parsii testidatan oikein määritellyn kokeen perusteella ja laskee oikeiden vastausten määrän.
