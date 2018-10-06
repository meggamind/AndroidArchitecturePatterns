package sherpa.app.tictactoe.controller;

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

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();

    private Board mModel;

    private ViewGroup mButtonGrid;
    private View mWinnerPlayerContainer;
    private TextView mWinnerPlayerLabel;
    private static final String MEDIA_KEY = "MEDIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWinnerPlayerLabel = (TextView) findViewById(R.id.winner_player_label);
        mWinnerPlayerContainer = findViewById(R.id.winner_player_conatiner);
        mButtonGrid = (ViewGroup) findViewById(R.id.buttonGrid);

        mModel = new Board();


    }


    // TODO: retain model after rotate
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Board mModel = savedInstanceState.getParcelable(MEDIA_KEY);
//        System.out.println("mModel: " + mModel.)
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
                reset();
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

        Player playerThatMoved = mModel.mark(row, col);

        if (playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (mModel.getWinner() != null) {
                mWinnerPlayerLabel.setText(playerThatMoved.toString());
                mWinnerPlayerContainer.setVisibility(View.VISIBLE);
            }
        }
    }

    private void reset() {
        mWinnerPlayerContainer.setVisibility(View.GONE);
        mWinnerPlayerLabel.setText("");

        mModel.restart();

        for (int i = 0; i < mButtonGrid.getChildCount(); i++) {
            ((Button) mButtonGrid.getChildAt(i)).setText("");
        }
    }
}
