#Laivanupotus-pelin rakennekuvaus

Logiikka käynnistetään käyttöliittymästä. Logiikan konstruktori luo pelin komponentit, kaksi pelilautaa. Nämä pelilaudat ovat arvoina oliomuuttajana olevassa HashMap-oliossa. Konstruktorissa Logiikka luo LaivojenAsettaja-olion, ja kutsuu sen metodia luoLaivatAutomaattisesti. Luodut laivat annetaan pelilautojen konstruktoreille.
LaivojenAsettaja ei ole oliomuuttuja.

Jos peli on yksinpeli, käyttöliittymään kuuluu yksi TekoalyPelaaja-olio. TekoalyPelaajalle annetaan konstruktorissa käyttöliittymän luoma Logiikka-olio.

Pelilauta-oliossa on HashMap-oliossa Laiva-olioita. Avaimina toimivat kyseisen Laiva-olion ruutu-oliot. Käyttöliittymä, LaivojenAsettaja ja Tekoaly -luokilla on oliomuuttujana Asetukset-olio, sillä ne tarvitsevat tietoa esimerkiksi pelilaudan leveydestä, joka on tallennettuna Asetukset-olioon.

LaivojenAsettaja luo Laiva-oliot, kun se arpoo niiden paikat. Logiikka lisää osumia Laiva-olioihin. Laiva pitää sisällään sen muodostavat Ruutu-oliot.

Tekoaly, TekoalyPelaaja ja LaivojenAsettaja pitävät ArrayList-olioissa Ruutu-olioita. LaivojenAsettaja-oliossa Ruutu-oliot pitävät kirjaa ruuduista, joihin ei saa asettaa laivaa. Tekoaly-olion Ruutu-oliot pitävät kirjaa arvatuista ruuduista, sekä osumista. TekoalyPelaaja-luokan Ruutu-oliot pitävät kirjaa vuoron aikana tehdyistä osumista sekä siirroista.
