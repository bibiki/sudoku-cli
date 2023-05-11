package com.gagi.sudoku;

import java.io.IOException;

import com.gagi.sudoku.operations.SudokuOperations;

import picocli.CommandLine.Command;

@Command(name = "newgame")
public class NewGame implements Runnable {

	SudokuOperations operations = new SudokuOperations();
	
	@Override
	public void run() {
		System.out.println("runing new game");
		int[][] sudoku = operations.newSudoku();
		try {
			operations.storeCurrentGame(sudoku, false);
			operations.printGame(sudoku);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
