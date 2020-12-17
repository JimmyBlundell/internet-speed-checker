package internetspeed.datausage.spedwatche.receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import internetspeed.datausage.spedwatche.service.DataService;

public class DataReceiver extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Intent intentService = new Intent(context, DataService.class);
        context.startForegroundService(intentService);
      } else {
        Intent intentService = new Intent(context, DataService.class);
        context.startService(intentService);
      }
    }


}
