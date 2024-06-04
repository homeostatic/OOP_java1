##################################################################################################################################
Objektorientierte Programmierung SoSe24
Java Projekt 1 - Address book
Jenna Krüger [stu223281] & Sam Tagart Cottis [stu247962]

Aufgabenstellung:
# 1. Entwerfen Sie zuallerst eine Klasse die einen Personenkontakt wiederspiegelt.

    # Eine Person hat ...

        #     einen Namen bestehend aus Vor- und einen Nachnamen.
        #     eine Adresse bestehend aus Stadt, Postleitzahl, Straße und Hausnummer

    # Legen sie dazu jeweils eine Klasse für den Namen und die Adresse an.

    # Es sollte möglich sein die Daten aus einem Kontakt auszulesen, zu setzen und auszugeben.
    # Ergänzen Sie für alle Klassen getter und setter Methoden und eine toString() Methode, die entsprechend eine Lesbare Darstellung der Klasse zurückgibt.

    # Testen Sie die Funktionalität dieser Klasse. Zum Beispiel könnte folgendes Programm folgende Ausgabe erzeugen:

        # Contact contact = new Contact();
        # contact.setName(new Name("Sören", "Domrös"));
        # Address b = new Address("Kiel", 24118, "Christian-Albrechst-Platz", 4);
        # contact.setAddress(b);
        # System.out.println(contact);

        # Sören Domrös
        # 24118 Kiel, Christian-Albrechts-Platz 4

# 2. Entwerfen sie ein Adressbuch

    # Ein Adressbuch einhält eine beliebige Anzahl von Kontakten. Nutzen sie eine ArrayList (siehe Präsenzübung) um eine beliebige Anzahl von Objekten in einer Liste zu speichern.
    # Das Adressbuch soll folgende Methoden bereitstellen:

    #     deleteContact() - erlaubt interaktiv über Konsoleneingabe eine Kontakt auszuwählen und zu löschen
    #     addContact() - erlaubt interaktiv über Konsoleneingabe eine neuen Kontakt anzulegen und dem Adressbuch hinzuzufügen. Hier ist es möglich Teile des Kontakts, zum Beispiel die Adresse unausgefüllt zu lassen.
    #     printContacts() - gibt das Adressbuch menschenlesbar auf der Konsole aus.
    #     search(String s) - durchsucht alle Kontakte nach dem vorkommen von s: Hier sollte es egal, wo innerhalb eines Kontaktes das Wort vorkommt. Zum Beispiel könnte die suche nach "Horst" sowas den Eintrag der Firma (siehe unten) mit dem Namen "HSG Horst/Kiebitzreihe", der Person "Horst Dieter" oder einer Person aus dem Ort "Horst" ergeben.

    # Zusätzlich ist eine Klasse mit einer main-Methode teil der Abgabe in der die oben beschrieben Funktionalitäten demonstriert werden.

# 3. Erweitern Sie ihre Kontaktarten um einen Kontakteintrag für Firmen

    # Eine Firma hat ...

    #     einen Namen zum Beispiel "SDO unlimited"
    #     eine Firmenhauptsitz bestehend aus Stadt, Postleitzahl, Straße und Hausnummer
    #     einen Besitzer der einen Vor- und Nachnamen hat

    # Überlegen Sie sich, wie Sie mit Hilfe von Vererbung eine abstrakte Oberklasse für Kontakte entwerfen und wie sie andere Attribute und Methode sinnvoll darin auslagern können.
    # Begründen Sie diese Entscheidungen und geben Sie sie als Teil ihrer Dokumentation (siehe unter ab).

# 4. Dokumentation (Am besten schon während der vorherigen Schritte anfangen):

    # Alle Methoden, Attribute und Klassen sollen hinreichend dokumentiert sein (Kommentare).
    # Alle Annahmen, die Sie über den Aufbau von Namen, Adressen, etc. für eventuell unvollständige Einträge machen, müssen  dokumentiert werden.
    # Alle etwaigen zusätzlichen Annahmen über den Aufbau eines Kontakts sind ebenfalls dort dokumentiert (siehe 3).
    # Die Dokumentation enthält die benutze Java Version, die benutze Entwicklungsumgebung und Beschreibung wie ihr Adressbuch benutzt werden soll.
    # Hier kann die mitgelieferte Klasse mit einer Main-Methode als Beispiel genutzt werden.

# 5. Kapselung:

    # Alle Attribute aller Klassen sollten privat sein.
    # Es sollte von außerhalb nur möglich sein auf die Methoden des Adressbuchs die in 2. bzw. 3. beschrieben sind zuzugreifen. Alle anderen Methoden sind dabei entweder private oder package-private.
    # Es darf also nicht möglich sein einen bereits erstellten Kontakt zu editieren.

# Abgabe:
# Die Abgabe besteht aus einem .zip Archiv im dem alle Klassen in ihrer entsprechenden Ordnersturktur enthalten sind, mitsamt ihrer Dokumentation als Textdatei mit der Endung .txt (kein Word, kein pdf, kein ....). Wir erwarten Java-Code der ohne Probleme kompiliert und lauffähig ist.

Todo:
    !# we need to figure out how to allow both Contact and Firma objects in our Adressbuch ArrayList.
        # maybe we can make it an ArrayList of abstraktContacts?
        # or can we make an ArrayList that accepts 2 Objekt-types?
        # worst case we could also just have a second ArrayList with the Firmen #done!

    # rewrite interaktiv console tool to be generalised to all contact types
        # Aufgabenstellung says the addContact() should be the interactive one
        # do we want to move parts of it to the repective classes (make the code more modular)

    # fix search to work with Firmen, can we generalise this somehow? all the if's are kind of ugly
        # maybe we could use the toString() methods to search all Attributes at once? #done!

    # can we move more stuff into the abstraktContact class?
        # maybe a constructor that can be called from the addContact() method in Addressbook?
        # maybe the interactive part addAddress part?

    # write this document.  
