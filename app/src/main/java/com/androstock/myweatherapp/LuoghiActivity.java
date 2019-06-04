package com.androstock.myweatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LuoghiActivity extends Activity {


    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_luoghi);


        final TextView sitoweb = (TextView) findViewById(R.id.t_sitoweb_valori);
        final TextView telefono = (TextView) findViewById(R.id.t_telefono_valori);
        final TextView servizi = (TextView) findViewById(R.id.t_servizi_valori);
        final TextView orari = (TextView) findViewById(R.id.t_orari_valore);

        final Spinner spinnerDoc = (Spinner) findViewById(R.id.sp_luoghi);
        final String[] arraySpinner = new String[]{
                "Capodimonte", "Virgiliano", "MostraOltremare", "PontileDiBagnoli", "VillaFloridiana"
        };

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoc.setAdapter(spin_adapter);
        spinnerDoc.setSelection(0);
        spinnerDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position,
                                        long id){

                String luogoscelto=spinnerDoc.getSelectedItem().toString();


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


                if(luogoscelto.equals ("MostraOltremare")) {
                    orari.setText("07:00 - 17:30");
                    telefono.setText("0817258000");
                    servizi.setText("PuntiRistoro,Bagni,AreaCani,NoleggioBici");
                    sitoweb.setText("www.mostradoltremare.it");
                    Linkify.addLinks(telefono,Linkify.PHONE_NUMBERS);
                    Linkify.addLinks(sitoweb,Linkify.WEB_URLS);

                }


                if(luogoscelto.equals ("PontileDiBagnoli")) {
                    orari.setText("08:00 - 19:00");
                    telefono.setText("3314047412");
                    servizi.setText("Area Passeggio");
                    sitoweb.setText("Non disponibile");
                    Linkify.addLinks(telefono, Linkify.PHONE_NUMBERS);
                    Linkify.addLinks(sitoweb, Linkify.WEB_URLS);
                }


                if(luogoscelto.equals ("VillaFloridiana")) {
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
}