package com.gagi.sudoku;

import com.gagi.sudoku.operations.SudokuOperations;

import picocli.CommandLine.Command;

@Command(name = "show")
public class ShowGame implements Runnable {

	SudokuOperations operations = new SudokuOperations();

	@Override
	public void run() {
		System.out.println("showing sudoku game");
		int[][] sudoku = operations.currentGame();
		if (operations.hasConflicts(sudoku)) {
			System.out.println("there are conflicts in your sudoku".toUpperCase());
		}
		if(operations.isSolved(sudoku)) {
			System.out.println("game is solved".toUpperCase());
		}
		operations.printGame(sudoku);
	}
}
