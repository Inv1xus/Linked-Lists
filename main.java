package comp249_A3;
//---------------------------------------------------------
//Assignment 3
//
//Written by: Tony Awaad 40236201
//---------------------------------------------------------
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The main class for controlling vocabulary topics.
 * This Java program serves as a Vocabulary Control Center, allowing users to manage and manipulate vocabulary 
 * topics and their associated words. It provides functionalities such as browsing topics, adding, removing,
 *  and modifying topics and words, searching for words within topics, loading topics and words from a file, 
 *  and saving topics and words to a file. The program utilizes a Doubly Linked List and SinglyLinkedList data structure to organize 
 *  topics and their respective words efficiently. 
 */
public class main {
	static DoublyLinkedList<Vocab> topics = new DoublyLinkedList<>();
	static Scanner input = new Scanner(System.in);

	
	/**
     * The main method to run the program.
     *
     * @param args The command line arguments.
     */
	public static void main(String[] args) {
		

		int choice = 0;
		do {
			try {
				printMenu();

				choice = input.nextInt();
				input.nextLine();

				if (choice == 1) {
					browseATopic();
				} else if (choice == 2) {
					addTopicBefore();

				} else if (choice == 3) {
					addTopicAfter();

				} else if (choice == 4) {
					removeTopic();

				} else if (choice == 5) {
					modifyTopic();

				} else if (choice == 6) {
					searchWord();

				} else if (choice == 7) {
					readFile();

				} else if (choice == 8) {
					showAllWordsWithLetter();

				} else if (choice == 9) {
					saveToFile();
				} else if (choice == 0) {
					break;
				} else {
					System.out.println("\n invalid number \n");
				}
			} catch (Exception e) {
				System.out.println("\n Invalid input, try again!! \n");
				input.nextLine();
			}

		} while (true);
		System.out.println("GoodBye!");

	}
	/**
     * Saves topics and words to a file.
     */
	private static void saveToFile() {

		PrintWriter pw = null;
		String line;
		Vocab vocab = null;
		System.out.println("Please input file name you want to save to: ");
		String name = input.next();
		try {
			pw = new PrintWriter(new FileWriter("./src/comp249_A3/"+name));
			System.out.println("File Successfully saved.");
			for (int i=0;i<topics.size();i++) {
				pw.println(topics.getElement(i).topic);
				
				for (int j=0;j<topics.getElement(i).size();j++) {
					pw.println(topics.getElement(i).words.getElement(j));
				}
				pw.println();
			}
			pw.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("file not found");
		}
		
	}

