package com.gagi;

import com.gagi.sudoku.Sudoku;

import picocli.CommandLine;

public class App {
	
	public static void main(String[] args) {
		int exitCode = new CommandLine(new Sudoku()).execute(args);
		System.out.println(exitCode);
	}
}
