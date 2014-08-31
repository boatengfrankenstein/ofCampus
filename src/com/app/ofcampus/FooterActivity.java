package com.app.ofcampus;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.app.offcampus.fragments.ContactUsFragment;
import com.app.offcampus.fragments.HelpFragment;
import com.app.offcampus.fragments.LegalFragment;
import com.app.ui.AbstractFragmentActivity;

public class FooterActivity extends AbstractFragmentActivity {


	private Bundle bundle;

	private String footerType;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_container);

//		initUI();

		getBundleExtras();

		addFragmentsToView();

	}

	private void addFragmentsToView() {

		FragmentManager fragmentManager = getSupportFragmentManager();

		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		if(footerType.equals(FOOTER_HELP)) {
			
			fragmentTransaction.add(R.id.ln_layout_fragment_container,HelpFragment.newInstance());
			
		}else if(footerType.equals(FOOTER_LEGAL)) {
			
			fragmentTransaction.add(R.id.ln_layout_fragment_container,LegalFragment.newInstance());
			
		}else if(footerType.equals(FOOTER_CONTACT_US)) {
			
			fragmentTransaction.add(R.id.ln_layout_fragment_container,ContactUsFragment.newInstance());
			
		}
		
		fragmentTransaction.commit();
	}

	private void getBundleExtras() {

		bundle = getIntent().getExtras();

		if(bundle!=null) {

			footerType = bundle.getString(FOOTER_TITLE);

		}

	}
}
