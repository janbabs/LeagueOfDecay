package com.leagueofdecay;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;

import data.DecayHttpClient;
import data.JSONDecayParser;
import model.Match;
import model.Player;

public class MainActivity extends AppCompatActivity {
    String apiKey = "?api_key=";
    //TODO Add apiKey implementation

    private TextView decayInfo;
    private EditText summonerName;
    private Spinner server;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        decayInfo = (TextView) findViewById(R.id.decayText);
        summonerName = (EditText) findViewById(R.id.editSummonerName);
        server = (Spinner) findViewById(R.id.serverSpinner);
        button = (Button) findViewById(R.id.button);

        Log.v("Name",summonerName.getText().toString());

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View v) {

                String sumName = summonerName.getText().toString();
                String serverName = new String();
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
                }


                Log.v("Server", serverName);

                renderSummonerDate(sumName, serverName, apiKey);
            }
        });


    }
    public void renderSummonerDate (String name, String server, String apiKey) {
        SummonerTask summonerTask = new SummonerTask();
        summonerTask.execute(new String[]{name, server, apiKey});

    }

    private class SummonerTask extends AsyncTask <String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {

            Log.v("Summonername: ", params[0]);
            String data = ((new DecayHttpClient()).getSummonerData(params[0], params[1],params[2]));
            Log.v("Data: ", data);
            Player player = JSONDecayParser.getPlayer(data);
            String league = JSONDecayParser.getLeague(new DecayHttpClient().getLeagueById(player.getId(), params[1], params[2]));
            if (!(league.equals("PLATINIUM") || league.equals("DIAMOND") || league.equals("MASTER") || league.equals("CHALLENGER"))) {
                Log.v("Error: ", "below PLAT OR ERROR");
                return -2;
            }

            Log.v("Summonername: ", player.getName());
            String acId = "" + player.getAccountId();
            Log.v("AccountId: ", acId);

            data = ((new DecayHttpClient()).getLastRankedMatchData(player.getAccountId(), params[1], params[2]));
            Log.v("Data: ", data);
            Long matchId = JSONDecayParser.getMatchId(data);
            Log.v("MatchId =", matchId.toString());

            data =  ((new DecayHttpClient()).getMatchDetailsById(matchId,params[1],params[2]));
            Log.v("Data: ", data);
            int timediff = JSONDecayParser.getTimeDiff(data);
            Log.v("Timediff =", String.valueOf(timediff));

            return timediff;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer == -2) {
                decayInfo.setText("Your lp wont decay");
                return;
            }

            if(integer >= 28)
                decayInfo.setText("Decay has already started.");
            else
                decayInfo.setText(28 - integer + " days before decay starts.");
        }
    }

}
