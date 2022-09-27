import java.util.ArrayList;

public class Decompression {

	public String Data = new String();
	
	public Decompression() {
		Data = new String();
	}

	public void Extract(ArrayList<String> Dictionary, ArrayList<Tag> tags) {
		for(int i=0; i<tags.size(); i++) {
			int p = Integer.parseInt(tags.get(i).pointer);
			if(p == 0) {
				Data += tags.get(i).NextChar;
				Dictionary.add(tags.get(i).NextChar);
			}
			else {
				String cur = new String();
				Data += Dictionary.get(p);
				Data += tags.get(i).NextChar;
				Data += cur;
				Dictionary.add(cur);
			}
		}
	}

	public String getData() {
		return Data;
	}
}
