package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Book;

/**
 * Imports Books from books.txt
 * The format of the file is one book on each line.
 * The format of a line is "BookName;Author;Year;Weight".
 * The text file needs to be placed in the project root.
 * @author Daniel and Oscar
 */
public class BookImporter {

	/**
	 * Imports the books.
	 * @return A list of imported Books.
	 */
	public static List<Book> ImportBooks(){
		List<Book> books = new ArrayList<Book>();
		
		try{
			File file = new File("books.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] data;
			Book newBook;
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				newBook = new Book(data[0],data[1],data[2],Integer.parseInt(data[3]));
				books.add(newBook);
			}
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return books;
	}
}
