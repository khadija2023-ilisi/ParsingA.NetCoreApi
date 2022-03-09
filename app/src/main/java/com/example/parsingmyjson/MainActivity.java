package com.example.parsingmyjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url = "http://10.0.2.2:45455/api/etudiants";
    private ListView lv;
    ArrayList<Etudiant> mesEtu =new ArrayList<>();
    CustonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lst);
        new MySiteAPI().execute(url);

    }
    public class MySiteAPI extends AsyncTask<String, Void, List<Etudiant>>
    {
        public String ConvertStream2String (InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = null;
            String str;

            br = new BufferedReader (new InputStreamReader(is));

            while ((str = br.readLine()) != null)
                sb.append(str);

            return sb.toString();
        }

        @Override
        protected List<Etudiant> doInBackground(String... strings)
        {
            String urlStr = strings[0];
            String rssStr;

            try {
                URL url = new URL(urlStr);
                HttpURLConnection cnx = (HttpURLConnection) url.openConnection();

                rssStr = ConvertStream2String(cnx.getInputStream());
                //parsing

                JSONArray LivreLArr = new JSONArray(rssStr);

                for(int index = 0; index < LivreLArr.length(); index++)
                {
                    JSONObject LivreObj = LivreLArr.getJSONObject(index);
                    Etudiant livre = new Etudiant(0,
                            LivreObj.getString("prenom"),
                            LivreObj.getString("nom"),
                            LivreObj.getString("image")
                    );
                    mesEtu.add(livre);
                }
                adapter= new CustonAdapter(getApplicationContext(),mesEtu);
                lv.setAdapter(adapter);

            } catch (MalformedURLException | JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return mesEtu;
        }

        protected void onPostExecute(List<Etudiant> result)
        {
            lv = (ListView) findViewById(R.id.lst);
            List<String> txt  = new ArrayList<String>();
            for(Etudiant l: result) {
                txt.add((String) l.print());
                Log.i("tag1",l.toString());
            }
            adapter= new CustonAdapter(getApplicationContext(),mesEtu);
            lv.setAdapter(adapter);
        }
    }

}