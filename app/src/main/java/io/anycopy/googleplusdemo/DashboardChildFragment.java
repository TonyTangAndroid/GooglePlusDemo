package io.anycopy.googleplusdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class DashboardChildFragment extends Fragment {

    private static final String ARGUMENT_POSITION = "argument_position";
    private static final String ARGUMENT_NAME = "argument_name";

    public static DashboardChildFragment newInstance(int position, String name) {

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        args.putString(ARGUMENT_NAME, name);
        DashboardChildFragment fragment = new DashboardChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_child, container, false);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvDashBoard = view.findViewById(R.id.tv_dashboard);
        int position = Objects.requireNonNull(getArguments()).getInt(ARGUMENT_POSITION, -1);
        String name = Objects.requireNonNull(getArguments()).getString(ARGUMENT_NAME);
        tvDashBoard.setText(position == 0 ? R.string.do_not_stop_believing : R.string.life_is_a_very_funny_proposition_after_all);
        tvDashBoard.append("\n");
        tvDashBoard.append("\n");
        tvDashBoard.append("\n");
        tvDashBoard.append(name);
    }
}
