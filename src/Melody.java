import java.util.*;


public class Melody 
{
	private Queue<Note> Song;
	public double totalDuration;
	
	//repeat(if it finds only 1 true all the way to the end it will repeat all that comes after the true)
	public Melody(Queue<Note> song)
	{
		if(song == null)
			throw new IllegalArgumentException();
		else
			Song = song;
	}
	
	public double getTotalDuration()
	{
		for(int i = 0; i < Song.size(); i++)
		{
			Note temp = Song.remove();
			totalDuration += temp.getDuration();
			Song.add(temp);
		}
		
		return totalDuration;
	}
	
	//look for sentence structure double(duration), note, octave, accidental, boolean(loop || !loop)
	public String toString()
	{
		String noteInfo = "";
		Note temp;
		for(int i = 0; i < Song.size(); i++)
		{
			temp = Song.remove();
			noteInfo += temp.toString()+ ", ";
			Song.add(temp);
		}
		noteInfo+="\n";
		return noteInfo;
	}
	
	public void changeTempo(double tempo)
	{
		if(tempo < 0)
			throw new IllegalArgumentException();
		else
			for(int i =0; i< Song.size(); i++)
			{
				Note temp = Song.remove();
				double oldDuration = temp.getDuration();
				temp.setDuration(oldDuration / tempo);
				Song.add(temp);
			}
	}
	
	public void reverse()
	{
		Stack<Note> reverse = new Stack<Note>();
		
		int size = Song.size();
		
		for(int i = 0; i < size; i++)
		{
			reverse.add(Song.remove());
			if(Song.isEmpty())
			{
				for(int k = 0; k < size; k++)
				{
					Song.add(reverse.pop());
				}
			}
		}
	}
	
	public void append(Melody other)
	{
		if(other == null)
			throw new IllegalArgumentException();
		else
			for(int i = 0; i < other.Song.size(); i++)
			{
				Note temp = other.Song.remove();
				this.Song.add(temp);
				other.Song.add(temp);
			}
	}
	
	public void play()
	{
		int count = 0;
		Queue<Note> repeat = new LinkedList<Note>();
		
		int size = Song.size();
		for(int i = 0; i < size; i++)
		{
			Note currentNote = Song.remove();
//			if(currentNote.isRepeat())
//			{
//				count += 1;
//				while(count>1)
//				{
//					repeat.add(currentNote);
//				}
//			}
//			else
//			{
				currentNote.play();
//			}
				Song.add(currentNote);
		}
		
	}
	
	//external method for main to use for repeat cases
//	private void repeat(Queue<Note> playing)
//	{
//		boolean isRepeat;
//		boolean inRepeat;
//		if()
//		{
//			isRepeat = true;
//			inRepeat = true;
//			.add(playing);
//		}
//	}
}
