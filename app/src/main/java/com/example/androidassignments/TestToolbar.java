package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {

    private static final String TAG = "Toolbar";

    // Message shown by the Snackbar for menu item 1
    private String snackbarMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        snackbarMessage = getString(R.string.item_one_message);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.fab_message), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();

        if (id == R.id.action_one) {
            Log.d(TAG, "Option 1 selected");
            Snackbar.make(findViewById(R.id.main), snackbarMessage, Snackbar.LENGTH_LONG).show();
        } else if (id == R.id.action_two) {
            Log.d(TAG, "Option 2 selected");
            showGoBackDialog();
        } else if (id == R.id.action_three) {
            Log.d(TAG, "Option 3 selected");
            showCustomDialog();
        } else if (id == R.id.action_about) {
            Toast.makeText(this, getString(R.string.about_message), Toast.LENGTH_LONG).show();
        }
        return true;
    }

    // Menu item 2: "Do you want to go back?"
    private void showGoBackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.toolbar_dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled: do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Menu item 3: custom dialog to set a new Snackbar message
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        final EditText input = dialogView.findViewById(R.id.new_message);

        builder.setTitle(R.string.custom_dialog_title);
        builder.setView(dialogView);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String typed = input.getText().toString().trim();
                if (!typed.isEmpty()) {
                    snackbarMessage = typed;
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled: keep the old message
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}