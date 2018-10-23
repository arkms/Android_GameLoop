package arjierdagames.gameloop;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	static private SoundManager _instance;
	private static SoundPool mSoundPool;

	private static HashMap mSoundPoolMap;
	private static AudioManager mAudioManager;
	private static Context mContext;
	
	private SoundManager(){}
	
	static synchronized public SoundManager getInstance()
	{
		if (_instance==null)
			_instance= new SoundManager();
		return _instance;
	}
	

	public static void initSounds(Context theContext, int numAudios)
	{
		mContext= theContext;
		mSoundPool= new SoundPool(numAudios, AudioManager.STREAM_MUSIC, 0);
		mSoundPoolMap= new HashMap();
		mAudioManager= (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
	}
	

	public static void loadSoundsIntro()
	{
		if (!mSoundPoolMap.isEmpty())
		{
			//mSoundPoolMap.remove(1); //liberar
			//mSoundPoolMap.remove(2); //Liberar
		}
		//mSoundPoolMap.put(4, mSoundPool.load(mContext, R.raw.start_game, 1));
	}
	

	public static void loadSounds2()
	{
		//mSoundPoolMap.remove(4); //Numero de sonidos
		              //id,                       //ID sonido
		//mSoundPoolMap.put(1, mSoundPool.load(mContext, R.raw.click_bad, 1));
		//mSoundPoolMap.put(2, mSoundPool.load(mContext, R.raw.click_good, 1));
	}
	
	public static void playSound(int index, float speed)
	{
		float streamVolume= mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume= streamVolume/mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		mSoundPool.play((Integer)mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, speed);
	}
	
	public static void stopSound(int index)
	{
		mSoundPool.stop((Integer)mSoundPoolMap.get(index));
	}
	
	public static void cleanup()
	{
		mSoundPool.release();
		mSoundPool= null;
		mSoundPoolMap.clear();
		mAudioManager.unloadSoundEffects();
		_instance= null;
	}
}
