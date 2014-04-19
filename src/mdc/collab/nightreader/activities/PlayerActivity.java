package mdc.collab.nightreader.activities;

import mdc.collab.nightreader.R;
import mdc.collab.nightreader.application.NightReader;
import mdc.collab.nightreader.util.AudioFileInfo;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerActivity extends Activity {
	
	private static final String TAG = "PlayerActivity";
	
	private NightReader application;
	
	
	private ImageView albumArtView;
	
	private TextView  songTextView;
	private TextView  albumTextView;
	private TextView  artistTestView;
	
	private Button    toggleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
		this.application = (NightReader) this.getApplication();
		
		
		this.albumArtView   = (ImageView) this.findViewById( R.id.AlbumArtView );
		this.songTextView   = (TextView)  this.findViewById( R.id.SongName );
		this.albumTextView  = (TextView)  this.findViewById( R.id.AlbumName );
		this.artistTestView = (TextView)  this.findViewById( R.id.ArtistName );
		this.toggleButton   = (Button)    this.findViewById( R.id.ToggleButton );
		
		AudioFileInfo curInfo = this.application.getCurAudioFile();
		if( curInfo == null ) {
			Log.e( TAG, "Error not audio currently playing." );
			this.finish();
			return;
		}
		
		this.songTextView.setText( curInfo.getSongTitle() );
		this.albumTextView.setText( curInfo.getAlbumName() );
		this.artistTestView.setText( curInfo.getArtistName() );
		
		this.songTextView.setTypeface( this.application.getApplicationTypeface() );
		this.albumTextView.setTypeface( this.application.getApplicationTypeface() );
		this.artistTestView.setTypeface( this.application.getApplicationTypeface() );
		
		Uri albumArtURI = curInfo.albumArtUri;
		if( albumArtURI == null ) {
			Log.i( TAG, "Album missing art. Using default." );
			
			this.albumArtView.setImageDrawable( this.getResources().getDrawable( R.drawable.notes ) );
		}
		else {
			Log.i( TAG, "Album URI: " + this.application.getCurAudioFile().albumArtUri );
			
			this.albumArtView.setImageURI( albumArtURI );
		}
		
		/*
		 * See comment in ToggleMediaState
		 * 
		 * -- Jacob Smith
		 */
		if( this.application.isMediaPlaying() ) {
			this.toggleButton.setBackgroundResource( R.drawable.eject );
		}
		else {
			this.toggleButton.setBackgroundResource( R.drawable.record );
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
		return true;
	}

	public void ToggleMediaState( View view ) {
		this.application.toggleMediaState();
		
		// TODO
		/*
		 * I suck at art so I reused some textures
		 * 	from previous activities to replace
		 * 	play/pause buttons.
		 * 
		 * -- Jacob Smith
		 */
		if( this.application.isMediaPlaying() ) {
			this.toggleButton.setBackgroundResource( R.drawable.eject );
		}
		else {
			this.toggleButton.setBackgroundResource( R.drawable.record );
		}
	}
}
