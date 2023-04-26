package ie.tudublin;

public class Follow {

    public String word;
    public int count;

   

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
    }// end method

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }// end method

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }// end method

    public Follow(String word,int count)
    {
        this.word=word;
        this.count=count;
    }// end method

    public String toString()
    {
        return word +"["+ count+"]";
    }// end method
}
