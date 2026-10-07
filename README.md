# Übungsblatt: Objektorientierte Programmierung - Grundlagen
[Link to English version](./README_en.md)

In diesem Übungsblatt üben Sie konkrete Vererbung, Überschreiben und Zusammensetzung. Voraussetzungen sind eigene Klassen, Konstruktoren, Methoden und einfache Collections aus ÜB 06. Bearbeiten Sie zuerst die Tiere und den Zoo, anschließend das Bücherregal.

## Übung 1: Vererbung (`Animals` - Tiere)

### Teil 1: Vererbung bei Tieren

Betrachten Sie die folgende Hierarchie von Tierklassen:

* Tier (Animal)
   * Säugetier (Mammal)
      * Katze (Cat)
      * Hund (Dog)

#### Aufgaben

Implementieren Sie die Klassen im Paket `de.phl.programmingproject.animals`.
1. Erstellen Sie eine `Animal`-Klasse, die ein `name`-Feld und eine `speak`-Operation hat, die ihren Namen mit `System.out.format("I am %s and I am an animal.%n", name);` auf der Konsole ausgibt. Implementieren Sie auch eine Getter-Operation für den Namen.
2. Erstellen Sie eine `Mammal`-Klasse, die von `Animal` erbt und ein `breed`-Feld (Rasse) vom Typ `String` hinzufügt. Überschreiben Sie die `speak`-Operation, um auch die Rasse auszugeben. Implementieren Sie auch eine Getter-Operation für die Rasse.
3. Erstellen Sie `Cat`- und `Dog`-Klassen, die von `Mammal` erben.
4. Überschreiben Sie die `speak`-Operation in jeder Unterklasse, um eine nach Tierart spezifische Nachricht zusätzlich zu den Ausgaben der Elternklasse auszugeben. Beispielsweise sollte die `speak`-Operation in der `Cat`-Klasse auch `"Meow!"` ausgeben.
5. Implementieren Sie eine Operation `meow` für `Cat` und `bark` für `Dog`, um `"Meow!"` bzw. `"Woof!"` auszugeben.

### Teil 2: Zoo

Stellen Sie sich vor, Sie erstellen eine Zoo-Simulation. Neben der obigen Hierarchie von Tieren benötigen Sie eine `Zoo`-Klasse, um die Tiere zu verwalten.

#### Aufgaben

1. Erstellen Sie eine `Zoo`-Klasse, die eine Menge (`Set`) von `Animal`-Objekten und eine `displayAnimals`-Operation hat, die es jedem Tier ermöglicht zu sprechen.
2. Implementieren Sie eine Operation, um ein Tier zum Zoo hinzuzufügen (`addAnimal`), und eine Operation, um ein Tier aus dem Zoo zu entfernen (`removeAnimal`).
3. Entfernen Sie den Kommentar für den Code in der `main`-Operation in der `Main`-Klasse und führen Sie ihn aus, um zu sehen, was passiert.

## Übung 2: Bücherregal und Buchklasse (Zusammensetzung)

In dieser Übung werden Sie objektorientiertes Programmieren im Kontext eines Bücherregals (`Bookshelf`) und eines Buchverwaltungssystems üben.
Implementieren Sie die Klassen im Paket `de.phl.programmingproject.bookshelf`.

### Aufgabe 1: Die `Bookshelf` Klasse
Erstellen Sie eine `Bookshelf` Klasse, die eine Sammlung (`Set`) von Büchern repräsentiert. Die `Bookshelf` Klasse sollte die folgenden Eigenschaften haben:

- Ein `Set` von Büchern (`Book`) (verwenden Sie ein `HashSet`, um die Bücher zu speichern)
   - Imlementieren Sie auch einen entsprechenden Getter `getBooks`
- Eine `capacity` (maximale Anzahl von Büchern, die das Bücherregal aufnehmen kann)

Die `Bookshelf` Klasse sollte auch die folgenden Operationen haben:

- Eine Operation zum Hinzufügen eines Buches zum Bücherregal (`addBook`). Wenn das Bücherregal bereits seine maximale Kapazität erreicht hat, sollte die Operation eine `IllegalStateException` auslösen und das Buch nicht hinzufügen.
- Eine Operation zum Entfernen eines Buches aus dem Bücherregal (`removeBook`). Wenn das Bücherregal leer ist oder das Buch nicht enthält, sollte die Operation eine `IllegalStateException` auslösen.
- Eine Operation zum Abrufen der Gesamtanzahl der Seiten aller Bücher im Bücherregal (`getTotalNumberOfPages`).

### Aufgabe 2: Die `Book` Klasse
Erstellen Sie eine `Book` Klasse, die ein Buch repräsentiert. Die `Book` Klasse sollte die folgenden Eigenschaften haben:

- Titel (`title`)
- Autor (`author`)
- Anzahl von Seiten (`pages`)

Die `Book` Klasse sollte auch die folgenden Operationen haben:

- Einen Konstruktor, der den Titel, den Autor und die Anzahl der Seiten als Argumente akzeptiert.
- Getter-Operationen für die Eigenschaften Titel, Autor und Anzahl der Seiten.

### Aufgabe 3: Die `Main` Operation
Erstellen Sie schließlich in der `main` Operation ein `Bookshelf` Objekt und fügen Sie mehrere `Book` Objekte hinzu. Die `main` Operation sollte auch die Operation des `Bookshelf` Objekts aufrufen, um die Gesamtanzahl der Seiten abzurufen und diese Zahl auszugeben.

### Bonus

Implementieren Sie eine `equals`-Operation in der `Book` Klasse, die überprüft, ob zwei Bücher gleich sind. Zwei Bücher gelten als gleich, wenn sie denselben Titel, Autor und dieselbe Seitenanzahl haben.

## Ausblick auf ÜB 07b

Dieses Blatt behandelt konkrete Vererbung, Überschreiben und Zusammensetzung. Im Zoo wird bereits dieselbe Operation über den gemeinsamen Typ `Animal` aufgerufen: Die konkrete Tierklasse bestimmt die ausgeführte Implementierung.

Das Mitarbeiterbeispiel mit einer abstrakten Basisklasse folgt in [ÜB 07b](https://github.com/PHL-INF520-Programmierprojekt-Uebungen/uebungsblatt-07b-oop-advanced). Dort vergleichen Sie abstrakte Klassen mit Interfaces. Für dieses Blatt werden noch keine eigenen abstrakten Klassen oder Interfaces benötigt.
