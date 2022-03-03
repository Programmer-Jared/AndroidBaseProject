package com.jared.base.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.jared.base.event.Event;
import com.jared.base.utils.CLog;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
/**
 * @ClassName NetworkManager
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public class NetworkManager {
    private static final String TAG = "NetworkManager";
    private ConnectivityManager connMgr;
    private NetworkRequest request;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NetworkManager(Context context) {
        connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        request = builder.build();
        if (connMgr != null) {
            connMgr.registerNetworkCallback(request, networkCallback);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void unregisterNetworkCallback() {
        if (connMgr != null) {
            connMgr.unregisterNetworkCallback(networkCallback);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            CLog.d(TAG, "onLost: 网络已断开");
            EventBus.getDefault().post(new Event<>(NetworkCode.NET_DISCONNECT));
        }

        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            CLog.d(TAG, "onAvailable: 网络已连接");
            EventBus.getDefault().post(new Event<>(NetworkCode.NET_CONNECT));
        }
        @Override
        public void onCapabilitiesChanged(@NotNull Network network, @NotNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    CLog.d(TAG, "onCapabilitiesChanged: 网络类型为wifi");
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    CLog.d(TAG, "onCapabilitiesChanged: 蜂窝网络");
                } else {
                    CLog.d(TAG, "onCapabilitiesChanged: 其他网络");
                }
            }
        }
    };
}
