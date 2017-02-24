# Aihemäärittely: loganalyser

**Aihe:** Lokitiedostojen analysointisovellus. Toteutetaan sovellus, jonka avulla voidaan analysoida [Presentation](http://www.neurobs.com)-sovelluksen lokitiedostoja. Presentation-ohjelmaa käytetään laajalti psykologian/neurotieteen alan tutkimuksissa. Sen tuottamat oletuslokitiedostot ovat rakenteeltaan melko sekavia ja sisältävät paljon epäolennaisia merkintöjä. Tämä vaikeuttaa datan analyysia ja vaatii yleensä aina lokitiedoston parsimisen ennen varsinaista data-analyysiä. Koska kokeet poikkeavat yleensä toisistaan, joten lokitiedostot pitää aina jäsentää hieman eri tavalla.

Tämän sovelluksen on tarkoitus helpottaa lokitiedostojen käsittelyä ja tarjota mahdollisuus monien erilaisten Presentation-lokitiedostojen analysoimiseen. Sovelluksesta voi tulostaa ulos lokitiedoston helpommin hallittavassa muodossa, mutta siinä voi myös tehdä yksinkertaista datan esikatselua, esim. reaktioaikojen jakaumaa, virheiden määrää.

Sovellukseen tulee myös mahdollisuus kaikkien kokeen koehenkilöiden datan analysoimiseen kerralla, jolloin sovelluksesta voidaan tulostaa suoraan tilasto-ohjelmaan syötettävää keskiarvodataa.  (Tämä ominaisuus ei välttämättä valmistu tämän kurssin puitteissa, mutta sovellus suunnitellaan tätä silmällä pitäen. Tässä vaiheessa sovellukseen lisätään myös mahdollisuus muunlaisten lokitiedostojen analysoimiseen)

**Käyttäjät:** Presentation-ohjelmaa käyttävät tutkijat

**Sovelluksen toiminnot:**
* kokeen rakenteen määrittäminen
* lokitiedoston lataaminen
  * onnistuu, jos lokitiedosto vastaa Presentationin peruslokitiedoston muotoa
* selkeämmän lokitiedoston tulostaminen
* reaktioaikojen visualisoiminen histogrammina
* vastausosuuksien visualisoiminen pylväsdiagrammeilla

### Sekvenssikaavioita

![Lokitiedoston lataaminen](/dokumentaatio/Lokitiedoston lisääminen.png)
![Koetietojen lataaminen](/dokumentaatio/Koetietojen lataaminen.png)

### Käyttöliittymän päänäkymän ja experiment-määritelmän hahmotelma

![käyttöliittymä](/dokumentaatio/Käyttöliittymä_mocup.pdf)

### Luokkakaavio

![luokkakaavio](/dokumentaatio/luokkakaavio.png)

#### Luokkakaavio luotu osoitteessa [yuml.me](https://yuml.me) allaolevilla määrittelyillä:
```
[MainWindow]1-1[MainView]
[MainView]1-1[ExperimentInfo]
[MainView]1-*[SubjectData]
[MainView]1-1[AnalyseHandler]
[MainView]1-1[DeletionHandler]
[MainView]1-1[LogSelectionHandler]
[MainView]1-1[ParseHandler]
[MainView]1-1[ExperimentSettingsHandler]
[MainView]1-1[ParseHandler]
[ParseHandler]1-1[LogParser]
[ParseHandler]1-1[SubjectData]
[ExperimentSettingsHandler]1-1[ExperimentWindow]
[AnalyseHandler]1-*[AnalyseData]
[AnalyseHandler]1-*[AnalysisWindow]
[AnalyseHandler]1-*[DrawHistogram]
[AnalyseHandler]1-*[DrawResponseRates]
[AnalyseHandler]1-*[DrawTimeSeries]
[AnalyseData]1-1[SubjectData]
[LogParser]1-1[FileReader]
[LogParser]1-1[ExperimentInfo]
[LogParser]1-*[Trial]
[LogWriter]1-*[Trial]
[ExperimentInfoIO]1-1[FileReader]
[ExperimentInfoIO]1-1[ExperimentInfo]
[ExperimentInfoIO]1-*[ResponseMapping]
[SubjectData]1-1[ExperimentInfo]
[SubjectData]1-*[Trial]
[ExperimentInfo]1-*[ResponseMapping]
```

### Rakennekuvaus
