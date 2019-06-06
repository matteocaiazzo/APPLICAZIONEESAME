package com.androstock.myweatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class LuoghiActivity extends AppCompatActivity {

    private String URLstring = "http://currcurrwaglio.altervista.org/Json/NuovaCartella/NuovoFile.php";
    private static ProgressDialog mProgressDialog;
    private ArrayList<GoodModel> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luoghi);

        spinner = findViewById(R.id.spCompany);



        retrieveJSON();

    }

    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){

                                goodModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    GoodModel playerModel = new GoodModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setLuogo(dataobj.getString("Luogo"));
                                    playerModel.setOrari(dataobj.getString("Orari"));
                                    playerModel.setTelefono(dataobj.getString("Telefono"));
                                    playerModel.setServizi(dataobj.getString("Servizi"));
                                    playerModel.setSitoWeb(dataobj.getString("SitoWeb"));

                                    goodModelArrayList.add(playerModel);

                                }

                                for (int i = 0; i < goodModelArrayList.size(); i++){
                                    names.add(goodModelArrayList.get(i).getLuogo().toString());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(LuoghiActivity.this, simple_spinner_item, names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spinner.setAdapter(spinnerArrayAdapter);
                                removeSimpleProgressDialog();

                                final TextView sitoweb = (TextView) findViewById(R.id.t_sitoweb_valori);
                                final TextView telefono = (TextView) findViewById(R.id.t_telefono_valori);
                                final TextView servizi = (TextView) findViewById(R.id.t_servizi_valori);
                                final TextView orari = (TextView) findViewById(R.id.t_orari_valore);

                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




                                    @Override
                                    public void onItemSelected (AdapterView < ? > parent, View view, int position,
                                                                long id){

                                        String luogoscelto=spinner.getSelectedItem().toString();


                                        if(luogoscelto.equals ("Capodimonte")) {
                                            orari.setText("07:15 - 19:30");
                                            telefono.setText("0817499111");
                                            servizi.setText("Museo,Bagno,AreaCani");
                                            sitoweb.setText("www.museocapodimonte.beniculturali.it");
                                            Linkify.addLinks(telefono,Linkify.PHONE_NUMBERS);
                                            Linkify.addLinks(sitoweb,Linkify.WEB_URLS);
                                        }

                                        if(luogoscelto.equals ("Virgiliano")) {
                                            orari.setText("07:00 - 20:30");
                                            telefono.setText("08119706082");
                                            servizi.setText("PuntiRistoro,Bagni");
                                            sitoweb.setText("Non disponibile");
                                            Linkify.addLinks(telefono,Linkify.PHONE_NUMBERS);

                                        }


                                        if(luogoscelto.equals ("Mostra d'Oltremare")) {
                                            orari.setText("07:00 - 17:30");
                                            telefono.setText("0817258000");
                                            servizi.setText("PuntiRistoro,Bagni,AreaCani,NoleggioBici");
                                            sitoweb.setText("www.mostradoltremare.it");
                                            Linkify.addLinks(telefono,Linkify.PHONE_NUMBERS);
                                            Linkify.addLinks(sitoweb,Linkify.WEB_URLS);

                                        }


                                        if(luogoscelto.equals ("Pontile di Bagnoli")) {
                                            orari.setText("08:00 - 19:00");
                                            telefono.setText("3314047412");
                                            servizi.setText("Area Passeggio");
                                            sitoweb.setText("Non disponibile");
                                            Linkify.addLinks(telefono, Linkify.PHONE_NUMBERS);
                                            Linkify.addLinks(sitoweb, Linkify.WEB_URLS);
                                        }


                                        if(luogoscelto.equals ("Villa Floridiana")) {
                                            orari.setText("08:30 - 19:00");
                                            telefono.setText("0815788418");
                                            servizi.setText("Area Passeggio");
                                            sitoweb.setText("www.ilparcopiubello.it/index.php/park/dettaglio/447");
                                            Linkify.addLinks(telefono, Linkify.PHONE_NUMBERS);
                                            Linkify.addLinks(sitoweb, Linkify.WEB_URLS);
                                        }

                                    }




                                    @Override
                                    public void onNothingSelected (AdapterView < ? > parent){

                                    }


                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
