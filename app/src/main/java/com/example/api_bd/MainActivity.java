package com.example.api_bd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etIdCliente, etNombreCliente, etCantidadJugos, etValor, etId;
    Button btnCreate, btnModify, btnDelete, btnShow;
    TextView tvUserInfo;

    RequestQueue requestQueue;

    private static final String URL1 = "http://192.168.10.38/ventas/save.php";
    private static final String URL2 = "http://192.168.10.38/ventas/modify.php";
    private static final String URL3 = "http://192.168.10.38/ventas/delete.php";
    private static final String URL4 = "http://192.168.10.38/ventas/show.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        initUI();
    }

    private void initUI() {
        etIdCliente = findViewById(R.id.etIdCliente);
        etNombreCliente = findViewById(R.id.etNombreCliente);
        etCantidadJugos = findViewById(R.id.etCantidadJugos);
        etValor = findViewById(R.id.etValor);
        etId = findViewById(R.id.etId);
        tvUserInfo = findViewById(R.id.tvUserInfo);

        btnCreate = findViewById(R.id.btnCreate);
        btnModify = findViewById(R.id.btnModify);
        btnDelete = findViewById(R.id.btnDelete);
        btnShow = findViewById(R.id.btnShow);

        btnCreate.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCreate) {
            createUser();
        } else if (v.getId() == R.id.btnModify) {
            modifyUser();
        } else if (v.getId() == R.id.btnDelete) {
            deleteUser();
        } else if (v.getId() == R.id.btnShow) {
            showUsers();
        }
    }

    private void createUser() {
        final String idCliente = etIdCliente.getText().toString().trim();
        final String nombreCliente = etNombreCliente.getText().toString().trim();
        final String cantidadJugos = etCantidadJugos.getText().toString().trim();
        final String valor = etValor.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idCliente", idCliente);
                params.put("nombreCliente", nombreCliente);
                params.put("cantidadJugos", cantidadJugos);
                params.put("valor", valor);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void modifyUser() {
        final String idCliente = etId.getText().toString().trim(); // Obtener el ID del usuario a modificar
        final String nombreCliente = etNombreCliente.getText().toString().trim();
        final String cantidadJugos = etCantidadJugos.getText().toString().trim();
        final String valor = etValor.getText().toString().trim();

        // Crear la solicitud de modificación
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error modifying user", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idCliente", idCliente);
                params.put("nombreCliente", nombreCliente);
                params.put("cantidadJugos", cantidadJugos);
                params.put("valor", valor);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void deleteUser() {
        final String idCliente = etId.getText().toString().trim(); // Obtener el ID del usuario a eliminar

        // Crear la solicitud de eliminación
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error deleting user", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idCliente", idCliente);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void showUsers() {
        String idCliente = etId.getText().toString().trim();
        if(idCliente.isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de cliente", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = URL4 + "?id=" + idCliente;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Añade esta línea para imprimir la respuesta completa en el log
                        Log.d("Response", response);

                        // Verifica si la respuesta es "Connected"
                        if(response.trim().equals("Connected")) {
                            Toast.makeText(MainActivity.this, "Error: " + response, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            if (jsonResponse.has("nombreCliente")) {
                                String nombreCliente = jsonResponse.getString("nombreCliente");
                                tvUserInfo.setText(nombreCliente);
                            } else if (jsonResponse.has("message")) {
                                String message = jsonResponse.getString("message");
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(stringRequest);
    }


}

