package colomind.com.myownzhihu.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/28.
 */

public class Utility  {
    public static boolean checkNetworkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public static void NoNetworkWarning(Context context){
        Toast.makeText(context,"Network is not available!",Toast.LENGTH_SHORT).show();
    }


}
