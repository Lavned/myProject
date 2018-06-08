package io.ionic.ylnewapp.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiangyy.easydialog.LoadingDialog;

import io.ionic.ylnewapp.R;



/**
 * Created by Kevin on 2016/11/20.
 * Blog:http://blog.csdn.net/student9128
 * Describe：the BaseFragment
 */

public class BaseFragment extends Fragment {


    Context context;

//    public LoadingDialog.Builder mBuilder = new LoadingDialog.Builder(getActivity());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        context = getActivity();

        return view;

    }
}
