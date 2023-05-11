package com.gagi.sudoku;

import java.io.IOException;

import com.gagi.sudoku.operations.SudokuOperations;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "unset")
public class UnsetCellValue implements Runnable {

	SudokuOperations operations = new SudokuOperations();
	
	@Parameters(paramLabel = "col", index = "1")
	int col;
	
	@Parameters(paramLabel = "row", index = "0")
	int row;
	
	@Override
	public void run() {
		System.out.println("unsetting " + row + " " + col);
		if(col < 0 || row < 0 || col > 9 || row > 9) {
			System.out.println("col and row must both be between 0 and 9");
			return;
		}
		int[][] sudoku = operations.currentGame();
		sudoku[row][col] = 0;
		try {
			operations.storeCurrentGame(sudoku, false);
			operations.printGame(sudoku);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
