What this is
============

This is a command line sudoku game. It has the following commands: newgame, show, set (setvalue), unset.

The newgame command takes no arguments and assumes that possible sudoku puzzles are stored in a file as this application is not able by itself to generate sudoku puzzles.

The show command takes no arguments and shows the current state of the ongoing sudoku game.

The set command has setvalue as alias. An example usage of it is ```sudoku set row col value``` where row, col and value are numbers. Row, and col range between 0 and 8, inclusive, whereas value ranges between 1 and 9, inclusive.

The unser command takes row and column numbers to identify the sudoku cell whose value must be unset.
