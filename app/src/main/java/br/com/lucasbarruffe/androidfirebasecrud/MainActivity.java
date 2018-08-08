package br.com.lucasbarruffe.androidfirebasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.lucasbarruffe.androidfirebasecrud.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_email;
    ListView lv_dados;

    //Objetos de conexão com a Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        lv_dados = findViewById(R.id.lv_dados);

        initializeFirebase();
    }

    private void initializeFirebase(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.new_menu) {
            Pessoa p = new Pessoa();
            p.setUid(UUID.randomUUID().toString());
            p.setNome(et_name.getText().toString());
            p.setEmail(et_email.getText().toString());
            databaseReference.child("Pessoa").child(p.getUid()).setValue(p);
            clearFields();
        }
        return true;
    }

    private void clearFields() {
        et_name.setText("");
        et_email.setText("");
    }
}
