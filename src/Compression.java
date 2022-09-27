import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Compression {
	
	public String Data;
    String DictionaryPath = "Dictionary.txt";
    String TagPath = "Tags.txt";
    ArrayList<String> Dictionary = new ArrayList<>();
    ArrayList<Tag> tags = new ArrayList<>();

    public ArrayList<String> getDictionary() {
        return Dictionary;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public Compression() {
    	Data = new String();
        Dictionary.add("\0");
    }

    public Compression(String d) {
    	Data = d;
        Dictionary.add("\0");
    }

    public void compress() {
    	String cur = "";
        int DictionaryIndex = 0;
        for(int i=0; i<Data.length(); i++) {
        	char currentCharacter = Data.charAt(i);
            cur += currentCharacter;
            int check = SearchIndex(cur);

            if(check == -1) {
                Tag tag = new Tag();
                tag.pointer += Integer.toString(DictionaryIndex);
                tag.NextChar += Data.charAt(i);
                tags.add(tag);
                Dictionary.add(cur);
                DictionaryIndex = 0;
                cur = "";
            }
            else if(i == Data.length()-1) {
                Tag tag = new Tag();
                tag.pointer += DictionaryIndex;
                tag.NextChar += '\0';
                tags.add(tag);
                
            }
            else if(check > -1) {
                DictionaryIndex = check;
            }
        }
    }

    int SearchIndex(String cur) {
        for(int i=0; i<Dictionary.size(); i++) {
            if((Dictionary.get(i).compareTo(cur)) == 0)
            	return i;
            }
        return -1;
        }

    public void Print() {
        System.out.print("Dictoinary is: ");
        for(int i=0; i<Dictionary.size(); i++) {
            System.out.println(Dictionary.get(i));
        }
        System.out.println("Tags are: ");
        for(int i=0; i<tags.size(); i++) {
            System.out.println("<" + tags.get(i).pointer + "," + tags.get(i).NextChar + ">");
        }
    }

    public void StoreDataIntoFile() {
        FileWriter DictionaryFile = null;
        FileWriter TagsFile = null;
        BufferedWriter DictionaryBuffer = null;
        BufferedWriter TagsBuffer = null;
        try {
        	DictionaryFile = new FileWriter(new File(DictionaryPath));
        	DictionaryBuffer = new BufferedWriter(DictionaryFile);
            TagsFile = new FileWriter(new File(TagPath));
            TagsBuffer = new BufferedWriter(TagsFile);
            
            for(int i=0; i<Dictionary.size(); i++) {
            	DictionaryBuffer.write(Dictionary.get(i) + "\n");
            	}
            DictionaryBuffer.close();
            
            for(int i=0; i<tags.size(); i++) {
            	TagsBuffer.write(tags.get(i).pointer + "," + tags.get(i).NextChar +  "\n");
            	}
            TagsBuffer.close();
            }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
