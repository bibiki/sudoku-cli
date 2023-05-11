package com.gagi.sudoku;

import java.io.IOException;

import com.gagi.sudoku.operations.SudokuOperations;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "set", aliases = {"setvalue"})
public class SetCellValue implements Runnable {

SudokuOperations operations = new SudokuOperations();
	
	@Parameters(paramLabel = "col", index = "1")
	int col;
	
	@Parameters(paramLabel = "row", index = "0")
	int row;
	
	@Parameters(paramLabel = "value", index = "2")
	int value;
	
	@Override
	public void run() {
		System.out.println("setting " + row + " " + col);
		if(!operations.areCoordinatesAcceptable(row, col)) {
			System.out.println("col and row must both be between 0 and 9");
			return;
		}
		if(!operations.isValueAcceptable(value)) {
			System.out.println("value has to be between 1 and 9 but you provided " + value);
			return;
		}
		int[][] sudoku = operations.currentGame();
		sudoku[row][col] = value;
		try {
			operations.storeCurrentGame(sudoku, false);
			if(operations.hasConflicts(sudoku)) {
				System.out.println("there are conflicts in your sudoku".toUpperCase());
			}
			if(operations.isSolved(sudoku)) {
				System.out.println("game is solved".toUpperCase());
			}
			operations.printGame(sudoku);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
