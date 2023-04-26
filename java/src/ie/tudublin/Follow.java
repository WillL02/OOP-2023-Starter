package ie.tudublin;

public class Follow {

    public String word;
    public int count;

   

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    public Follow(String word,int count)
    {
        this.word=word;
        this.count=count;
    }

    public String toString()
    {
        return word +"["+ count+"]";
    }
}
