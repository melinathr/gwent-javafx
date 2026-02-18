# Gwent (JavaFX) — Card Game Clone

A JavaFX (FXML) implementation of the **Gwent** card game (from *The Witcher* universe).  
This project includes core gameplay mechanics, factions, card abilities, and basic account/deck management.

## Features
- JavaFX UI with FXML
- Register / Login flow
- Deck management (create/edit/select)
- Core Gwent-style board with rows (Close / Ranged / Siege)
- Multiple factions (e.g., Northern Realms, Nilfgaard, Monsters, Skellige, Scoia’tael)
- Card abilities (examples: Spy, Medic, Scorch, Muster, Tight Bond, Morale Boost, Decoy, Commander's Horn, etc.)
- JSON dependency for data handling

## Tech Stack
- Java (JDK 21)
- JavaFX (FXML)
- Maven

## Requirements
- **JDK 21**
- **Maven**
- (Optional) IntelliJ IDEA

## Run
From the project directory (the one that contains `pom.xml`):

```bash
mvn clean javafx:run
