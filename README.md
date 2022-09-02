# Press article API

### Dlaczego powstał ten projekt? 

Projekt powstał jako zadanie rekrutacyjne,  w ramach rekrutacji na stanowisko Junior Java Developera. 
Twórcy zadania pozostawili kandydatom sporą dowolność,  w zakresie wyboru narzędzi, użytych do stworzenia aplikacji. 

### Główne zadanie - Stworzernie API dla artykułów prasowych

<details><summary> Szczegóły encji </summary>
<p>
Artykuł prasowy zawiera:

- treść (tytuł publikacji i treść publikacji)
- datę publikacji (data)
- nazwę czasopisma
- autora artykułu (imię i nazwisko)
- datę zapisu dokumentu do warstwy persystencji (timestamp
</p>
</details>

<details><summary> Szczegóły wymaganych endpointów </summary>
<p>

Aplikacja powinna zawierać następujące endpointy:

- endpoint zwracający wszystkie artykuły prasowe posortowane malejąco po dacie publikacji
- endpoint zwracający pojedynczy artykuł prasowy po id
- endpoint zwracający listę wszystkich artykułów prasowych po słowie kluczowym zawartym w tytule lub treści publikacji
- endpoint pozwalający na zapis artykułu prasowego
- endpoint do aktualizacji istniejącego artykułu prasowego
- endpoint do usuwania wybranego artykułu prasowego
- Warstwą persystencji może być zapis do dowolnej bazy SQL lub zapis do pliku
</p>
</details>

<details><summary> Wymagania techniczne </summary>
<p>

- aplikacja może być napisana w czystej Javie lub wykorzystywać dowolny framework
- aplikacja powinna zawierać plik README z instrukcją uruchomienia aplikacji, krótkim opisem endpointów zawierającym informacje o przyjętych regułach walidacji i akceptowalnych requestach
- aplikacja powinna zawierać testy jednostkowe kontrolerów i serwisów
- aplikacja powinna być możliwa do skompilowania i uruchomienia na komputerach z zainstalowanym JDK w wersjach 8 lub 11 i za pomocą Mavena lub Gradle
- Kod aplikacji powinien być przekazany w formie linku do otwartego repozytorium lub archiwum
- Kod aplikacji powinien spełniać wymogi kodu produkcyjnego takie jak - zawarty plik .gitignore, brak nadmiarowego i martwego kodu, w udostępnionym repozytorium powinny być zawarte tylko niezbędne pliki
</p>
</details>

### Stack

* Java 11
* Spring Boot
* H2 
* Lombok

### How to run this? (for dummies)
<details><summary> 1.Narzędzia, które potrzebujesz </summary>
<p>

* Java 11 - https://www.oracle.com/pl/java/technologies/javase/jdk11-archive-downloads.html
* Maven - https://maven.apache.org/download.cgi
* Projek - https://github.com/Dragdas/PressArticleApi.git
</p>
</details>

<details><summary> 2.  Ściąganie projektu </summary>
<p>

#### Nie masz gita: 
* otwórz link https://github.com/Dragdas/PressArticleApi.git
* kliknij zielony przycisk "code" a następnie "download zip"

#### Masz gita:
* przejdź do folderu, do którego chcesz ściągnąć projekt 
* uruchom cmd (możesz wpisać cmd w pasku adresu)
* użyj komendy:
```
git clone https://github.com/Dragdas/PressArticleApi.git
```
</p>
</details>

<details><summary> 3. Budowanie projektu </summary>
<p>

#### Jeżeli mvn jest zdefiniowany w Twoich zmiennych środowiskowych:
* przejdź do ściągniętego repozytorium (przez cmd lub ponownie wpisz cmd w adresie folderu)
* użyj komendy 
```
mvn clean install
```
#### Jeżeli mvn nie jest zdefiniowane w Twoich zmiennych środowiskowych:

* możesz podać dokladną ścieżkę do pliku mvn znajdującego się w archiwum z pkt 1. 
Przykładowa komenda:
```
  "C:\Program Files\maven\bin\mvn" clean install
```
</p>
</details>

<details><summary> 4. Uruchomienie projektu </summary>
<p>

#### Jeżeli chcesz użyć mavena 
* w głównym folderze projektu użyj komendy:
```
  mvn spring-boot:run
```

#### Jeżeli Java jest zdefiniowana w Twoich zmiennych środowiskowych
* przejdź do folderu target w ściągniętym repozytorium 
* użyj komendy:
```
java -jar PressArticleApi-0.0.1-SNAPSHOT.jar
```
#### Jeżeli Java nie jest zdefiniowana w Twoich zmiennych środowiskowych
* użyj dokładnej ścieżki do pliku Java.exe znajdującego się w Java JDK z pkt 1. Przykładowa komenda:
```
"C:\Program Files\Java\jdk-11.0.15.1\bin\java" -jar PressArticleApi-0.0.1-SNAPSHOT.jar
```
</p>
</details>

### Opis endpointów

Api zostało zmapowane pod ścieżką: 

```
/api/articles
```
<details><summary>Get requests</summary><blockquote>

  <details><summary> getAllArticlesSorted() </summary><p>

* Zwraca listę artykułów, posortowaną według daty publikacji, zaczynając od najnowszego.
* Operacja jest wykonywana jednym zapytanie bazodanowym 

```sql
    @Query(value =  "SELECT a FROM Article a " +
                    "LEFT JOIN FETCH a.articleContent " +
                    "LEFT JOIN FETCH a.author " +
                    "ORDER BY a.publicationDate DESC ")
```
  </p></details>

  <details><summary> getArticleById(@PathVariable Long articleId) </summary><blockquote>

* zwraca artykuł o podanym numerze id albo rzuca wyjątek ArticleNotFoundException
* ID przekazywane jest w ścieżce jako @PathVariable

  </blockquote></details>

  <details><summary> getArticleByKeyWord(@RequestParam String keyWord) </summary><blockquote>

znajduje się pod endpointem 

```
/api/articles/search
```
* Zwraca listę artykułów, w których tytule lub treści znalazła się zadana fraza
* Fraza przekazywana jest w ścieżce jako  @RequestParam keyWord
* Nie jest case sensitive
* Operacja jest wykonywana jednym zapytanie bazodanowym
```sql
    @Query(value =  "SELECT a FROM Article a " +
                    "LEFT JOIN FETCH a.author " +
                    "LEFT JOIN FETCH a.articleContent AS content " +
                    "WHERE LOWER(content.title) LIKE LOWER(:keyWord)" +
                    "OR LOWER(content.content)  LIKE LOWER(:keyWord)")
```
  </blockquote></details>

</blockquote></details>

<details><summary> Delete request </summary>
<p>

* Usuwa z bazy artykuł o zadanym nr ID albo rzuca ArticleNotFoundException
* Operacja jest kaskadowana na treść artykułu
* Operacja nie jest kaskadowana na autora artykułu 
* ID przekazywany jest w ścieżce jako @PathVariable

</p>
</details>

<details><summary> Post request </summary>
<p>

* Dodaje nowy artykuł do bazy danych
* Dodaje nową treść artykułu do bazy danych
* Przyjmuje plilk JSON jako @RequestBody
* Odpowiedni serwis jest oznaczony jako @Transactional ze względu na większą ilość zapytań do bazy
* Zwraca artykuł, który został zapisany w bazie 
..
* Przypisuje aktualny timestamp bez względu na dane klienta
* Jeżeli autor o danym ID istnieje wykorzystuje istniejącego autora 
* Jeżeli autor o danym imieniu i nazwisku istnieje wykorzystuje istniejącego autora
* Jeżeli został podany ID, imię i nazwisko autora i zgadzają się one z tymi w bazie artykuł jest dodany 
* Jeżeli został podany ID, imię i nazwisko autora i nie ma go w bazie nowy autor jest tworzony i artykuł zostaje dodany
* Jeżeli zostały podane tylko imię i nazwisko a takiego autora nie ma w bazie nowy autor jest dodany
* Jeżeli został podany ID, imię i nazwisko, w bazie jest autor o podanym ID ale pozostałe dane w bazie, nie są zgodne z tymi z requesta, rzuca InvalidAuthorDataException
* Jeżeli konieczne jest dodanie nowego autora ale w zapytaniu brakuje jego imienia albo nazwiska rzuca IncompleteAuthorInformationException

</p>
</details>

<details><summary> Put request </summary>
<p>

* Zmienia istniejący artykuł 
* Przyjmuje plik JSON jako @RequestBody
* Odpowiedni serwis jest oznaczony jako @Transactional ze względu na większą ilość zapytań do bazy
* Zwraca artykuł z naniesionymi zmianami 
  ..
* Przypisuje aktualny timestamp bez względu na dane klienta 
* Nie pozwala na zmianę treści artykułu jeżeli w zapytaniu, ID treści wskazuje na encję która nie jest 
związana z artykułem  
* rzuca ArticleNotFoundException jeżeli danego artykułu nie ma w bazie
* Jeżeli trzeba zmienić autora artykułu, tworzenie albo wykorzystywanie istniejącego autora przebiega analogiczne do logiki z Post Request

</p>
</details>

