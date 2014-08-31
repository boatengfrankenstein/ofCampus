package com.app.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.app.ofcampus.R;

public class AlertMessage {

	public interface DialogDialogDeligate {
		public void positiveButtonClick(DialogInterface dialog, int id);
		public void negativeButtonClick(DialogInterface dialog, int id);
	}

	public static void showAlert(String title, String message, String positiveBtn, String negativeBtn,Context context, final DialogDialogDeligate deligate) {

		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

		if(title!=null) {

			builder.setTitle(title);
		}

		if(message!=null) {
			builder.setMessage(message);
		}

		if(positiveBtn!=null) {

			builder.setPositiveButton(positiveBtn, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {

					if(deligate!=null) {
						deligate.positiveButtonClick(dialog, id);
					}

				}
			});
		}

		if(negativeBtn!=null) {

			builder.setNegativeButton(negativeBtn, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {

					if(deligate!=null) {
						deligate.negativeButtonClick(dialog, id);
					}
				}
			});
		}

		builder.create();

		builder.show();
	}


	public static void singleChoiceDialog(Context context, String title, String [] items,final SingleChoiceItemListener choiceItemListener) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title)
		.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				choiceItemListener.onItemSelected(which);
			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	public interface SingleChoiceItemListener {
		public void onItemSelected(int which);
	}
}
