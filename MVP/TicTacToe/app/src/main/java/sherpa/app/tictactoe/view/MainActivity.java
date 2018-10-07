package sherpa.app.tictactoe.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import sherpa.app.tictactoe.R;
import sherpa.app.tictactoe.model.Board;
import sherpa.app.tictactoe.model.Player;
import sherpa.app.tictactoe.presenter.TicTacToePresenter;

public class MainActivity extends AppCompatActivity implements TicTacToeView {

    private static String TAG = MainActivity.class.getName();

    private Board mModel;

    private ViewGroup mButtonGrid;
    private View mWinnerPlayerContainer;
    private TextView mWinnerPlayerLabel;
    private static final String MEDIA_KEY = "MEDIA";

    private TicTacToePresenter presenter = new TicTacToePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWinnerPlayerLabel = (TextView) findViewById(R.id.winner_player_label);
        mWinnerPlayerContainer = findViewById(R.id.winner_player_conatiner);
        mButtonGrid = (ViewGroup) findViewById(R.id.buttonGrid);

        mModel = new Board();
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);

        outBundle.putParcelable(MEDIA_KEY, mModel);
    }

    public void onCellClicked(View v) {
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        presenter.onButtonSelected(row, col);
    }

    @Override
    public void showWinner(String winningPlayerDisplayLabel) {
        mWinnerPlayerLabel.setText(winningPlayerDisplayLabel);
        mWinnerPlayerContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearWinnerDisplay() {
        mWinnerPlayerContainer.setVisibility(View.GONE);
        mWinnerPlayerLabel.setText("");
    }

    @Override
    public void clearButtons() {
        for (int i = 0; i < mButtonGrid.getChildCount(); i++) {
            ((Button) mButtonGrid.getChildAt(i)).setText("");
        }
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button button = (Button) mButtonGrid.findViewWithTag("" + row + col);
        if (button != null) {
            button.setText(text);
        }
    }
}
