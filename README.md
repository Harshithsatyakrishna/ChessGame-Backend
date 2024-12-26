# Chess Game

An interactive chess game developed in Java, featuring a graphical user interface (GUI) and adherence to standard chess rules. This project demonstrates the use of object-oriented programming, GUI design, and game logic implementation.

## Features

- **Turn-based Gameplay**: Alternate turns between white and black players.
- **Piece Movement Validation**: Ensures moves comply with chess rules.
- **Pawn Promotion**: Implements promotion logic for pawns reaching the opposite side of the board.
- **Castling**: Supports castling moves for both players.
- **Capture Logic**: Handles piece capturing and updates the board accordingly.
- **Graphical User Interface**: Built with Java Swing and AWT for an intuitive and interactive experience.
- **Real-time Interaction**: Mouse event handling for piece selection, movement, and validation.

## Installation

1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```

2. Navigate to the project directory:
   ```bash
   cd chess-game
   ```

3. Compile the project using a Java compiler:
   ```bash
   javac -d bin src/Main/*.java src/peice/*.java
   ```

4. Run the application:
   ```bash
   java -cp bin Main.GamePanel
   ```

## How to Play

1. Launch the game by running the application.
2. Select and move pieces by clicking and dragging them to valid positions.
3. Follow chess rules for each piece's movement and actions.
4. The game alternates turns between white and black players.

## Project Structure

```
chess-game/
├── src/
│   ├── Main/
│   │   ├── GamePanel.java
│   │   ├── board.java
│   │   └── Mouse.java
│   ├── peice/
│       ├── Piece.java
│       ├── Pawn.java
│       ├── Rook.java
│       ├── Knight.java
│       ├── Bishop.java
│       ├── Queen.java
│       └── King.java
├── bin/
├── README.md
└── resources/
    └── images/  # Piece icons
```

## Technologies Used

- **Programming Language**: Java
- **Libraries**: Java Swing and AWT

## Future Enhancements

- Add support for AI-based opponent.
- Include a timer for each player's move.
- Implement save/load game functionality.
- Enhance the GUI with improved graphics and animations.

## Acknowledgements

Special thanks to the creators of open-source chess resources and tutorials that guided the development of this project.

---

Feel free to fork the repository and contribute to the project. Happy coding!

