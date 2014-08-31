package com.app.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.app.ofcampus.FooterActivity;
import com.app.ofcampus.R;
import com.app.utils.AlertMessage.DialogDialogDeligate;
import com.app.utils.AppConstants;
import com.app.utils.AppPreferences;

public class AbstractFragment extends Fragment implements UitilityHelper, AppConstants{

	private UitilityHelperImp uitilityHelperImp = new UitilityHelperImp(); 
	protected Activity activity = getActivity();
	protected Context context = getActivity();
	private TextView txtHelp;
    private TextView txtContactUs;
    private TextView txtLegal;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	activity = getActivity();
    	context = getActivity();
    }

	@Override
	public void switchToActivity(Activity activity,
			Class<? extends Activity> destinationClass, Bundle extras) {
		uitilityHelperImp.switchToActivity(activity, destinationClass, extras);
	}

	@Override
	public void goToActivity(Activity activity,
			Class<? extends Activity> destinationClass, Bundle extras) {
		uitilityHelperImp.goToActivity(activity, destinationClass, extras);
	}

	@Override
	public Typeface createTypeface(Context context, String font) {
		return uitilityHelperImp.createTypeface(context, font);
	}

	@Override
	public void hideKeyboard(Context context, EditText view) {
		uitilityHelperImp.hideKeyboard(context, view);
	}

	@Override
	public DisplayMetrics getDisplayMetrics(Activity activity) {
		return uitilityHelperImp.getDisplayMetrics(activity);
	}

	@Override
	public boolean isNetworkAvailable(Context context) {

		boolean isNetworkAvailable = uitilityHelperImp.isNetworkAvailable(getActivity());
		
		if(!isNetworkAvailable) {
			showAlert(getString(R.string.network_error), getString(R.string.network_error_message), getString(R.string.ok), null, getActivity(), null);
		}
		
		return isNetworkAvailable;
	}

	@Override
	public AppPreferences getSharedPreference(Context context) {
		return uitilityHelperImp.getSharedPreference(context);
	}

	@Override
	public String getDeviceId(Context context) {
		return null;
	}

	@Override
	public int getDisplayWidth(Activity activity) {

		return uitilityHelperImp.getDisplayWidth(activity);
	}

	@Override
	public int getDisplayHeight(Activity activity) {

		return uitilityHelperImp.getDisplayHeight(activity);
	}
	
	@Override
	public void showAlert(String title, String message, String positiveBtn,
			String negativeBtn, Context context, DialogDialogDeligate deligate) {
		uitilityHelperImp.showAlert(title, message, positiveBtn, negativeBtn, context, deligate);
	}
	
	protected String getSessionToken() {
		
		return getSharedPreference(getActivity()).getString(SESSION_TOKEN, "");
	}
	
	protected void setUpFooter(View view) {

        txtHelp = (TextView)view.findViewById(R.id.txt_helpTextView);

        txtContactUs = (TextView) view.findViewById(R.id.txt_contactUsTextView);

        txtLegal = (TextView) view.findViewById(R.id.txt_legalTextView);

        setFooterListeners();
    }

    private void setFooterListeners() {

        txtHelp.setOnClickListener(new FooterLabelsClickListeners());

        txtContactUs.setOnClickListener(new FooterLabelsClickListeners());

        txtLegal.setOnClickListener(new FooterLabelsClickListeners());
    }

    private class FooterLabelsClickListeners implements OnClickListener {

        @Override
        public void onClick(View v) {

            TextView text = (TextView)v;

            int id = text.getId();

            switch (id) {

            case R.id.txt_helpTextView:

                helpClick();

                break;

            case R.id.txt_contactUsTextView:

                contactUsClick();

                break;
            case R.id.txt_legalTextView:

                legalClick();

                break;

            default:
                break;
            }

        }

        private void legalClick() {

            Bundle bundle = new Bundle();

            bundle.putString(FOOTER_TITLE, FOOTER_LEGAL);

            goToActivity(activity, FooterActivity.class, bundle);

        }

        private void contactUsClick() {

            Bundle bundle = new Bundle();

            bundle.putString(FOOTER_TITLE, FOOTER_CONTACT_US);

            goToActivity(activity, FooterActivity.class, bundle);

        }

        public void helpClick() {

            Bundle bundle = new Bundle();

            bundle.putString(FOOTER_TITLE, FOOTER_HELP);

            goToActivity(activity, FooterActivity.class, bundle);

        }

    }
	

	@Override
	public ProgressDialog getLoadingDialog(Context context, String title,
			String message, boolean isCancellable) {
		return uitilityHelperImp.getLoadingDialog(context, title, message, isCancellable);
	}
}
