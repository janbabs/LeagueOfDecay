package com.leagueofdecay.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.leagueofdecay.Utils.Utils;

public class DecayHttpClient {

    public String getSummonerData (String name, String server, String apiKey){
        HttpURLConnection connection;
        InputStream inputStream;

        try {
            connection = (HttpURLConnection) (new URL("https://" + server + "." + Utils.SummonerByIdURL + name + apiKey)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

    public String  getLastRankedMatchData (long accountID, String server, String apiKey) {
        HttpURLConnection connection;
        InputStream inputStream;

        try {
            connection = (HttpURLConnection) (new URL("https://" + server + "." + Utils.MatchListByAcIdURL + accountID + apiKey +"&queue=420&beginIndex=0&endIndex=1")).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
        public String getMatchDetailsById (long matchID, String server, String apiKey) {
            HttpURLConnection connection;
            InputStream inputStream;

            try {
                connection = (HttpURLConnection) (new URL("https://" + server + "." + Utils.MatchDetailsByIdURL + matchID + apiKey)).openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                StringBuffer stringBuffer = new StringBuffer();
                inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + "\r\n");
                }

                inputStream.close();
                connection.disconnect();

                return stringBuffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
    public String getLeagueById (long summonerId, String server, String apiKey) {
        HttpURLConnection connection;
        InputStream inputStream;

        try {
            connection = (HttpURLConnection) (new URL("https://" + server + "." + Utils.LeagueByIdURL + summonerId+ apiKey)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

