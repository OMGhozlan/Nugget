package threadcount.nugget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceService extends Service {
    private static final String name = "ServiceService";
    private boolean started  = false;

    @Override
    public void onCreate() {
        Log.i(name, "Service onCreate");
        started = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(name, "Service onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    if(started){
                        Log.i(name, "Service running");
                    }
                }
                stopSelf();
            }
        }).start();
        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(name, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        started = false;
        Log.i(name, "Service onDestroy");
    }
}
