# jobTaskOne

Dzień dobry! :)

Dziękuję za szansę i możliwość zrobienia zadania, w tym repozytorium znajduje się moja propozycja rozwiązania. Zgodnie z założeniami, aplikacja udostępnia dwa endpointy:
* GET /api/transfers zwróci listę wszystkich zapisanych transferów.
* POST /api/transfers po przekazaniu pliku HTML doda do bazy danych nowe elementy.

## Jak to jest zrobione?
W ramach zadania stworzyłem kolekcję "transfers", której pola pokrywają się z danymi umieszczonymi w przykładowymi pliku. Dodatkowo dodałem pole ID, oraz nieco inaczej podszedłem do samej kwoty - nie wyróżniam tutaj obciążenia od uznania, ponieważ prościej jest po prostu przechowywać kwotę (znak sam powie o odpowiednim umieszczeniu w przypadku eksportu).
TransferCollectionDTO to niemal bliźniacza klasa do TransferCollection, jednakże uznałem, że zastosowanie wzorca DTO nawet pomimo stosunkowo niewielkiej złożoności projektu poprawi jakość kodu. Kiedy uczyłem się Springa usłyszałem określenie "Encja na twarz i pchasz" - staram się robić inaczej :).
TransferWrapper to klasa pomocnicza, wykorzystuje ją do konwersji pomiędzy Elements[] -> TransferCollectionDTO oraz TransferCollectionDTO -> TransferCollection).
RestApiControllers to po prostu klasa kontrolera, przechwytuje żądania do API. Pracowałem nad nią najdłużej, ale o tym trochę niżej.
RestApiService to klasa, której zadania opierają się na sprawdzeniu, czy można dodać konkretny Transfer (o tym też niżej), oraz konwertuje dane z html do javy za pomocą wrappera.
TransferRepository ma w sumie najmniej ciekawych rzeczy, dodałem jedynie customową metodę do znajdowania duplikatów na podstawie moich kryteriów.
Wykorzystałem również Jsoup do parsowania html w przyjaźniejszą formę, mogłem to robić oczywiście ręcznie, ale uznałem, że nie ma to sensu.

## Problem duplikatów
Aplikacja tego typu ma dość spory problem, zaznaczony zresztą w poleceniu - duplikaty. Uznałem, że za duplikat będziemy uważać element, w którym pokrywa się zarówno data transakcji jak i opis, nie jest to perfekcyjne rozwiązanie. Co zrobiłbym inaczej? Na poziomie danych, dobrze byłoby dodać dokładny czas wykonania transakcji, wtedy nie byłoby wątpliwości. 

## Czego się nauczyłem?

Sporo by wymieniać :) Jestem raczej ambitnym człowiekiem, dlatego nie poddałem się po zobaczeniu polecenia. Zaczynając pisać aplikację podszedłem do tego z podejściem "op ogółu do szczegółu" - najpierw napisałem kolejkcję, parser, podpiąłem bazę danych i napisałem REST API w klasyczny sposób (co w historii commitów zapewne jest). Potem już przepisałęm API na WebFlux - jak wyszło, zapewne ocenić potrafi ktoś bardziej kompetentny - starałem się uzyskać efekt stosując się do zasady KISS i wszelkie wątpliwości googlując. Jestem przekonany, że da się to zrobić lepiej i dużo nauki przede mną, i to chyba najważniejsze - cały czas pracować.

##Dlaczego chciałbym dostać tę pracę?

Bardzo lubię programować i tworzyć coś nowego, a jeśli do tego dorzucę trochę ambicji i pracowitości, to wychodzi z tego całkiem przyzwoity programista. Na pewno mam braki, ale jeśli ktoś mi je wskaże i da szansę - dam z siebie wszystko, żeby to poprawić. Dziękuję bardzo za poświęcenie mi czasu, z chęcią też porozmawiam o kodzie - miłego dnia bądź wieczoru.
