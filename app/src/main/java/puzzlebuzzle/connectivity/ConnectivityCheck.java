package puzzlebuzzle.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Cassendra on 8/9/2015.
 */
public class ConnectivityCheck {

    public boolean checkWif(Context context) {
        ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = conn.getActiveNetworkInfo();

        return wifi != null && wifi.isConnected();
    }
}
