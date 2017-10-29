package com.leagueofdecay;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.leagueofdecay.Utils.Utils;

public class MainActivity extends AppCompatActivity {
    private String apiKey;
    private TextView decayInfo;
    private EditText summonerName;
    private Spinner server;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        apiKey = "?api_key=" + this.getString(R.string.LEAGUE_OF_LEGENDS_API_TOKEN);
        decayInfo = (TextView) findViewById(R.id.decayText);
        summonerName = (EditText) findViewById(R.id.editSummonerName);
        server = (Spinner) findViewById(R.id.serverSpinner);
        button = (Button) findViewById(R.id.button);

        TextView.OnEditorActionListener editViewListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    button.callOnClick();
                }
                return true;
            }
        };
        summonerName.setOnEditorActionListener(editViewListener);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String sumName = summonerName.getText().toString();
                String serverName;

                switch (server.getSelectedItemPosition()) {
                    case 0:
                        serverName = "na1";
                        break;
                    case 1:
                        serverName = "euw1";
                        break;
                    case 2:
                        serverName = "eun1";
                        break;
                    default:
                        serverName = "na1";
                }
                renderSummonerDate(sumName, serverName, apiKey);
            }
        });
    }

    public void renderSummonerDate (String name, String server, String apiKey) {
        SummonerTask summonerTask = new SummonerTask();
        summonerTask.execute(new String[]{name, server, apiKey});
    }

    private class SummonerTask extends AsyncTask <String, Void, Integer>{
        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching data");
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
            progressDialog.show();
            decayInfo.setText("");
        }

        @Override
        protected Integer doInBackground(String... params){
            return Utils.TimeDiff(params[0], params[1], params[2]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if(progressDialog.isShowing())
                progressDialog.dismiss();

            if (integer == -2) {
                decayInfo.setText("Your lp wont decay");
            }
            else if (integer == -1) {
                decayInfo.setText("Summoner not found or service is unavailable");
            }
            else if(integer >= 28) {
                decayInfo.setText("Decay has already started.");
            }
            else {
                decayInfo.setText(28 - integer + " days before decay starts.");
            }
        }
    }
}