	/**
     * Shows all words starting with a given letter.
     */
	private static void showAllWordsWithLetter() {
		System.out.println("Type letter you want to search:");
		String letter = input.nextLine();
		ArrayList <String> words = new ArrayList<>();
		
		
			for (int i =0;i<topics.size();i++) {
				for (int j =0; j<topics.getElement(i).size();j++) {
					if (topics.getElement(i).words.getElement(j).toLowerCase().startsWith(letter.toLowerCase())) {
						
						words.add(topics.getElement(i).words.getElement(j));
					
		            }
					}
					
			}
			words.sort(null);
			int counter = 0;
			for (int i=0; i<words.size();i++) {
				counter++;
			System.out.printf( "%-30s",counter+": "+words.get(i));//prints formatted
			if((counter) % 4 == 0) {
                System.out.println();
			
			}
			}
		
	}

	
	 /**
     * Searches for a word in all topics.
     */
	private static void searchWord() {
		System.out.println("Type a word and press Enter, or press Enter to end input");
		String word = input.nextLine();
		if (!word.trim().isEmpty()) {
			for (int i =0;i<topics.size();i++) {
				if (topics.getElement(i).hasWord(word)) {
					System.out.println("Word is in this topic: "+topics.getElement(i).topic);
					return;
			}
			}
			System.out.println("Word is not found in any topic");
			}
			 else {
				 System.out.println("invalid input");
		}
		
	}
	/**
     * Modifies a topic: adds, removes, or changes words.
     */
	private static void modifyTopic() {
		int choice = 0;
		do {
			System.out.println(
					"\n------------------------------ \n" + " Pick a topic \n" + "------------------------------");

			// prints the topics and ignores the first character '#'
			for (int i = 0; i < topics.size(); i++) {
				System.out.println(
						i + 1 + " " + (topics.getElement(i).topic).substring(1, (topics.getElement(i).topic).length()));
			}
			System.out.println("0 Exit" + "\n------------------------------" + "\n Enter Your Choice: ");
			choice = input.nextInt();
			input.nextLine(); //consume newline character
			
			if (choice <= topics.size() && choice > 0) {
				System.out.println("-----------------------------\n"
						+ "    Modify Topics Menu\n"
						+ "-----------------------------\n"
						+ " a add a word\n"
						+ " r remove a word\n"
						+ " c change a word\n"
						+ " 0 Exit\n"
						+ "----------------------------- \n"
						+"Enter Your Choice: ");
				String modifyChoice= input.next();
				input.nextLine();//newline character
				if (modifyChoice.equalsIgnoreCase("a")) {
					System.out.println("Type a word and press Enter, or press Enter to end input");
					String word = input.nextLine();
					if (!word.trim().isEmpty()) {
						if(topics.getElement(choice-1).hasWord(word)) {
							System.out.println("Already has word");
							break;}
						else {
							topics.getElement(choice-1).addWord(word);
							
						}
					} else {
						break;
					}
					
				}
				else if (modifyChoice.equalsIgnoreCase("r")) {
					System.out.println("Type a word and press Enter, or press Enter to end input");
					String word = input.nextLine();
					if (!word.trim().isEmpty()) {
						if(topics.getElement(choice-1).hasWord(word)) {
							topics.getElement(choice-1).removeWord(word);
							break;}
						else {
							System.out.println("there is no word: "+word);
							
						}
					} else {
						break;
					}
					
				}
				else if (modifyChoice.equalsIgnoreCase("c")) {
					System.out.println("Type a word and press Enter, or press Enter to end input");
					String word = input.nextLine();
					if (!word.trim().isEmpty()) {
						if(topics.getElement(choice-1).hasWord(word)) {
							System.out.println("write new word:" );
							String newWord = input.next();
							topics.getElement(choice-1).changeWord(word, newWord);
							break;}
						else {
							System.out.println("there is no word: "+word);
							
						}
					} else {
						break;
					}
	
				}	
				else if (modifyChoice.equalsIgnoreCase("0")) {
					break;}
				else {
						System.out.println("invalid choice");
					}
				

			} else if (choice == 0) {
				break;
			} else {
				System.out.println("invalid choice");
			}

		} while (choice != 0);
		System.out.println();		
	}
	/**
     * Removes a topic.
     */
	private static void removeTopic() {
		int choice = 0;
		do {
			System.out.println(
					"\n------------------------------ \n" + " Pick a topic \n" + "------------------------------");

			// prints the topics and ignores the first character '#'
			for (int i = 0; i < topics.size(); i++) {
				System.out.println(
						i + 1 + " " + (topics.getElement(i).topic).substring(1, (topics.getElement(i).topic).length()));
			}
			System.out.println("0 Exit" + "\n------------------------------" + "\n Enter Your Choice: ");
			choice = input.nextInt();
			input.nextLine(); //consume newline character
			
			//deletes topic
			if (choice <= topics.size() && choice > 0) {
				topics.deleteAt(choice-1);

			} else if (choice == 0) {
				break;
			} else {
				System.out.println("invalid choice");
			}

		} while (choice != 0);
		System.out.println();		
	}
	 /**
     * Adds a new topic after another one.
     */
	private static void addTopicAfter() {
		int choice = 0;
		do {
			System.out.println(
					"\n------------------------------ \n" + " Pick a topic \n" + "------------------------------");

			// prints the topics and ignores the first character '#'
			for (int i = 0; i < topics.size(); i++) {
				System.out.println(
						i + 1 + " " + (topics.getElement(i).topic).substring(1, (topics.getElement(i).topic).length()));
			}
			System.out.println("0 Exit" + "\n------------------------------" + "\n Enter Your Choice: ");
			choice = input.nextInt();
			input.nextLine(); //consume newline character
			
			//we check if there are any topics
			if (topics.size()==0) {
					System.out.println("Enter a topic name: ");
					String name = input.nextLine();
					Vocab newTopic = new Vocab("#"+name);
					topics.add(newTopic);
					System.out.println("Enter a word - to quit press Enter: ");
					// make a loop to keep on adding words, the loop breaks if the user inputs
					// nothing and just presses enter
					while (true) {
						String word = input.nextLine();
						if (!word.trim().isEmpty()) {
							newTopic.addWord(word);
						} else {
							break;
						}
					}
					return;}
							
				//Checks if topic exists
				if (choice <= topics.size() && choice > 0) {
					System.out.println("Enter a topic name: ");
					String name = input.nextLine();
					for (int i = 0;i<topics.size();i++) {
						if (topics.getElement(i).topic.replaceFirst("#","").equalsIgnoreCase(name)) {
							System.out.println("Topic already exists");
							return;
						}
						else {
							continue;
						}
					}
					
					//adds topic
				
				Vocab newTopic = new Vocab("#"+name);
				topics.addAt(newTopic, choice);
				System.out.println("Enter a word - to quit press Enter: ");
				// make a loop to keep on adding words, the loop breaks if the user inputs
				// nothing and just presses enter
				while (true) {
					String word = input.nextLine();
					if (!word.trim().isEmpty()) {
						newTopic.addWord(word);
					} else {
						break;
					}
				}

			} else if (choice == 0) {
				break;
			} else {
				System.out.println("invalid choice");
			}

		} while (choice != 0);
		System.out.println();		
	}
	/**
     * Adds a new topic before another one.
     */
	private static void addTopicBefore() {
		int choice = 0;
		do {
			System.out.println(
					"\n------------------------------ \n" + " Pick a topic \n" + "------------------------------");

			// prints the topics and ignores the first character '#'
			for (int i = 0; i < topics.size(); i++) {
				System.out.println(
						i + 1 + " " + (topics.getElement(i).topic).substring(1, (topics.getElement(i).topic).length()));
			}
			System.out.println("0 Exit" + "\n------------------------------" + "\n Enter Your Choice: ");
			choice = input.nextInt();
			input.nextLine(); //consume newline character
			
			//we check if there are any topics
			if (topics.size()==0) {
					System.out.println("Enter a topic name: ");
					String name = input.nextLine();
					Vocab newTopic = new Vocab("#"+name);
					topics.add(newTopic);
					System.out.println("Enter a word - to quit press Enter: ");
					// make a loop to keep on adding words, the loop breaks if the user inputs
					// nothing and just presses enter
					while (true) {
						String word = input.nextLine();
						if (!word.trim().isEmpty()) {
							newTopic.addWord(word);
						} else {
							break;
						}
					}
					return;
				
			}
			//Checks if topic exists
			if (choice <= topics.size() && choice > 0) {
				System.out.println("Enter a topic name: ");
				String name = input.nextLine();
				for (int i = 0;i<topics.size();i++) {
					if (topics.getElement(i).topic.replaceFirst("#","").equalsIgnoreCase(name)) {
						System.out.println("Topic already exists");
						return;
					}
					else {
						continue;
					}
				}
				
				//adds topic
				Vocab newTopic = new Vocab("#"+name);
				topics.addAt(newTopic, choice - 1);
				System.out.println("Enter a word - to quit press Enter: ");
				// make a loop to keep on adding words, the loop breaks if the user inputs
				// nothing and just presses enter
				while (true) {
					String word = input.nextLine();
					if (!word.trim().isEmpty()) {
						newTopic.addWord(word);
					} else {
						break;
					}
				}

			} else if (choice == 0) {
				break;
			} else {
				System.out.println("invalid choice");
			}

		} while (choice != 0);
		System.out.println();

	}
	/**
     * Browses a topic.
     */
	private static void browseATopic() {
		int choice = 0;
		do {
			System.out.println(
					"\n------------------------------ \n" + " Pick a topic \n" + "------------------------------");

			// prints the topics and ignores the first character '#'
			for (int i = 0; i < topics.size(); i++) {
				System.out.println(
						i + 1 + " " + (topics.getElement(i).topic).substring(1, (topics.getElement(i).topic).length()));
			}
			System.out.println("0 Exit" + "\n------------------------------" + "\n Enter Your Choice: ");
			choice = input.nextInt();
			input.nextLine();
			if (choice <= topics.size() && choice > 0) {
				(topics.getElement(choice - 1)).printSorted();
			} else if (choice == 0) {
				break;
			} else {
				System.out.println("invalid choice");
			}

		} while (choice != 0);
		System.out.println();

	}
	
