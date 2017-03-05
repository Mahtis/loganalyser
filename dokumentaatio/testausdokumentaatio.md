# Testausdokumentatio

## Käsin testatut osiot
Käyttöliittymää ja muita graafisia osia ei ole testattu automaattisesti.
Käyttöliittymää on testattu käsin kokeilemalla, miten sovellus reagoi odottamattomiin napinpainalluksiin ja syötteisiin. Esim. hajoaako sovellus, jos kokeeseen antaa oikeiksi vastauskoodeiksi jotain muita kuin kokonaislukuja, tai muita arvoja kuin mitä on määritetty kokeen koodeiksi (ei hajoa). Lisäksi on kokeiltu, mitä tapahtuu, jos yrittää ladata jonkun muun kuin log-tiedoston, ja tällä hetkellä sovelluksen pitäisi tehokkaasti hylätä vääränlaisten lokitiedostojen lataaminen. Samaa on kokeiltu myös tallentamiselle, eikä sovelluksen pitäisi nyt sallia ei-parsittujen lokitiedostojen valintaa tallennettaviksi tiedostoiksi.

Graafien piirtoa on testattu kattavasti niiden kehittelyn yhteydessä, lähinnä käyttämällä yksinkertaisia syötteitä, joiden pitäisi tuottaa tietynlaisia kuvia, esim. kaksi palkkia, joista toinen on tasan puolet toisen korkeudesta, kun testataan oikeiden vastausten osuuksien piirtämistä.

Tällä hetkellä sovelluksen ainoat tunnetut bugit liittyvät juuri graafien piirtoon, ja liittyvät siihen, että jollain trial-määrillä kuvat eivät täytä ikkunaa tasaisesti, vaan jättävät oikeaan laitaan tyhjää tilaa. Kuvista ei varsinaisesti puutu mitään, mutta ne ovat hieman epäesteettisempiä (kuin mitä ovat jo muutenkin).
