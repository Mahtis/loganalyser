## Käyttöohjeet

Ohjelman käynnistyttyä ensimmäiseksi tulee valita käsiteltävä lokitiedosto(t). Tämä onnistuu painamalla ikkunan ylälaidassa olevaa Files-nappia. Sovelluksen "src/main/resources/testdata"-kansiosta löytyy esim. kh01_consonant.log-tiedosto (sekä muuta testidataa, lisää alla), jota voi käyttää ohjelman kokeiluun. Kun lokitiedosto on valittu, sen pitäisi ilmestyä sovelluksen listakenttään näkyviin.

Tämän jälkeen asetetaan kokeen parametrit, jonka mukaan lokitiedosto käsitellään. Tämä tapahtuu painamalla Set Experiment -nappia. Tämä aukaisee uuden Experiment selector -ikkunan. Ikkunassa on useita nappeja, mutta yksinkertaisimmin kokeen tiedot voi ladata valmiista tiedostosta "Load Experiment" -napista. Samasta testdata-kansiosta kuin lokitiedosto, löytyy myös Experiment2.json-tiedosto, jota voi käyttää ohjelman kokeiluun.

Experiment selector ikkunasta löytyy myös napit kaikkien kokeen ominaisuuksien määrittämiseen, experiment name, codes, names ja correct. Näiden kautta voi asettaa kokeen nimen, kokeeseen kuuluvat vastauskoodit (kokonaislukuja), vastauskoodien sanalliset kuvaukset (esim. mitä näppäintä koodi vastaa), sekä oikeat vastaukset. Oikeat vastaukset (eli correct-napin takaa aukeava ikkuna) määritellään erikseen jokaiselle kokeeseen määritetylle konditiolle. Kuhunkin konditioon voi liittyä rajoittamaton määrä erillisiä vastauksia. Yhden vastauskerran oikeat vastaukset erotellaan toisistaan pilkulla, ja vastauskerrat toisistaan puolipisteellä. Eli kokeessa voi olla tilanne, jossa ensin pitää vastata painamalla vasenta nuolinäppäintä, jonka jälkeen pitää vielä vastata painamalla oikeaa tai vasenta nuolinäppäintä. Eli ensimmäiseen vastauskertaan on yksi oikea vastaus ja toiseen kaksi (syöte voisi siis olla esim. "1;1,2"). Vastauksia pitää merkitä niihin liittyvillä koodeilla, ei nimillä.

Lopuksi Experiment selector -ikkunasta löytyy myös Save Experiment -nappi, jolla määritellyn kokeen voi tallentaa json-tiedostoksi myöhempää käyttöä varten. Experiment selector -ikkunan vasemmassa yläkulmassa on "saved" tai "not saved" -teksti sen mukaan, onko nykymuotoinen koe tallennettuna tiedostoon.

Kun koe on valittu, Experiment selector -ikkunan voi sulkea normaalisti ikkunan yläkulman ruksista. Pääikkunassa pitäisi nyt näkyä oikeassa ylälaidassa Current experiment -tekstin alla "exp2" (tai mikä vain koe äsken ladattiin). Tämän jälkeen pääikkunassa voi valita avatun lokitiedoston ja klikata Parse logfile -nappia. Tämä käsittelee lokitiedoston ja listaan pitäisi ilmestyä uusi, "kh01 exp2 data" -niminen, objekti. Valitsemalla nyt tämän uuden tiedoston ja klikkaamalla Analyse trials -nappia, ohjelma analysoi dataa ja tuottaa erilaisia kuvia datasta. Näytöllä avautuu Histogram, Rates, Time series, sekä kokeeseen liityvien konditioiden nimisiä ikkunoita. Histogram-ikkunassa on piirretty datan reaktioaikojen jakauma histogrammin muodossa. Tästä voi karkeasti arvioida kerätyn datan normaalisuutta. Rates-ikkunassa on esitetty datan eri konditioiden oikeiden vastausten osuudet prosentteina. Time series -ikkunassa on plotattuna kaikkien kokeen trialien (tehtäväsuoritusten) reaktioajat aikajärjestyksessä. Kuvassa o-merkit tarkoittavat oikeita vastauksia ja x-vastaukset vääriä vastauksia. Lisäksi kuvassa näkyy viivoja, jotka ovat kuvaavat reaktioaikojen keskiarvoa viivan pituuden kattavilla trialeilla. Erilliset konditioikkunat ovat muuten täysin samanlaisia kuin Time series -ikkuna, mutta niissä on vain ikkunan nimen mukaisen kondition trialeita. Kuvista puuttuvat kaikki tarkemmat tiedot, mutta ne ovat tällä hetkellä tarkoitettu vain datan suuripiirteiseen arviointiin.

Lopuksi parsitut lokitiedostot voi tallentaa selkeämmässä muodossa "Save parsed log" -napista. Valitse haluamasi tallennuskansio (Huom! Nimenomaan kansio) ja klikkaa save-nappia. Voit valita kerralla useamman lokitiedoston tallennettavaksi, mutta ole tarkkana, ettet valitse muita kuin parsittuja lokitiedostoja.

Lisäksi päänäkymän listasta voi poistaa tiedostoja "Remove selected" -napilla.

### Testidata
Sovelluksen kokeilua varten "src/main/resources/testdata"-hakemistosta löytyy kh01-kh05 consonant.log-tiedostot, joita voi käyttää ohjelman kokeiluun. Tämän ylähakemistosta, "src/main/resources/" löytyy myös dataa, mutta tämän kansion tiedostoja käytetään sovelluksen testeissä, joten niiden käyttö/muokkaaminen voi hajottaa joitain sovelluksen testeistä. Tästä kansiosta löytyy myös Experiment1.json-tiedosto, jonka määrittelemä koe kääntää Experiment2.json-kokeen vastaukset päinvastaisiksi ja antaa siis esim. kh01_consonant-datalle paljon vääriä vastauksia.
Jos kokeiden määrittelyä haluaa kokeilla itse, niin testidatoista löytyy kokonaisuudessaan seuraavat konditiot:
deS, deV
keS, keV
leS, leV
meS, meV
neS, neV
peS, peV
seS, seV
reS, reV
teS, teV
Kaikki datat ovat samasta kokeesta. Vastauskoodeina kokeessa on käyetty vain lukuja 1 ja 2.
