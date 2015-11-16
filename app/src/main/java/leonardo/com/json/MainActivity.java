package leonardo.com.json;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    ProgressBar barr;
    TextView name;
    String []objetos = new String[3];
    String id = "London";
    String URL1 = "api.openweathermap.org/data/2.5/weather?q=" + id;
    JSONObject jsonObjectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTaskExample().execute(URL1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public class AsyncTaskExample extends AsyncTask<String, String, String[]> {


        @Override
        protected void onPreExecute() {
            barr = (ProgressBar) findViewById(R.id.progressBar);
            barr.setVisibility(View.VISIBLE);

        }


        @Override
        protected String[] doInBackground(String... url) {
            try {
                jsonObjectText = JsonParser.readJsonFromUrl(url[0]);
                System.out.println(url[0]);
                objetos[0] = jsonObjectText.getJSONObject("coord").getString("lon");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return objetos;
        }


        @Override
        protected void onPostExecute(String[] stringFromDoInBackground) {
            name = (TextView)findViewById(R.id.textViewName);

            name.setText("Coord"+stringFromDoInBackground[0]);

            barr = (ProgressBar)findViewById(R.id.progressBar);
            barr.setVisibility(View.GONE);

        }
    }
}
