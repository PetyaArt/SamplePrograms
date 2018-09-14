package com.example.petya.criminalintent.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.petya.criminalintent.R;
import com.example.petya.criminalintent.model.CrimeLab;
import com.example.petya.criminalintent.model.PictureUtils;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public class DialogPhotoShowFragment extends DialogFragment {

    private static final String ARG_PHOTO = "photo";

    private ImageView mImageView;
    private File mPhotoFile;

    public static DialogPhotoShowFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, uuid);

        DialogPhotoShowFragment fragment = new DialogPhotoShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UUID uuid = (UUID) getArguments().getSerializable(ARG_PHOTO);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_photo_show, null);
        mImageView = view.findViewById(R.id.imageView);
        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(CrimeLab.get(getContext()).getCrime(uuid));

        Bitmap bitmap = PictureUtils.getScaledBitmap(
                mPhotoFile.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return view;
    }
}
