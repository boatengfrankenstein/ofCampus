package com.app.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

@SuppressLint("ValidFragment")
public class AlertDialogFragment extends DialogFragment {

	public interface DialogFragmentDeligate {
		
		public void positiveButtonClick(DialogInterface dialog, int id);
		
		public void negativeButtonClick(DialogInterface dialog, int id);
	}


	private String positiveBtn;
	
	private String negativeBtn;
	
	private DialogFragmentDeligate dialogFragmentDeligate;
	
	private String message;
	
	private String title;

	public AlertDialogFragment(String message, String title, String positiveBtn, String negativeBtn,DialogFragmentDeligate dialogFragmentDeligate) {
	
		this.message = message;
		
		this.title = title;
		
		this.positiveBtn = positiveBtn;
		
		this.negativeBtn = negativeBtn;
		
		this.dialogFragmentDeligate = dialogFragmentDeligate;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		if(title!=null) {
			builder.setTitle(title);
		}

		if(message!=null) {
			builder.setMessage(message);
		}

		if(positiveBtn!=null) {
		
			builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int id) {
					
					if(dialogFragmentDeligate!=null) {
					
						dialogFragmentDeligate.positiveButtonClick(dialog, id);
					}

				}
			});
		}

		if(negativeBtn!=null) {

			builder.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int id) {
					
					if(dialogFragmentDeligate!=null) {
						
						dialogFragmentDeligate.negativeButtonClick(dialog, id);
					}

				}
			});
		}

		return builder.create();
	}

}
