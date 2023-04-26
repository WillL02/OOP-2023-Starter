package ie.tudublin;
import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows;

    
    /**
     * @return the word
     */
    public String getWord() {
        return word;
    } // end method
    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    } // end method
    /**
     * @return the follows
     */
    public ArrayList<Follow> getFollows() {
        return follows;
    } // end method
    /**
     * @param follows the follows to set
     */
    public void setFollows(ArrayList<Follow> follows) {
        this.follows = follows;
    } // end method
    public void addFollowCount(Follow f)
    {
        f.setCount(f.getCount()+1);
    } // end method
    public void addFollow(Follow f)
    {
        follows.add(f);
    } // end method
   

    public int findFollow(String word)
    {
        for(Follow f:follows)
        {
            if(f.getWord().equals(word))
            {
                return follows.indexOf(f);
            } // end if
        } // end for
        return -1;
    } // end findFollow method


    public Word(String word) {
        this.word = word;
        follows = new ArrayList<Follow>();
    } // end word method
    
    public String toString()
    {
        String str = " ";
        str += word + ":";
        for(Follow word:follows) {
            str +=" "+word.toString();
        } // end for
        return str;
    } // end toString
} // End Word Class
