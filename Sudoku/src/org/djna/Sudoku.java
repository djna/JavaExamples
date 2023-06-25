package org.djna;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayDeque;
import java.util.Deque;

public class Sudoku {

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
        int[][] initialBoard = {
                {0, 0, 0, 0, 0, 2, 1, 0, 0},
                {0, 0, 4, 0, 0, 8, 7, 0, 0},
                {0, 2, 0, 3, 0, 0, 9, 0, 0},
                {6, 0, 2, 0, 0, 3, 0, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 0, 6, 0, 0, 3, 0, 1},
                {0, 0, 3, 0, 0, 5, 0, 8, 0},
                {0, 0, 8, 2, 0, 0, 5, 0, 0},
                {0, 0, 9, 7, 0, 0, 0, 0, 0}
        };

        // Initialising
        System.out.println("Solving board:");
        printBoard(initialBoard);

        // Set up the stack
        Deque<int[][]> stack = new ArrayDeque<>();
        stack.add(initialBoard);

        while (!stack.isEmpty()) {
            int[][] board = stack.remove();
            Slot slot = getEmptySlot(board);

            if (slot == null) {
                System.out.println("Solved!");
                printBoard(board);
                return;
            }

            for (int guess = 1; guess <= 9; guess++) {
                if (isValidInSlot(guess, slot, board)) {
                    stack.add(updateBoard(guess, slot, board));
                }
            }
        }
    }

    private static Slot getEmptySlot(int[][] board) throws ExecutionControl.NotImplementedException {

        for ( int row = 0; row < board.length; row++){
            for ( int col = 0; col < board[row].length; col++){
                if ( board[row][col] == 0){
                    return new Slot(row, col);
                }
            }
        }
        return null;
    }

    private static boolean isValidInSlot(int guess, Slot slot, int[][] board) throws ExecutionControl.NotImplementedException {
        return isValidInRow(slot.row, guess, board) &&
                isValidInCol(slot.col, guess, board) &&
                isValidInSquare(slot, guess, board);
    }

    private static boolean isValidInRow(int row, int guess, int[][] board) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == guess) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidInCol(int col, int guess, int[][] board) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == guess) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidInSquare(Slot slot, int guess, int[][] board) {
        int squareX = slot.row / 3;
        int squareY = slot.col / 3;

        for (int row = squareX * 3; row < (squareX + 1) * 3; row++) {
            for (int col = squareY * 3; col < (squareY + 1) * 3; col++) {
                if (board[row][col] == guess) {
                    return false;
                }
            }
        }
        return true;
    }


    private static int[][] updateBoard(int guess, Slot slot, int[][] board) {
        int[][] newBoard = new int[9][];
        for (int row = 0; row < 9; row++) {
            newBoard[row] = board[row].clone();
        }
        newBoard[slot.row][slot.col] = guess;
        return newBoard;
    }

    private static void printBoard(int[][] board)
    {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 0 ? "  " : cell + " ");
            }
            System.out.println();
        }
    }

    private static class Slot {
        int row;
        int col;

        Slot(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

