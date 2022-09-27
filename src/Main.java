import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		ArrayList<String> Dictionary  = new ArrayList<>();
		ArrayList<Tag> Tags = new ArrayList<>();;
		String Data = "ABAABABAABABBBBBBBBBBAB";
		System.out.println("Code is: " + Data);
		Compression com = new Compression(Data);
		com.compress();
		com.Print();
		com.StoreDataIntoFile();

		System.out.print("Decompression code is: ");
		Dictionary = com.getDictionary();
		Tags = com.getTags();
		Decompression demo = new Decompression();
		demo.Extract(Dictionary, Tags);
		System.out.println(demo.getData());
	}
}
