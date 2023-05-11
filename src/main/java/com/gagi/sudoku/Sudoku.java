package com.gagi.sudoku;

import picocli.CommandLine.Command;

@Command(name = "sudoku", subcommands = {NewGame.class, ShowGame.class, SetCellValue.class, UnsetCellValue.class})
public class Sudoku {
	
}
