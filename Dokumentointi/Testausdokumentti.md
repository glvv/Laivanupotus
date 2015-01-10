#Testausdokumentti

Ohjelma on testattu yksikkötesteillä mahdollisimman kattavasti eri tilanteissa ja ääriarvoilla. Satunnaisuutta sisältävät luokat on testattu käyttämällä apuna TestiArpoja-luokkaa.

Ohjelmat ovat testattu käyttäen public-metodeja. Private-metodit on saatu testattua luomalla erilaisia testitilanteita.

Graafista käyttöliittymää on testattu käsin:

* Näkyy oikea voittaja
* Laivoja on sama määrä kuin mitä valikossa on valittu
* Pelilaudan leveys on sama kuin mitä valikossa on valittu
* Pelimoodin valitseminen toimii
* Siirtoja ei voi antaa, kun peli on päättynyt
* Yksinpelissä pelaajan laivat ovat näkyvillä, ja toista pelilautaa ei voi painaa.
* Kaksinpelissä toinen pelilauta on aina reagoimaton, kun ei ole sen pelaajan vuoro.
* Osumat näkyvät oikein
* Hudit näkyvät oikein
* Uponneet laivat näkyvät oikein.


