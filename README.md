# S1.05.Java_Utils

## Statement of the exercise

# Exercici 1
Crea una classe que llisti alfabèticament el contingut d'un directori rebut per paràmetre.

# Exercici 2
Afegeix a la classe de l’exercici anterior, la funcionalitat de llistar un arbre de directoris amb el contingut de tots els seus nivells (recursivament) de manera que s'imprimeixin en pantalla en ordre alfabètic dins de cada nivell, indicant a més si és un directori (D) o un fitxer (F), i la seva última data de modificació.

# Exercici 3
Modifica l’exercici anterior. Ara, en lloc de mostrar el resultat per la pantalla, guarda el resultat en un fitxer TXT.

# Exercici 4
Afegeix la funcionalitat de llegir qualsevol fitxer TXT i mostra el seu contingut per consola.

# Exercici 5
Ara el programa ha de serialitzar un Objecte Java a un fitxer .ser i després l’ha de desserialitzar.

## Features

Alphabetical listing of a directory's contents.
Recursive directory tree traversal with file/directory type and last modified timestamp.
Option to export the directory tree output to a .txt file.
Ability to read any .txt file and print its contents to the console.
Java object serialization and deserialization to/from .ser files.

## Technologies

Language: Java
Testing: JUnit 5
Build Tools: Maven or Gradle (depending on project setup)

## Installation & Execution

Clone the repository
Open the project
Run the application
Navigate to the Main class of each exercise.
Execute it using your IDE or the command line.


## Diagrams & Technical Decisions

The project follows a single-responsibility structure, where each class handles one specific feature (directory listing, formatting, file handling, serialization).
I use interface and variable Path (instead of File) for more sturdiness and good practice. 
Recursive traversal is handled through a helper method using depth-level indentation.
Java NIO (java.nio.file.*) is used instead of legacy IO for improved performance and readability.
Appendable is preferred over System.out directly to allow better testability.
Serialization uses ObjectOutputStream and ObjectInputStream to ensure standard Java-compatible .ser files.
