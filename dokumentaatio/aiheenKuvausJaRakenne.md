# Aihemäärittely: loganalyser

**Aihe:** Lokitiedostojen analysointisovellus. Toteutetaan sovellus, jonka avulla voidaan analysoida [Presentation](http://www.neurobs.com)-sovelluksen lokitiedostoja. Presentation-ohjelmaa käytetään laajalti psykologian/neurotieteen alan tutkimuksissa. Sen tuottamat oletuslokitiedostot ovat rakenteeltaan melko sekavia ja sisältävät paljon epäolennaisia merkintöjä. Tämä vaikeuttaa datan analyysia ja vaatii lokitiedoston parsimisen selkeämpään muotoon ennen varsinaista data-analyysiä. Koska kokeet poikkeavat yleensä toisistaan, lokitiedostot pitää aina jäsentää hieman eri tavalla.

Tämän sovelluksen on tarkoitus helpottaa lokitiedostojen käsittelyä ja tarjota mahdollisuus monien erilaisten Presentation-lokitiedostojen analysoimiseen. Sovelluksesta voi tulostaa ulos lokitiedoston helpommin hallittavassa muodossa, mutta siinä voi myös tehdä yksinkertaista datan esikatselua, esim. reaktioaikojen jakaumaa ja virheiden määrää.

Sovellukseen ei tämän kurssin aikana ehtinyt valmistua mahdollisuus kaikkien kokeen koehenkilöiden datan analysoimiseen kerralla, mutta tämä toteuteteaan kurssin jälkeen. Sovellus jäi melko pieneksi, koska sovelluksen suunnittelu, sekä käyttöliittymän ohjelmoiminen veivät odotettua enemmän aikaa ja vaivaa. Esimerkiksi koetietojen tallennusmuoto vaihtui itse kehitellystä tekstimuotoisesta määrittelystä json-muotoon, joka on huomattavasti selkeämpi ja helpompi käyttää. Myös käyttöliittymän rakenne muuttui pariin kertaan ja koki huomattavaa refaktorointia. Eniten aikaa meni todennäköisesti kuitenkin siihen, että koeparametreista saatiin tarpeeksi joustavia, jotta sovellus voi tukea monentyylisiä kokeita (kokeita joissa on eri määrä vastauksia). Tähän meni paljon aikaa, koska muutokset vaikuttivat aina melkeinpä kaikkeen sovelluksen toimintaan. Tätä joustavuutta pitää kuitenkin vielä edelleen kehittää, jotta saadaan tuki esim. kokeille, joissa on alussa harjoitus-trialeita, joita ei haluta mukaan lopulliseen analyysiin/lokiin. Tällaisenaan sovellus kuitenkin tukee yksinkertaisten kokeiden purkamista ja karkeaa analysointia.

**Käyttäjät:** Presentation-ohjelmaa käyttävät tutkijat.

**Sovelluksen toiminnot:**
* kokeen rakenteen määrittäminen
* lokitiedoston lataaminen
  * onnistuu, jos lokitiedosto vastaa Presentationin peruslokitiedoston muotoa
* selkeämmän lokitiedoston tulostaminen
* reaktioaikojen visualisoiminen histogrammina
* vastausosuuksien visualisoiminen pylväsdiagrammeilla
* kokeen trialien visualisoiminen aikasarjana, sekä yhteisesti että erikseen jokaiselle kokeen tilanteelle

### Sekvenssikaavioita

![Lokitiedoston lataaminen](/dokumentaatio/Lokitiedoston lisääminen.png)
![Koetietojen lataaminen](/dokumentaatio/Koetietojen lataaminen.png)


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
[MainView]1-1[LogWriterHandler]
[LogSelectionHandler]1-1[FileSelectorUtil]
[ParseHandler]1-1[LogParser]
[ParseHandler]1-1[SubjectData]
[LogWriterHandler]1-1[LogWriter]
[LogWriterHandler]1-1[FileSelectorUtil]
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
[ExperimentInfo]1-1[ExperimentWindow]
[ExperimentWindow]1-1[ExperimentSettingsHandler]
[ExperimentWindow]1-1[ExperimentConditionHandler]
[ExperimentWindow]1-1[ExperimentNameHandler]
[ExperimentWindow]1-1[ExperimentRespCodeHandler]
[ExperimentWindow]1-1[ExperimentRespMappingHandler]
[ExperimentWindow]1-1[ExperimentRespNameHandler]
[ExperimentWindow]1-1[ExperimentSaveHandler]
[ExperimentWindow]1-1[ExperimentSelectionHandler]
[ExperimentSaveHandler]1-1[FileSelectorUtil]
[ExperimentSelectionHandler]1-1[FileSelectorUtil]
[ExperimentRespMappingHandler]1-1[SetCorrResponsesWindow]]
```

### Rakennekuvaus
Ohjelman toiminta nojaa rakenteeltaan vahvasti MainView-luokkaan, joka on siis sovelluksen päänäkymä. Tämä luokka sisältää nappeja, listat käsiteltävistä datoista/tiedostoita, sekä sillä hetkellä käytössä olevan kokeen tiedot. Napinpainallukset käsitellään erillisissä handler-luokissa, jotka perivät ActionListener-rajapinnan. Osa handlereista vastaa lähinnä GUI-toiminnoista (tiedostojen valinta ja poisto), mutta osa kutsuu edelleen ohjelman toiminnan kannalta keskeisiä logiikkaluokkia. Logiikkaluokat toimivat ns. taustalla, ja käyttävät hyväkseen domain-paketin säilytysluokkia. Logiikkaluokat (tärkeimpänä todennäköisesti ParseTrials-luokka) palauttavat sitten handlereille takaisin esim. käsiteltyä dataa, joka syötetään edelleen MainView:n käytettäväksi, tai sitten vain prosessoidaan ja tulostetaan uuteen ikkunaan kuvana. Keskeisin GUI-luokka MainView'n lisäksi on ExperimentWindow, jonka kautta voidaan määritellä käytössä olevaa koetta. Se sisältää myös joukon omia handler-luokkiaan, jotka vastaavat eri koetietojen asettamisesta, ja päivittämisestä. Nämä handlerit implementoivat ExtraInputHandler-nimisen rajapinnan, joka puolestaan on ActionListener-rajapinnan laajennus. Tämä on jätetty kaaviosta pois selkeyden vuoksi, sekä siksi, ettei tämän rajapinnan käytöstä ole tällä hetkellä erityistä etua.

Koska GUIin liittyvien luokkien määrä kasvoi niin suureksi, on pakkausrakenetta laajennettu niin, että MainView'hyn liittyvät handlerit ovat omassa alipakkauksessaan ja ExperimentView'n handlerit omassaan.

Jatkossa tarkoitus on refaktoroida logiikkaluokkia vielä pienempiin osiin. Myös analyysit on tarkoitus niiden kehittyessä jakaa erillisiin luokkiin ja tehdä niille samantyylinen oma käyttöliittymäikkuna kuin Experiment selector. Nämä eivät kuitenkaan ehtineet valmistua tämän kurssin aikana.
