package sherpa.app.tictactoe.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Board implements Parcelable {

    private Cell[][] cells = new Cell[3][3];

    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Board() {
        restart();
    }

    public Board(Player winner, GameState state, Player currentTurn) {
        this.winner = winner;
        this.state = state;
        this.currentTurn = currentTurn;
        restart();
    }

    protected Board(Parcel in) {
    }

    public static final Creator<Board> CREATOR = new Creator<Board>() {
        @Override
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        @Override
        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    private enum GameState {IN_PROGRESS, FINISHED}

    public void restart() {
        clearCells();
        winner = null;
        currentTurn = Player.X;
        state = GameState.IN_PROGRESS;
    }

    public Player mark(int row, int col) {
        Player playerThatMoved = null;

        if (isValid(row, col)) {
            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;

            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED;
                winner = currentTurn;
            }
        }
        return playerThatMoved;
    }

    public Player getWinner() {
        return winner;
    }

    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {
        return (cells[currentRow][0].getValue() == player
                && cells[currentRow][1].getValue() == player
                && cells[currentRow][2].getValue() == player
                || cells[0][currentCol].getValue() == player
                && cells[1][currentCol].getValue() == player
                && cells[2][currentCol].getValue() == player
                || currentRow == currentCol
                && cells[0][0].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][2].getValue() == player
                || currentRow + currentCol == 2
                && cells[0][2].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][0].getValue() == player);
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == Player.X ? Player.O : Player.X;
    }

    private boolean isValid(int row, int col) {
        if (state == GameState.FINISHED) {
            return false;
        } else if (isOutOfBounds(row) || isOutOfBounds(col)) {
            return false;
        } else if (isCellValueAlreadySet(row, col)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx > 2;
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }

    private void clearCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
