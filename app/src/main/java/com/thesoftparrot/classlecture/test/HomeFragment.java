package com.thesoftparrot.classlecture.test;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        click();
    }

    private void click() {
        mBinding.simpleAlertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleAlertDialog();
            }
        });

        mBinding.twoBtnsAlertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwoButtonsAlertDialog();
            }
        });

        mBinding.threeBtnsAlertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThreeButtonsAlertDialog();
            }
        });

        mBinding.inputNameAlertDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputAlertDialog();
            }
        });
    }

    private void showInputAlertDialog() {
        if(getContext() != null){
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setCancelable(false);

            final View view = LayoutInflater.from(getContext()).inflate(R.layout.designed_dialog_box_layout,null);
            builder.setView(view);

            final EditText firstName_ET = view.findViewById(R.id.first_name_et);
            Button sayHiButton = view.findViewById(R.id.say_hi_btn);

            final AlertDialog dialog = builder.create();
            dialog.show();

            sayHiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String firstName = firstName_ET.getText().toString().trim();

                    if(TextUtils.isEmpty(firstName)){
                        Toast.makeText(getContext(), "Please enter your first name!", Toast.LENGTH_SHORT).show();
                    }else {
                        String hello = "Hi, "+firstName;
                        mBinding.sayHelloTv.setText(hello);

                        dialog.dismiss();
                    }

                }
            });

        }
    }

    private void showThreeButtonsAlertDialog() {
        if(getContext() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setCancelable(false);

            builder.setTitle("Rate Our App!");
            builder.setMessage("Would you like to rate our app?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Rated!", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNeutralButton("Maybe Later", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Thanks! We'll wait for you.", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void showTwoButtonsAlertDialog() {
        if(getContext() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setCancelable(false);

            builder.setTitle("Confirm Delete");
            builder.setMessage("You really wanna delete this item?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Item Deleted!", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    // Shows simple alert dialog with one OK Button
    private void simpleAlertDialog() {
        if(getContext() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setCancelable(false);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure to close this dialog?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

}