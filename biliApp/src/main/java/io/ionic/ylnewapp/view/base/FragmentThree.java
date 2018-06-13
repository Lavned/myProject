package io.ionic.ylnewapp.view.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.net.URISyntaxException;

import io.ionic.ylnewapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_three)
public class FragmentThree extends BaseFragment {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.xxx.xxx:xxxx");//ip与端口
        } catch (URISyntaxException e) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initSockte();
        return view;

    }

    private void initSockte() {
        mSocket.on("ticker", onNewMessage);
        mSocket.connect();
    }
//
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if(getActivity()==null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String jj ;
                    try {
                        jj = data.getString("close");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            });
        }

    };

    @Override
    public void onDestroyView() {
        Log.i("socketlog","我关啦");
        super.onDestroyView();
        if(mSocket!=null){
            mSocket.disconnect();
            mSocket.off("ticker", onNewMessage);
        }
    }
}

