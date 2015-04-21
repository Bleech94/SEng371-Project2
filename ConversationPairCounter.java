
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;



public class ConversationPairCounter {
	
	static String[][] subjectNamePairs; // 2D array of strings. C1: Name, C2: Email Subject
	
	static String[] uniqueNames; // array of unique names found
	
	static Map<String, Integer> conversationMap; // Map <String, int>  Key: Pair of people, Value: Number of Conversations for the pair
	
	public static String[][] GetSubjectNamePairs(File filename) {
		
		try{
        	
				//Counts the number of lines
            Scanner inputStream = new Scanner(filename);
            
            int i = 0;
            int numLines = 0; 
           
            while(inputStream.hasNext()){
            	
            	numLines++;
            	inputStream.nextLine();
            	
            }
            inputStream.close();
            
            inputStream = new Scanner(filename);
            subjectNamePairs = new String[numLines][2];
            
            while(inputStream.hasNext()){
            	
            	String data = inputStream.nextLine();
            	
            	String[] s = data.split(",");
            	
            	subjectNamePairs[i][0] = s[0];
            	subjectNamePairs[i][1] = s[1];
            	
            	i++;
            }

            inputStream.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
		System.out.println(subjectNamePairs.length);
        System.out.println("********************Subject Name Pairs Generated************************");
		return subjectNamePairs;
	}// GetSubjectNamePairs()
	
	
	
	
	
	public static Map<String, Integer> GetConversationMap() {
		
		int i = 0;
		int nameCounter = 0;
		String[] dumbList  = new String[100000];
		
		// Determine list of unique names
		for(i = 0; i < subjectNamePairs.length; i++){	
			
			String name = subjectNamePairs[i][1];
			for(int j = 0; j <= nameCounter; j++) {
	
				if(name.equals(dumbList[j])) {
				
					break;
				}
				
				if(nameCounter == j){
					dumbList[nameCounter] = name;	
					nameCounter++;
					System.out.println(name);
					break;
				}
			}
		}
		System.out.println("nameCounter: "  + nameCounter);
		
		String[] uniqueNames = new String[nameCounter];
		
		i=0;
		
		//Copy dumbList to uniqueNames with correct size
		while(dumbList[i] != null) {
			uniqueNames[i] = dumbList[i];
			i++;
		}
		
		
		
		Map<String, Integer> conversationMap = new HashMap<String, Integer>(uniqueNames.length * (uniqueNames.length - 1) / 2);
		
		System.out.println("conversationList length: " + conversationMap.size());
		
		// Add all unique pairs of people to conversationList
		for(int person1 = 0; person1 < uniqueNames.length-1; person1++) {
			for(int person2 = person1 + 1; person2 < uniqueNames.length; person2++) {
				conversationMap.put(uniqueNames[person1] + "+ " + uniqueNames[person2], 0);
			}
		}	
		
//		Loop through all email subjects, if it has no Re: compare with all following emails and keep track of associated names
		for(int currentSubjectIndex = 0; currentSubjectIndex < subjectNamePairs.length; currentSubjectIndex++) {
			String currentSubject = subjectNamePairs[currentSubjectIndex][0];
			
			// Skip all subjects that start with RE
			Pattern startsWithRe = Pattern.compile("^(re|Re|rE|RE).*");
			Matcher m1 = startsWithRe.matcher(currentSubject);
			if(m1.matches()) {
				continue;
			}
			
			String[] namesAssociatedWithCurrentSubject = new String[100000];
			//Add name of initial email to the list of associated names
			namesAssociatedWithCurrentSubject[0] = subjectNamePairs[currentSubjectIndex][1];
			int numberAssociatedNames = 1;
			
			// Check the following emails for other associated names
			Pattern containsSubject = Pattern.compile("^(re|Re|rE|RE)" + currentSubject);
			for(int comparingSubjectIndex = currentSubjectIndex + 1; comparingSubjectIndex < subjectNamePairs.length; comparingSubjectIndex++) {
				String comparingSubject = subjectNamePairs[comparingSubjectIndex][0];
				Matcher m2 = containsSubject.matcher(comparingSubject);
				if(m2.matches()) {
					//Add subjectNamePairs[comparingSubjectIndex][1] to the list of associated names
					namesAssociatedWithCurrentSubject[numberAssociatedNames] = subjectNamePairs[comparingSubjectIndex][1];
					numberAssociatedNames++;
				}
			}
			
			//Once we have a full list of associated names
			//Iterate through and +1 each pair of people
			for(int person1 = 0; person1 < numberAssociatedNames - 1; person1++) {
				for(int person2 = person1 + 1; person2 < numberAssociatedNames; person2++) {
					
					if(namesAssociatedWithCurrentSubject[person1].equals(namesAssociatedWithCurrentSubject[person2])) {
						continue;
					}
					
					if(conversationMap.containsKey(namesAssociatedWithCurrentSubject[person1]+"+ "+namesAssociatedWithCurrentSubject[person2])) {						
						int currentConversationNumber = conversationMap.get(namesAssociatedWithCurrentSubject[person1]+"+ "+namesAssociatedWithCurrentSubject[person2]);
						currentConversationNumber++;
						conversationMap.put(namesAssociatedWithCurrentSubject[person1]+"+ "+namesAssociatedWithCurrentSubject[person2], currentConversationNumber);
					} else {
						int currentConversationNumber = conversationMap.get(namesAssociatedWithCurrentSubject[person2]+"+ "+namesAssociatedWithCurrentSubject[person1]);
						currentConversationNumber++;
						conversationMap.put(namesAssociatedWithCurrentSubject[person2]+"+ "+namesAssociatedWithCurrentSubject[person1], currentConversationNumber);

					} // if/else
					
				} // inner for
				
			} // end of one email subject
			
		} // finished all email subjects
		
		return conversationMap;
	}
	
	
	
	
	public static void main(String[] args) {
		        
        File file = new File("tester.txt");

        subjectNamePairs = GetSubjectNamePairs(file);
        
        conversationMap = GetConversationMap();
        
        FileWriter writer;
        
        try {
        	
            writer = new FileWriter("graphOutput.csv");
            
            for(String key: conversationMap.keySet()) {
            	if (conversationMap.get(key) > 1000) {
                	writer.append(key + ", " + conversationMap.get(key));
                	writer.append("\n");

            	}
            }
            
            writer.flush();
            writer.close();

        }catch (IOException e) {
        	e.printStackTrace();
        }
        
        
        
                           System.out.println("done"); 
            
        
    
        
	}

}
