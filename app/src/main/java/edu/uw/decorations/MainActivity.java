package edu.uw.decorations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //show fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, WordListFragment.newInstance());
        ft.commit();
    }

    //toggles the action bar
    public void handleButton(View v) {

        ActionBar toolbar = getSupportActionBar();
        if (toolbar.isShowing())
            toolbar.hide();
        else
            toolbar.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
//        super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.hello_menu_item:

                new HeloDialogFragment().show(getSupportFragmentManager(), null);

                return true;
            case R.id.hi_menu_item:

                Toast.makeText(this, "Hi!", Toast.LENGTH_LONG)
                        .show();

                return true;
            case R.id.add_menu_item:
                Log.v(TAG, "ADD!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class HeloDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle("Hello!")
                    .setMessage("Hello world, I am a dialog!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.v(TAG, "You clicked ok! Thanks! :)");
                        }
                    });
            AlertDialog dialog = builder.create();

            return dialog;
        }
    }
}
