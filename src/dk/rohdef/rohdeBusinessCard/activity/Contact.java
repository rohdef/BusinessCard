package dk.rohdef.rohdeBusinessCard.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import dk.rohdef.rohdeBusinessCard.DataHelper;
import dk.rohdef.rohdeBusinessCard.R;
import dk.rohdef.rohdeBusinessCard.model.Person;

public class Contact extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.contact);
	    
	    Person person = DataHelper.getInstance().getContactDetails();
	    
	    TextView contactAddress = (TextView) findViewById(R.id.contactAddress);
	    String contactAddressText = getString(R.string.contactAddress);
	    contactAddressText = String.format(contactAddressText,
	    		person.getFirstName(),
	    		person.getLastName());
	    
	    Button callButton = (Button) findViewById(R.id.contactPhoneButton);
	    callButton.setText(person.getPhone());
	    
	    Button emailButton = (Button) findViewById(R.id.contactMailButton);
	    emailButton.setText(person.getEmail());
	    
	    Spanned formattedAddress = Html.fromHtml(contactAddressText);
	    contactAddress.setText(formattedAddress);
	}
	
	public void contactMailClicked(View view) {
		Button emailButton = (Button) view;
		
		Intent callIntent  = new Intent(Intent.ACTION_SENDTO);
		callIntent.setData(Uri.parse("mailto:" + emailButton.getText()));
		startActivity(callIntent);
		
	}
	
	public void contactPhoneClicked(View view) {
		Button callButton = (Button) view;
		
		Intent callIntent  = new Intent(Intent.ACTION_VIEW);
		callIntent.setData(Uri.parse("tel:" + callButton.getText()));
		startActivity(callIntent);
	}
}
