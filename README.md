# Zad3-PPKWU

API służące do sprawdzenia występujących w ciągu znaków i zwraca odpowiednio sformatowane dane.
Sprawdza występowanie dużych i małych znaków,liczb,znaków specjalnych i dowolnych ich kombinacji.
Aby móc skorzystać z aplikacji należy postawić serwer https://github.com/SandersLR-dev/zad2-PPKWU
i server https://github.com/SandersLR-dev/zad3-PPKWU

## Jak stosować
```html
localhost:8082/JSON/{typ danych do konwersji}/{tutaj wpisujemy text do analizy} - zwracany typ danych to JSON
localhost:8082/XML/{typ danych do konwersji}/{tutaj wpisujemy text do analizy} - zwracany typ danych to XML
localhost:8082/CSV/{typ danych do konwersji}/{tutaj wpisujemy text do analizy} - zwracany typ danych to CSV
localhost:8082/TXT/{typ danych do konwersji}/{tutaj wpisujemy text do analizy} - zwracany typ danych to TXT
```

## Przykład użycia
TXT

Wysyłamy request na adres aby otrzymac dane w formacie txt skonwertowane z JSONa
localhost:8082/TXT/JSON/Test 4545 koniec

Wynik:
```text
givenString: Test 4545 koniec\nlenght: 16\nletter: 10\nlowerCase: 9\nupperCase: 1\nnumber: 4\nwhiteSpace: 2\nspecialChar: 0\n
```


JSON

Wysyłamy request na adres aby otrzymac dane w formacie JSON skonwertowane z TXT
localhost:8082/JSON/TXT/Test 4545 koniec

Wynik:
```json
{
"givenString": "Test 4545 koniec",
"length": 16,
"letter": 10,
"lowerCase": 9,
"upperCase": 1,
"number": 4,
"whiteSpace": 2,
"specialChar": 0
}
```
XML

Wysyłamy request na adres aby otrzymac dane w formacie XML skonwertowane z JSON
localhost:8082/XML/JSON/Test 4545 koniec

Wynik:
```xml
<root>
<number>4</number>
<givenString>Test 4545 koniec</givenString>
<whiteSpace>2</whiteSpace>
<upperCase>1</upperCase>
<letter>10</letter>
<lowerCase>9</lowerCase>
<specialChar>0</specialChar>
<length>16</length>
</root>
```
CSV

Wysyłamy request na adres aby otrzymac dane w formacie CSV skonwertowane z TXT
localhost:8082/CSV/TXT/Test 4545 koniec

Wynik:
```text
number,givenString,whiteSpace,upperCase,letter,lowerCase,specialChar,length
4,Test 4545 koniec,2,1,10,9,0,16
```
