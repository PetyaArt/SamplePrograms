package com.example.petya.criminalintent.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.petya.criminalintent.activity.CrimePagerActivity;
import com.example.petya.criminalintent.model.Crime;
import com.example.petya.criminalintent.model.CrimeLab;
import com.example.petya.criminalintent.model.PictureUtils;
import com.example.petya.criminalintent.R;
import com.example.petya.criminalintent.activity.CrimeListActivity;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;
    private static final int REQUEST_CONTACT = 2;
    private static final int REQUEST_PHOTO = 3;

    private Crime mCrime;
    private EditText mTitleField;
    private File mPhotoFile;
    private Button mDateButton;
    private Button mTimeButton;
    private Button mDeleteButton;
    private Button mStartPositionButton;
    private Button mEndPositionButton;
    private CheckBox mSolvedCheckBox;
    private List<Crime> mCrimes;
    private  Button mReportButton;
    private Button mSuspectButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onCrimeUpdated(Crime crime);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    private void updateCrime() {
        CrimeLab.get(getActivity()).updateCrime(mCrime);
        mCallbacks.onCrimeUpdated(mCrime);
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
                updateCrime();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mTimeButton = v.findViewById(R.id.crime_time);
        updateTime();
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                dialog.show(manager, DIALOG_TIME);
            }
        });


        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);
                updateCrime();
            }
        });

        if (mCrimes.get(0).getId().equals(mCrime.getId())) {
            Button button = v.findViewById(R.id.button_start);
            button.setEnabled(false);
        }
        else if (mCrimes.get(mCrimes.size() - 1).getId().equals(mCrime.getId())) {
            Button button = v.findViewById(R.id.button_end);
            button.setEnabled(false);
        }


        mDeleteButton = v.findViewById(R.id.button_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrimeLab.get(getActivity()).deleteCrime(mCrime);
                Intent intent = new Intent(getContext(), CrimeListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });

        mReportButton = v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText(getCrimeReport())
                        .setSubject(getString(R.string.crime_report_subject))
                        .createChooserIntent();
                startActivity(i);
            }
        });


        final Intent pickContact = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        mSuspectButton = v.findViewById(R.id.crime_suspect);
        //pickContact.addCategory(Intent.CATEGORY_HOME);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        if (mCrime.getSuspect() != null) {
            mSuspectButton.setText(mCrime.getSuspect());
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            mSuspectButton.setEnabled(false);
        }

        mPhotoButton = v.findViewById(R.id.crime_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "com.example.petya.criminalintent.fileprovider",
                        mPhotoFile);


                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                List<ResolveInfo> cameraActivities = getActivity()
                        .getPackageManager().queryIntentActivities(captureImage,
                                PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : cameraActivities) {
                    getActivity().grantUriPermission(activity.activityInfo.packageName,
                            uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = v.findViewById(R.id.crime_photo);
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DialogPhotoShowFragment dialog = DialogPhotoShowFragment.newInstance(mCrime.getId());
                dialog.show(manager, DIALOG_TIME);
            }
        });
        updatePhotoView();


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_DATE:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                if (resultCode == Activity.RESULT_OK) {
                    Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                    mCrime.setDate(date);
                    updateCrime();
                    updateDate();
                }
                break;
            case REQUEST_TIME:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                if (resultCode == Activity.RESULT_OK) {
                    Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                    mCrime.setDate(date);
                    updateTime();
                    updateDate();
                }
                break;
            case REQUEST_CONTACT:
                if (data == null)
                    return;

                Uri contactUri = data.getData();
                String[] queryFields = new String[] {
                  ContactsContract.Contacts.DISPLAY_NAME
                };

                Cursor c = getActivity().getContentResolver()
                        .query(contactUri, queryFields, null, null, null);
                try {
                    if (c.getCount() == 0) {
                        return;
                    }

                    c.moveToFirst();
                    String suspect = c.getString(0);
                    mCrime.setSuspect(suspect);
                    updateCrime();
                    mSuspectButton.setText(suspect);
                } finally {
                    c.close();
                }
                break;
            case REQUEST_PHOTO:
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "com.example.petya.criminalintent.fileprovider",
                        mPhotoFile);
                getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                updateCrime();
                updatePhotoView();
                break;
        }
    }

    private void updateTime() {
        mTimeButton.setText(DateFormat.format("k:mm, a", mCrime.getDate()));
    }

    private void updateDate() {
        mDateButton.setText(DateFormat.format("E, MMMM d, yyyy" , mCrime.getDate()));
    }

    private String getCrimeReport() {
        String solvedString = null;
        if (mCrime.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE, MMM, dd";
        String dateString = DateFormat.format(dateFormat, mCrime.getDate()).toString();

        String suspect = mCrime.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }

        String report = getString(R.string.crime_report,
                mCrime.getTitle(), dateString, solvedString, suspect);
        return report;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(mCrime);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

}
