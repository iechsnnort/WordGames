import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* WordGames
    CSIS 1400 Team Project
    Authors: Nephi Norton, Oscar Roman Marcial
    Last modified: 23 Apr. 2023

    Entry point for the program.
 */
public class WordGames {
    public static final Scanner INPUT = new Scanner(System.in);
    public static ArrayList<String> wordBank = new ArrayList<>();

    public static Hashtable<String, String> dictionary = new Hashtable<>();

    public static void main(String[] args) throws FileNotFoundException {
        // Add dictionary entries to the dictionary. Not the best way to do this.
        dictionary.put("Monkey".toUpperCase(), "A small to medium-sized primate that typically has a long tail, most kinds of which live in trees in tropical countries.");
        dictionary.put("Rabbit".toUpperCase(), "A plant-eating mammal with long ears, long hind legs, and a short tail.");
        dictionary.put("Dolphin".toUpperCase(), "A small toothed whale that typically has a beaklike snout and a curved fin on the back. Known for their sociable nature and high intelligence.");
        dictionary.put("Bird".toUpperCase(), "A warm-blooded egg-laying vertebrate distinguished by the possession of feathers, wings, and a beak and (typically) by being able to fly.");
        dictionary.put("Cougar".toUpperCase(), "A large American wild cat with a plain tawny to grayish coat, found from Canada to Patagonia. It is also called mountain lion, puma, or panther. ");
        dictionary.put("Dog".toUpperCase(), "A domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, non retractable claws, and a barking, howling, or whining voice. Often called “Man’s best friend”.");
        dictionary.put("Cat".toUpperCase(), "Widely kept as a pet or for catching mice. Soft fur animals, people often debate which one is better between this animal or dogs. ");
        dictionary.put("Mouse".toUpperCase(), "A small rodent that typically has a pointed snout, relatively large ears and eyes, and a long tail. It has the same name as a handheld device used for computers. ");
        dictionary.put("Alabama".toUpperCase(), "Admitted to the US Constitution on Dec. 14, 1819 with the capital being Montgomery and having a postal abbreviation of AL. ");
        dictionary.put("Alaska".toUpperCase(), "Admitted to the US Constitution on Jan. 3, 1959 with the capital being Juneau and having a postal abbreviation of AK.");
        dictionary.put("Arizona".toUpperCase(), "Admitted to the US Constitution on Feb. 14, 1912 with the capital being Phoenix and having a postal abbreviation of AZ. ");
        dictionary.put("Arkansas".toUpperCase(), "Admitted to the US Constitution on Jun. 15, 1836 with the capital being Little Rock and having a postal abbreviation of AR. ");
        dictionary.put("California".toUpperCase(), "Admitted to the US Constitution on Sep. 9, 1850 with the capital being  Sacramento and having a postal abbreviation of CA ");
        dictionary.put("Colorado".toUpperCase(), "Admitted to the US Constitution on Aug. 1, 1876 with the capital being Denver and having a postal abbreviation of CO. ");
        dictionary.put("Connecticut".toUpperCase(), "Admitted to the US Constitution on Jan. 9, 1788 with the capital being Hartford and having a postal abbreviation of CT. ");
        dictionary.put("Delaware".toUpperCase(), "Admitted to the US Constitution on Dec. 7, 1787 with the capital being Dover and having a postal abbreviation of DE. ");
        dictionary.put("Florida".toUpperCase(), "Admitted to the US Constitution on Mar. 3, 1845 with the capital being Tallahassee and having a postal abbreviation of FL. ");
        dictionary.put("Georgia".toUpperCase(), "Admitted to the US Constitution on Jan.2, 1788 with the capital being Atlanta and having a postal abbreviation of GA. ");
        dictionary.put("Hawaii".toUpperCase(), "Admitted to the US Constitution on Aug. 21, 1959 with the capital being Honolulu and having a postal abbreviation of HI. ");
        dictionary.put("Idaho".toUpperCase(), "Admitted to the US Constitution on Jul. 3, 1890 with the capital being Boise and having a postal abbreviation of ID. ");
        dictionary.put("Illinois".toUpperCase(), "Admitted to the US Constitution on Dec. 3, 1818 with the capital being Springfield and having a postal abbreviation of IL. ");
        dictionary.put("Indiana".toUpperCase(), "Admitted to the US Constitution on Dec. 11, 1816 with the capital being Indianapolis and having a postal abbreviation of IN. ");
        dictionary.put("Iowa".toUpperCase(), "Admitted to the US Constitution on Dec. 28, 1846 with the capital being Des Moines and having a postal abbreviation of IA. ");
        dictionary.put("Kansas".toUpperCase(), "Admitted to the US Constitution on Jan. 29, 1861 with the capital being Topeka and having a postal abbreviation of KS. ");
        dictionary.put("Kentucky".toUpperCase(), "Admitted to the US Constitution on Jun. 1, 1792 with the capital being Frankfort and having a postal abbreviation of KY. ");
        dictionary.put("Louisiana".toUpperCase(), "Admitted to the US Constitution on Apr. 30, 1812 with the capital being Baton Rouge and having a postal abbreviation of LA. ");
        dictionary.put("Maine".toUpperCase(), "Admitted to the US Constitution on Mar. 15, 1820 with the capital being Augusta and having a postal abbreviation of ME. ");
        dictionary.put("Maryland".toUpperCase(), "Admitted to the US Constitution on Apr. 28, 1788 with the capital being Annapolis and having a postal abbreviation of MD. ");
        dictionary.put("Massachusetts".toUpperCase(), "Admitted to the US Constitution on Feb. 6, 1788 with the capital being Boston and having a postal abbreviation of MA. ");
        dictionary.put("Michigan".toUpperCase(), "Admitted to the US Constitution on Jan. 26, 1837 with the capital being Lansing and having a postal abbreviation of MI. ");
        dictionary.put("Minnesota".toUpperCase(), "Admitted to the US Constitution on May 11, 1858 with the capital being St. Paul and having a postal abbreviation of MN. ");
        dictionary.put("Mississippi".toUpperCase(), "Admitted to the US Constitution on Dec. 10, 1817 with the capital being Jackson and having a postal abbreviation of MS. ");
        dictionary.put("Missouri".toUpperCase(), "Admitted to the US Constitution on Aug. 10, 1821 with the capital being Jefferson City and having a postal abbreviation of MO. ");
        dictionary.put("Montana".toUpperCase(), "Admitted to the US Constitution on  Nov. 8, 1889 with the capital being Helena and having a postal abbreviation of MT. ");
        dictionary.put("Nebraska".toUpperCase(), "Admitted to the US Constitution on Mar. 1, 1867 with the capital being Lincoln and having a postal abbreviation of NE. ");
        dictionary.put("Nevada".toUpperCase(), "Admitted to the US Constitution on Oct. 31, 1864 with the capital being Carson City and having a postal abbreviation of NV. ");
        dictionary.put("NewHampshire".toUpperCase(), "Admitted to the US Constitution on Jun. 21, 1788 with the capital being Concord and having a postal abbreviation of NH. ");
        dictionary.put("NewJersey".toUpperCase(), "Admitted to the US Constitution on Dec. 18, 1787 with the capital being Trenton and having a postal abbreviation of NJ. ");
        dictionary.put("NewMexico".toUpperCase(), "Admitted to the US Constitution on Jan 6, 1912 with the capital being Sante Fe and having a postal abbreviation of NM. ");
        dictionary.put("NewYork".toUpperCase(), "Admitted to the US Constitution on Jul. 26, 1788 with the capital being Albany and having a postal abbreviation of NY. ");
        dictionary.put("NorthCarolina".toUpperCase(), "Admitted to the US Constitution on Nov 21, 1789 with the capital being Raleigh and having a postal abbreviation of NC. ");
        dictionary.put("NorthDakota".toUpperCase(), "Admitted to the US Constitution on Nov. 2, 1889 with the capital being Bismarck and having a postal abbreviation of ND. ");
        dictionary.put("Ohio".toUpperCase(), "Admitted to the US Constitution on Mar. 1, 1803 with the capital being Columbus and having a postal abbreviation of OH. ");
        dictionary.put("Oklahoma".toUpperCase(), "Admitted to the US Constitution on Nov. 16, 1907 with the capital being Oklahoma City and having a postal abbreviation of OK. ");
        dictionary.put("Oregon".toUpperCase(), "Admitted to the US Constitution on Feb 14, 1859 with the capital being Salem and having a postal abbreviation of OR. ");
        dictionary.put("Pennsylvania".toUpperCase(), "Admitted to the US Constitution on Dec. 12, 1787 with the capital being Harrisburg and having a postal abbreviation of PA. ");
        dictionary.put("RhodeIsland".toUpperCase(), "Admitted to the US Constitution on May 29, 1790 with the capital being Providence and having a postal abbreviation of RI. ");
        dictionary.put("SouthCarolina".toUpperCase(), "Admitted to the US Constitution on May 23, 1788 with the capital being Columbia and having a postal abbreviation of SC. ");
        dictionary.put("SouthDakota".toUpperCase(), "Admitted to the US Constitution on Nov. 2, 1889 with the capital being Pierre and having a postal abbreviation of SD. ");
        dictionary.put("Tennessee".toUpperCase(), "Admitted to the US Constitution on Jun. 1, 1796 with the capital being Nashville and having a postal abbreviation of TN. ");
        dictionary.put("Texas".toUpperCase(), "Admitted to the US Constitution on Dec. 29, 1845 with the capital being Austin and having a postal abbreviation of TX. ");
        dictionary.put("Utah".toUpperCase(), "Admitted to the US Constitution on Jan. 4 1896 with the capital being Salt Lake City and having a postal abbreviation of UT. ");
        dictionary.put("Vermont".toUpperCase(), "Admitted to the US Constitution on Mar. 4, 1791 with the capital being Montpelier and having a postal abbreviation of VT. ");
        dictionary.put("Virginia".toUpperCase(), "Admitted to the US Constitution on Jun. 25, 1788 with the capital being Richmond and having a postal abbreviation of VA. ");
        dictionary.put("Washington".toUpperCase(), "Admitted to the US Constitution on Nov. 11, 1889 with the capital being Olympia and having a postal abbreviation of WA. ");
        dictionary.put("WestVirginia".toUpperCase(), "Admitted to the US Constitution on Jun. 20, 1863 with the capital being Charleston and having a postal abbreviation of WV. ");
        dictionary.put("Wisconsin".toUpperCase(), "Admitted to the US Constitution on May 29, 1848 with the capital being Madison and having a postal abbreviation of WI. ");
        dictionary.put("Wyoming".toUpperCase(), "Admitted to the US Constitution on Jul. 10, 1890 with the capital being Cheyenne and having a postal abbreviation of WY. ");
        dictionary.put("NewYearsEve".toUpperCase(), "The last day of the year and usually celebrated with friends or family. People count down the end of the year together. ");
        dictionary.put("NewYearsDay".toUpperCase(), "The first day of the year. People make goals for the year whether they complete them or not. ");
        dictionary.put("Christmas".toUpperCase(), "Often associated with Santa and “holiday joy”. This day is celebrated on December 25th and people exchange gifts with friends and family. ");
        dictionary.put("Halloween".toUpperCase(), "The phrase “trick or treat” is heard the most this day and it is associated with scary things and costumes. Celebrated on the last day of October. ");
        dictionary.put("ValentinesDay".toUpperCase(), "The holiday of love and loving someone else. Most of the time it is a way for couples to express their feelings but it is not restricted to couples only. ");
        dictionary.put("StPatricksDay".toUpperCase(), "Make sure you wear green or you will be pinched. A holiday that is celebrated on March 17. ");
        dictionary.put("Easter".toUpperCase(), "Associated with a bunny and collecting as much eggs as you can find. Celebrated on April 9. ");
        dictionary.put("MothersDay".toUpperCase(), "A day to celebrate mothers and the things that mothers have done for their children. ");
        dictionary.put("FathersDay".toUpperCase(), "A day to celebrate fathers and the things that fathers have done for their children. ");
        dictionary.put("IndependenceDay".toUpperCase(), "Celebrated on July 4th, the day the Declaration of Independence was ratified by the Continental Congress in 1776. ");
        dictionary.put("Thanksgiving".toUpperCase(), "Family gathering to eat as much food as you can and give thanks to the things in your life. It is a necessity for most families to have a turkey. ");
        dictionary.put("Pencil".toUpperCase(), "Used for writing or drawing, it consists of a thin stick of graphite or a similar substance enclosed in a thin piece of wood. Typically has a rubber end to erase mistakes.");
        dictionary.put("Table".toUpperCase(), "A piece of furniture with a flat surface. This object typically has one or more legs with the purpose of placing things on top of the object. ");
        dictionary.put("Laptop".toUpperCase(), "A computer that is portable and suitable for use while traveling.");
        dictionary.put("Chair".toUpperCase(), "A seat for one person, typically has a back and four legs for support. ");
        dictionary.put("Textbooks".toUpperCase(), "Big heavy books that no one likes to read. ");
        dictionary.put("Whiteboard".toUpperCase(), "A wipeable board with a white surface, typically used for teaching. ");
        dictionary.put("Stapler".toUpperCase(), "A device used for combining sheets of paper with a staple. ");
        dictionary.put("Scissors".toUpperCase(), "An object used to cut things, typically sheets of paper. ");
        dictionary.put("Printer".toUpperCase(), "A machine for printing text or pictures onto paper. ");
        dictionary.put("Eraser".toUpperCase(), "An object used to rub out something written on paper, typically made of a soft piece of rubber. ");
        dictionary.put("Mercury".toUpperCase(), "The smallest planet in our solar system. It is nearest to the Sun and slightly larger than Earth’s moon.");
        dictionary.put("Venus".toUpperCase(), "The second planet from the Sun and a planetary neighbor to Earth, it is one of the four inner, terrestrial planets, and similar size to Earth.");
        dictionary.put("Earth".toUpperCase(), "The third planet from the Sun and the only planet (that we know of) inhabited by living things.");
        dictionary.put("Mars".toUpperCase(), "The fourth planet from the Sun with a very thin atmosphere. It is a very dusty & cold desert world.");
        dictionary.put("Jupiter".toUpperCase(), "The fifth planet from the Sun and the largest planet in the solar system. Iconic for its Great Red Spot.");
        dictionary.put("Saturn".toUpperCase(), "The sixth planet from the Sun and the second largest planet in the solar system. Very identifiable due to the rings that surround this planet.");
        dictionary.put("Uranus".toUpperCase(), "The seventh planet from the Sun and the third largest planet. This planet rotates at a nearly 90-degree angle making the planet appear to spin on its side. ");
        dictionary.put("Neptune".toUpperCase(), "The eighth planet from the Sun and the most distant major planet. The dark, cold planet is whipped by supersonic winds.");
        dictionary.put("Pluto".toUpperCase(), "Once known as the ninth planet from the Sun but it is now known as a dwarf planet.");
        int selection;
        try {
            File file = new File("words_alpha.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                WordGames.wordBank.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Big error! Uh oh!");
            throw e;
        }

        do {
            WordGames.printMenu();

            // Grab input and make sure it exists/is valid.
            try {
                selection = WordGames.INPUT.nextInt();
                WordGames.INPUT.nextLine();
            } catch (Exception e) {
                System.out.println("Error: Selection must be an integer from 0-3. Exiting program!");
                selection = 0;
            }

            // Match selection to game.
            switch (selection) {
                case 0 -> System.out.println("Thanks for playing!");
                case 1 -> {
                    WordleGame wordle = new WordleGame("");
                    wordle.run();
                }
                case 2 -> {
                    Random random = new Random();
                    String word = WordGames.wordBank.get(random.nextInt(WordGames.wordBank.size()));
                    HangmanGame hangman = new HangmanGame(word);
                    hangman.run();
                }
                case 3 -> {
                    System.out.println("""
                            Choose a category:
                            1 ── Animals
                            2 ── States
                            3 ── Holidays
                            4 ── Objects found in school
                            5 ── Planets
                                                        
                            0 ── Return
                            Your selection:\s""");
                    int categorySelection;
                    try {
                        categorySelection = WordGames.INPUT.nextInt();
                        WordGames.INPUT.nextLine();
                    } catch (Exception e) {
                        System.out.println("Error: Selection must be an integer from 0-5!");
                        continue;
                    }
                    if (categorySelection < 0 || categorySelection > 5) {
                        System.out.println("Error: Selection must be an integer from 0-5!");
                        continue;
                    }
                    String[] selectedWords;
                    switch (categorySelection) {
                        case 1:
                            selectedWords = new String[]{"MONKEY", "RABBIT", "DOLPHIN", "BIRD", "COUGAR", "DOG", "CAT", "MOUSE"};
                            break;
                        case 2:
                            selectedWords = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "NewHampshire", "NewJersey", "NewMexico", "NewYork", "NorthCarolina", "NorthDakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "RhodeIsland", "SouthCarolina", "SouthDakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "WestVirginia", "Wisconsin", "Wyoming"};
                            break;
                        case 3:
                            selectedWords = new String[]{"NewYearsEve", "NewYearsDay", "Christmas", "Halloween", "ValentinesDay", "StPatricksDay", "Easter", "MothersDay", "FathersDay", "IndependenceDay", "Thanksgiving"};
                            break;
                        case 4:
                            selectedWords = new String[]{"Pencil", "Table", "Laptop", "Chair", "Textbooks", "Whiteboard", "Stapler", "Scissors", "Printer", "Eraser"};
                            break;
                        case 5:
                            selectedWords = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
                            break;
                        default:
                            System.out.println("Returning to main menu!");
                            continue;
                    }

                    // Uppercase each word so that the crossword class doesn't freak out.
                    for (int i = 0; i < selectedWords.length; i++) {
                        selectedWords[i] = selectedWords[i].toUpperCase();
                    }
                    // Reduce amount of words to 5, because the algorithm grows super-duper-uber-exponentially in time
                    // taken and at above 5, it just sometimes breaks lol
                    ArrayList<String> selectedList = new ArrayList<>(List.of(selectedWords));
                    Collections.shuffle(selectedList);
                    for (int i = 0; i < selectedList.size(); i++) {
                        selectedWords[i] = selectedList.get(i);
                    }

                    CrosswordGame crossword = new CrosswordGame(Arrays.copyOfRange(selectedWords, 0, 10));
                }
            }
        } while (selection != 0);

    }

    public static void printMenu() {
        String menu = """
                ┌────────────────┐
                │ # ── Game      │
                └────────────────┘
                  1 ── Wordle
                  2 ── Hangman
                  3 ── Crossword
                                        
                  0 ── Exit
                  
                Your Selection:\s""";
        System.out.print(menu);
    }
}