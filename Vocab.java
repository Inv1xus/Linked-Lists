package comp249_A3;
//---------------------------------------------------------
//Assignment 3
//
//Written by: Tony Awaad 40236201
//---------------------------------------------------------
public class Vocab {
	SinglyLinkedList<String> words =  new SinglyLinkedList<>();
	String topic;
	
	public Vocab (String topic) {
		this.topic = topic;
	}
	public Vocab (String topic, SinglyLinkedList<String> words) {
		this.topic = topic;
		this.words = words; 
	}
	
	public void addWord(String word){
		words.add(word);
		
	}
	public void removeWord(String word){
		words.delete(word);
		
	}
	public void changeWord(String word, String newWord) {
		int index = words.getIndexOf(word);
		words.delete(word);
		words.addAt(newWord, index);
		
		
	}
	public boolean hasWord(String word) {
		return words.hasElement(word);
		
	}
//	public void PrintSorted() {
//		ArrayList<String> sortWords = new ArrayList<>();
//		for (int i =0;i<words.size();i++) {
//			sortWords.add(words.getElement(i));
//		}
//		
//		Collections.sort(sortWords);
//		int counter = 0;
//		for(String word:sortWords) {
//			counter++;
//			System.out.printf( "%-30s",counter+": "+word);//prints formatted
//			if((counter) % 4 == 0) {
//                System.out.println();
//            }
//		}
//	}
	
	
	public void printSorted() {
		SinglyLinkedList<String> sortedWords = new SinglyLinkedList<>();
		//copying words into new arrayList
		for (int i =0; i<words.size();i++) {
			sortedWords.add(words.getElement(i));
			}

		//sorting
		for (int i =0; i<sortedWords.size();i++) {
			for (int j =i+1;j<sortedWords.size();j++) {
			if(sortedWords.getElement(j).compareTo(sortedWords.getElement(i))<0) {
				String temp = sortedWords.getElement(i);
                sortedWords.set(i, sortedWords.getElement(j));
                sortedWords.set(j, temp);
			}
				
				}
			
		}
		
		
		//printing
		if (sortedWords.size()==0) {
			System.out.println("empty topic");
		}
		int counter = 0;
		for(int i=0;i<sortedWords.size();i++) {
			counter++;
			System.out.printf( "%-30s",counter+": "+sortedWords.getElement(i));//prints formatted
			if((counter) % 4 == 0) {
                System.out.println();
            }
		}
	}
	
	public int size() {
		return words.size();
	}
	
}
