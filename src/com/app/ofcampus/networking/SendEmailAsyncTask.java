package com.app.ofcampus.networking;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.app.ofcampus.R;
import com.app.utils.AppConstants;
import com.app.utils.AppPreferences;
import com.app.utils.UiUitils;

public class SendEmailAsyncTask extends AsyncTask<Void, Void, String> implements AppConstants{

	private String email;
	private Context context;
	private SendEmailDeligate emailDeligate; 
	private ProgressDialog progressDialog;
	public SendEmailAsyncTask(String email, Context context, SendEmailDeligate emailDeligate) {
		this.email = email;
		this.context = context;
		this.emailDeligate = emailDeligate;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		progressDialog = UiUitils.getLoadingDialog(context,null, context.getString(R.string.loading_), false);
		progressDialog.show();
	}

	@Override
	protected String doInBackground(Void... params) {

		Session session = createSessionObject();
		String subject = "ofCampus : Authetication";
		int number  = generateRandomNumber();
		saveVarificationNumber( number, context );
		Message message = null;
		String messageBody = "Verification ID : " + number ;
		
		try {
			message = createMessage( email, subject, messageBody, session ) ;
		}catch (AddressException e) {
			e.printStackTrace();
		}  catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		try{
            Transport.send(  message );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		if(progressDialog!=null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
		
		if(emailDeligate!=null) {
			emailDeligate.onEmailSend();
		}
	}

	private Session createSessionObject() 
	{
		Properties properties = new Properties();
		properties.put( "mail.smtp.auth", "true" );
		properties.put( "mail.smtp.starttls.enable", "true" );
		properties.put( "mail.smtp.host", "smtp.gmail.com" );
		properties.put( "mail.smtp.port", "587" );

		return Session.getInstance(properties, new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication( "testmobintia@gmail.com", "mobintia2014" );
			}
		});
	}

	private Message createMessage( String email, String subject, String messageBody, Session session ) throws MessagingException, UnsupportedEncodingException 
	{
		Message message = new MimeMessage( session );
		message.setFrom(new InternetAddress( "testmobintia@gmail.com", "mobintia2014" ) );
		message.addRecipient( Message.RecipientType.TO, new InternetAddress( email, email ) );
		message.setSubject( subject );
		message.setText( messageBody );
		return message;
	}
	
	private int generateRandomNumber()
	{
		int min = 1000;
		int max = 9999;
		Random random = new Random();
		int randomNumber = random.nextInt(max - min + 1) + min;
		return randomNumber;
	}

	private void saveVarificationNumber(int number, Context context) {
		AppPreferences.getAppPreferences(context).putInt(VERIFICATION_CODE, number);
	}
	
	public interface SendEmailDeligate {
		public void onEmailSend();
	}
}
