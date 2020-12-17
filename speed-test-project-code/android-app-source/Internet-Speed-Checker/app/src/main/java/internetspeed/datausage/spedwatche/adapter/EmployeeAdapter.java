package internetspeed.datausage.spedwatche.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import internetspeed.datausage.spedwatche.R;
import internetspeed.datausage.spedwatche.utils.Employee;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    Context mCtx;
    int listLayoutRes;
    List<Employee> employeeList;
    SQLiteDatabase mDatabase;

    public EmployeeAdapter(Context mCtx, int listLayoutRes, List<Employee> employeeList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, employeeList);
        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.employeeList = employeeList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);
        final Employee employee = employeeList.get(position);
        TextView downloadSpeedmeter = view.findViewById(R.id.downloadSpeedmeter);
        TextView slNo = view.findViewById(R.id.slNo);
        TextView dateSpeedmeter = view.findViewById(R.id.dateSpeedmeter);
        TextView uploadSpeedmeter = view.findViewById(R.id.uploadSpeedmeter);
        downloadSpeedmeter.setText(employee.getDownload());
        slNo.setText(String.valueOf(employee.getId()));
        dateSpeedmeter.setText(employee.getDate());
        uploadSpeedmeter.setText(employee.getUpload());
        return view;
    }

}
