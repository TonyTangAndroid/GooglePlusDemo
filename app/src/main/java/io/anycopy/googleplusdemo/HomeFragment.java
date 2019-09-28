package io.anycopy.googleplusdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private static final String ARG_ID = "arg_id";

    public static HomeFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ID, id);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = Objects.requireNonNull(getArguments()).getInt(ARG_ID);
        Toast.makeText(requireContext(), "id:" + id, Toast.LENGTH_SHORT).show();
    }
}
