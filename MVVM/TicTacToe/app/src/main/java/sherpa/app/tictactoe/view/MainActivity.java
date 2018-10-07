package sherpa.app.tictactoe.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import sherpa.app.tictactoe.databinding.ActivityMainBinding;
import sherpa.app.tictactoe.R;

import sherpa.app.tictactoe.viewmodel.TicTacToeViewModal;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName();
    TicTacToeViewModal viewModal = new TicTacToeViewModal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModal);
        viewModal.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModal.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModal.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModal.onDestroy();
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
                viewModal.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
