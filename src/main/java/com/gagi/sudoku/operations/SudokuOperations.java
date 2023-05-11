package com.gagi.sudoku.operations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SudokuOperations {

	public int[][] newSudoku() {
			Path path = Paths.get("puzzles.txt");
			List<String> allGames;
			int[][] sudoku = new int[9][9];
			try {
				allGames = Files.readAllLines(path);
				int random = (int)(Math.random() * allGames.size());
				String game = allGames.get(random);
				sudoku = toMatrix(game);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    return sudoku;
	}
	
	public int[][] currentGame() {
		String game;
		int[][] sudoku = null;
		try {
			game = this.getCurrentGameFromFile();
			sudoku = this.toMatrix(game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sudoku;
	}
	
	private int[][] toMatrix(String game) {
		int[][] sudoku = new int[9][9];
		String[] tokens = game.split(",");
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(tokens[9*i + j]);
			}
		}
		return sudoku;
	}
	
	private String getCurrentGameFromFile() throws IOException {
		Path path = Paths.get("game.txt");
	    String game = Files.readAllLines(path).get(0);
	    return game;
	}
	
	public void storeCurrentGame(int[][] sudoku, boolean append) throws IOException {
		Path fileName = Path.of("game.txt");
		String game = toOneLine(sudoku);
		if(append) {
			Files.writeString(fileName, game, StandardOpenOption.APPEND);
		}
		else {
			Files.writeString(fileName, game);
		}
	}
	
	public int[][] set(int[][] game, int row, int col, int val) {
		game[row][col] = val;
		return game;
	}
	
	public boolean isValueAcceptable(int val) {
		return val > 0 && val < 10;
	}
	
	public boolean areCoordinatesAcceptable(int row, int col) {
		return col >= 0 && row >= 0 && col < 10 &&  row < 10;
	}
	
	public void printGame(int[][] game) {
		System.out.print("      ");
		for(int i = 0; i < 9; i++) {
			if(i == 3 || i == 6) System.out.print("   ");
			System.out.print("  "+i+"  ");
		}
		System.out.println();
		for(int i = 0; i < 9; i++) {
			if(i == 3 || i == 6) {
				System.out.println();
			}
			System.out.print(i + "     ");
			for(int j = 0; j < 9; j++) {
				if(j == 3 || j == 6) {
					System.out.print("   ");
				}
				if(game[i][j] == 0) {
					System.out.print("| _ |");
				}
				else {
					System.out.print("| " + game[i][j] + " |");
				}
				if(j == 8) {
					System.out.println();
				}
			}
		}
	}
	
	private String toOneLine(int[][] sudoku) {
		String game = "";
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				game = game + sudoku[i][j] + ",";
			}
		}
		return game;
	}
	
	public boolean isSolved(int[][] sudoku) {
		if(hasConflicts(sudoku)) return false;
		for(int i = 0; i < 9; i++) {
			int sumRow = 0;
			int sumCol = 0;
			for(int j = 0; j < 9; j++) {
				sumRow += sudoku[i][j];
				sumCol += sudoku[j][i];
			}
			if(sumRow != 45 || sumCol != 45) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasConflicts(int[][] sudoku) {
		int countInRow;
		int countInCol;
		for(int val = 1; val < 10; val++) {
			for(int row = 0; row < 9; row++) {
				countInRow = 0;
				countInCol = 0;
				for(int col = 0; col < 9; col++) {
					if(sudoku[row][col] == val) {
						countInRow += 1;
					}
					if(sudoku[col][row] == val) {
						countInCol += 1;
					}
					if(countInRow > 1 || countInCol > 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
