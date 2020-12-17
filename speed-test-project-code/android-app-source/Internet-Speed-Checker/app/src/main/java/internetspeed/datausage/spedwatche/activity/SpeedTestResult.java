package internetspeed.datausage.spedwatche.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import internetspeed.datausage.spedwatche.R;
import internetspeed.datausage.spedwatche.adapter.EmployeeAdapter;
import internetspeed.datausage.spedwatche.utils.Employee;

public class SpeedTestResult extends AppCompatActivity {

    List<Employee> employeeList;
    SQLiteDatabase mDatabase;
    ListView listViewEmployees;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_test_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewEmployees = (ListView) findViewById(R.id.listViewEmployees);
        employeeList = new ArrayList<>();
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(adRequest);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        //opening the database
        mDatabase = openOrCreateDatabase(SpeedTestActivity.DATABASE_NAME, MODE_PRIVATE, null);
        showEmployeesFromDatabase();
    }
    private void showEmployeesFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM employees", null);

        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new Employee(
                        cursorEmployees.getInt(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3)
                ));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        //creating the adapter object
        adapter = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList, mDatabase);

        //adding the adapter to listview
        listViewEmployees.setAdapter(adapter);
    }
}
