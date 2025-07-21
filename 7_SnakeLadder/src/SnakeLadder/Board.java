package SnakeLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
        initializeBoard(boardSize);
        addSnakesLadders(cells, numberOfSnakes, numberOfLadders);
    }

    private void initializeBoard(int boardSize){
        cells = new Cell[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    private void addSnakesLadders(Cell[][] cells, int numberOfSnakes, int numberOfLadders){
        while(numberOfSnakes > 0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            if(snakeTail >= snakeHead){
                continue;
            }

            Jump snakeJump = new Jump();
            snakeJump.start =  snakeHead;
            snakeJump.end = snakeTail;

            Cell snakeCell = getCell(snakeHead);
            snakeCell.jump = snakeJump;

            numberOfSnakes--;
        }

        while(numberOfLadders > 0){
            int ladderHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);

            if(ladderHead >= ladderTail){
                continue;
            }

            Jump ladderJump = new Jump();
            ladderJump.start = ladderHead;
            ladderJump.end = ladderTail;

            Cell ladderCell = getCell(ladderHead);
            ladderCell.jump = ladderJump;
            numberOfLadders--;
        }
    }

    Cell getCell(int playerPosition){
        int boardRow = playerPosition / cells.length;
        int boardCol = playerPosition % cells.length;

        return cells[boardRow][boardCol];
    }


}
