package sherpa.app.tictactoe.viewmodel;

import sherpa.app.tictactoe.model.Board;
import sherpa.app.tictactoe.model.Player;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

public class TicTacToeViewModal implements ViewModel {

    private Board model;


    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModal() {
        this.model = new Board();
    }

    @Override
    public void onCreate() {
        model = new Board();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }
}
