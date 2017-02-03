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

###Määrittelyvaiheen luokkakaavio

![luokkakaavio](/dokumentaatio/luokkakaavio.png)