	/**
     * Reads topics and words from a file.
     */
	public static void readFile() {

		BufferedReader br = null;
		String line;
		Vocab vocab = null;
		System.out.println("Please input file name: ");
		String name = input.next();
		input.nextLine();
		try {
			br = new BufferedReader(new FileReader("./src/comp249_A3/"+name));
			System.out.println("File Successfully found.");
			while ((line = br.readLine()) != null) {
				if (line.contains("#")) {
					vocab = new Vocab(line);
					topics.add(vocab);
					continue;
				}
				// to check that we arent adding an empty line to our array of words
				if (!(line.trim().isEmpty())) {
					vocab.addWord(line);
				}

			}

		} catch (Exception e) {
			System.out.println("file not found");
		}

	}

	
	/**
     * Prints the main menu.
     */
	static public void printMenu() {

		String menu = "\n-----------------------------\n" + "  Vocabulary Control Center  \n"
				+ "-----------------------------\n" + " 1  browse a topic \n"
				+ " 2  insert a new topic before another one  \n" + " 3  insert a new topic after another one\n"
				+ " 4  remove a topic \n" + " 5  modify a topic \n" + " 6  search topics for a word \n"
				+ " 7  load from a file \n" + " 8  show all words starting with a given letter \n"
				+ " 9  save to file \n" + " 0  exit \n" + "-----------------------------\n" + "Enter Your Choice: ";

		System.out.print(menu);

	}

	static public void printEverything() {
		for (int i = 0; i < topics.size(); i++) {
			System.out.println(topics.getElement(i).topic);
			(topics.getElement(i)).words.printElements();
		}
	}

}
