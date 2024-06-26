# LABORATORIUM 02

## Kontynuacja LABORATORIUM 01 oraz stworzenie aspekt logującego wywołania metod serwisów

## ZADANIE 1. Sieciowe API do operacji typu CRUD na Training (bez użycia rekordów)

### Potrzeba biznesowa

Jako użytkownik, chce mieć możliwość dostępu do panelu z treningami:

- tworzenie nowych,
- wyświetlanie swoich treningów,
- aktualizacji trenigów

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [x] utworzenie nowego treningu
- [x] wyszukiwanie wszystkich treningów
- [x] wyszukiwanie treningów dla określonego Użytkownika:
  - [x] wyszukiwanie wszystkich treningów zakończonych (po konkretnej zdefiniowanej dacie)
  - [x] wyszukiwanie wszystkich treningów dla konkretnej aktywności (np. wszystkie treningi biegowe)
  - [x] aktualizacja treningu (dowolnie wybrane pole np. dystans)

### Wymagania techniczne

- [x] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [x] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [x] rozwiązanie powinno spełniać zasady SOLID
- [x] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [x] OPCJONALNE rozwiązanie powinno implementować logikę potrzebną do spełnienia już
  istniejących [testów integracyjnych API]
  . NIE należy zmieniać logiki tych testów.
- [x] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [x] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [x] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [x] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

## ZADANIE 2 (opcjonalne). Sieciowe API do operacji typu CRUD na Statistics (bez użycia rekordów)

### Potrzeba biznesowa

### Wymagania funkcjonalne

Stworzone API powinno pozwalać na:

- [ ] wylistowanie podstawowych informacji o wszystkich statystykach zapisanych w systemie
- [ ] utworzenie nowych statystyk
- [ ] aktualizacja Statystyk Użytkownika implementacja funkcjonalności do aktualizacji istniejących statystyk dla
  użytkownika.
- [ ] pobranie szczegółów dotyczących statystyk dla danego użytkownika
- [ ] usunięcie statystyk
- [ ] wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter, wyszukujące po fragmencie nazwy (zwracane
  tylko ID oraz e-mail użytkowników)
- [ ] wyszukiwanie użytkowników po wieku starszym niż zdefiniowany

### Wymagania techniczne

- [ ] przygotowanie danych wejściowych (uzupełnienie skryptu ładującego dane przy starcie aplikacji)
- [ ] API sieciowe powinno wykorzystywać protokół HTTP oraz format JSON do transferu danych
- [ ] w repozytoriach rozwiązanie może wykorzystywać metody dostarczane przez interfejs JpaRepository oraz metody
  domyślne, pobierające dane za pomocą `findAll()` oraz przetwarzające je za pomocą strumieni (`Stream`). Przykład
  znaleźć można w `UserRepository`
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] rozwiązanie powinno być pokryte testami jednostkowymi (>80%)
- [ ] OPCJONALNE rozwiązanie powinno implementować logikę potrzebną do spełnienia już
  istniejących [testów integracyjnych API]
  . NIE należy zmieniać logiki tych testów.
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
- [ ] do kodu powinna zostać dołączona wyeksportowana kolekcja zapytań z programu Postman, pozwalająca przetestować
  stworzone API
- [ ] rozwiązanie powinno wykorzystywać zwykłe klasy Javowe do definicji obiektów transferu danych (DTO)

## ZADANIE 3. Programowanie aspektowe.

### Potrzeba biznesowa

Jako pracownik utrzymania, chcę mieć możliwość kontroli wykonania się kodu programu za pomocą logów aplikacji.

### Wymagania funkcjonalne

- [ ] być uruchomiony podczas wywoływania metod publicznych serwisów (klas adnotowanych `@Service`)
- [ ] przed wywołaniem metody logować o niej informację w
  formacie (`typ zwracany nazwaKlasy.nazwaMetody(typParametru1 nazwaParametru1, ...)`),
  np. `void MyService.myMethod(String param1, Boolean param2)`
- [ ] po wywołaniu metody logować informację o metodzie (w tym samym formacie co przed wywołaniem) wraz z informacją na
  temat zwróconej wartości (wystarczy jej toString())

### Wymagania techniczne

- [ ] aspekt powinien zostać zaimplementowany z użyciem biblioteki AspectJ
- [ ] rozwiązanie powinno spełniać zasady SOLID
- [ ] testy jednostkowe rozwiązania nie są wymagane
- [ ] testy integracyjne rozwiązania nie są wymagane
- [ ] rozwiązanie powinno być odpowiednio zhermetyzowane (nie udostępniać funkcjonalności pozostałym pakietom programu)
- [ ] kod powinien być odpowiednio udokumentowany za pomocą JavaDoc
