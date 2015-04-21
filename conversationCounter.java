


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;



public class ConversationPairCounter {
	
	static String[][] subjectNamePairs = new String[100000][2];

	static String[][] conversationList = new String[100000][2];
	
	public static String[][] GetSubjectNamePairs(File filename) {
		
		try{
        	
            Scanner inputStream = new Scanner(filename);
            
            int i = 0;
            
            while(inputStream.hasNext()){
            	
            	String data = inputStream.nextLine();
            	
            	String[] s = data.split(",\",,,\",");
            	
            	if(s.length < 1) {
            		continue;
            	}
            	
            	while(s.length < 2) {
            		data = data + inputStream.nextLine();
            		s = data.split(",\",,,\",");
            	}
            	
            	subjectNamePairs[i][0] = s[0];
            	subjectNamePairs[i][1] = s[1];
            	
//            	System.out.println(s[0]+ "             " + s[1]);
//            	System.out.println();
            	
            	i++;
            }

            inputStream.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
		
        System.out.println("********************Subject Name Pairs Generated************************");
		return subjectNamePairs;
	}// GetSubjectNamePairs()
	
	public static String[][] GetConversationList() {
		
		int i = 0;
		
		
		while(subjectNamePairs[i][0] != null) {
			ArrayList<String> associatedNames = new ArrayList<String>();
			String subjectPattern = subjectNamePairs[i][0];
			String subjectPatternFinal = "";
			for (int character = 0; character < subjectPattern.length(); character++){
			    char c = subjectPattern.charAt(character);        
			    if (c == '?' || c == '(' || c == ')' || c == '*' || c == '[' || c ==']') {
			    	break;
			    }
			    subjectPatternFinal += c;
			}
			Pattern p = Pattern.compile(".*"+subjectPatternFinal+".*");
			
			associatedNames.add(subjectNamePairs[i][1]);
			
			for(int j = 0; j < 100000; j++) {
				if(subjectNamePairs[j][0] == null) {
					break;
				}
				
				Matcher m = p.matcher(subjectNamePairs[j][0]);
				
				if(m.matches()) {
					if(Collections.binarySearch(associatedNames, subjectNamePairs[j][1]) < 0) {
						associatedNames.add(subjectNamePairs[j][1]);
						Collections.sort(associatedNames);
					}
				}
			}
			
			int counter = 0;
			
			for(int k = 0 ; k < associatedNames.size(); k ++){
				  for(int l = k+1 ; l < associatedNames.size(); l ++){
				    System.out.println(subjectPattern + "     *AN.GETK: " + associatedNames.get(k) + "," + "*AN.GETL: " + associatedNames.get(l)); 
					  System.out.println(counter);
					  counter++;
				  }
			}
			
			
			i++;
		}
		
		return conversationList;
	}
	//takes in the file and returns a 2d array containing names and the number of conversations that they have been in.
	public static String[][] GetConversationNumber(File fileName, int nameCounter){
		String[][] conversationsAndPeople;//holds the final number of conversations that a person participated and their name.
			
		String[] uniqueNames = new String[70000];	//hold the list of unique people;
			
		String[] senders = new String[100000];//holds the people who 
		
		int[] numberSent = new int[70000];//
		
		int row = 0;//row iterator
		
		Scanner inputStream;//inputStream
		try {
			
			inputStream = new Scanner(fileName);//The file containing all the names
						
	        while(inputStream.hasNext()){ //while there are still entries in the file
	        	        	
	        	String data = inputStream.nextLine();//get each new line
	        		        	
	        	String[] segment = data.split(",");//split the line in half
	        	senders[row] = segment[1];//copy the name of all the sender into the senders array
	        	row++;
	        	//uniqueNamesTEST[testCounter] = segment[1];
	        	//now we need to go through every line, 
	        	//check if the name is already in list, 
	        	//and if it isn't add it to the uniqueNames array
	        	if(!nameInList(segment[1], uniqueNames, nameCounter)){
	        			        	
	        		uniqueNames[nameCounter] = segment[1];//add the name to the list
	        		//System.out.println(segment[1]);	        		
	        		nameCounter++;//increment the nameCounter
	        	}
	        	//inputStream.close();
	        	//now search for all the times that a name appears
	        	
	        }
	       
	        //takes the array of unique names and goes through the full list of senders for each entry in uniqueNames
	        for(int i = 0; i < nameCounter; i++){//iterates through the full list of uniqueNames
        		for(int j = 0; j < senders.length; j++){//iterates through the full list of senders as found in the email file
        			if(uniqueNames[i].equals(senders[j])){ //if the uniqueName is found in the array senders, then iterate the numberSent array entry
        				numberSent[i] = numberSent[i] + 1;
        				//System.out.println(numberSent[i]);
        			}
        		}
        	}
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(nameCounter);
		conversationsAndPeople = new String[nameCounter][2];
        for(int i = 0; i < nameCounter; i++){
        	conversationsAndPeople[i][0] = uniqueNames[i];
        	conversationsAndPeople[i][1] = Integer.toString(numberSent[i]);
        }
		return conversationsAndPeople;
	}
	
	//checks if the name is already part of the list
	//returns false if the name isn't in the list
	//true if the name is in the list
	private static boolean nameInList(String segment, String[] uniqueNames, int nameCounter) {
		
		boolean checkName = false;//variable for holding the check result
		
		
		for(int i = 0; i < nameCounter; i++){
			if(uniqueNames[i] != null && uniqueNames[i].equals(segment)){
				checkName = true;
			}
		}
		//return result of test
		return checkName;
	}
	
	
	public static void main(String[] args) {
		int nameCounter = 0;
        File file = new File("godfile.txt");
        String[][] numOfConversations;
        numOfConversations = GetConversationNumber(file, nameCounter);
        /*for(int i = 0; i < numOfConversations.length; i ++){
        		System.out.println(i + ":" + numOfConversations[i][0] + "," + numOfConversations[i][1]);
        }*/
        FileWriter writer;
        try{
          	writer = new FileWriter("conversationListOutput.csv");
            for(int i = 0; i < numOfConversations.length; i ++){
        		writer.append(numOfConversations[i][0]);
        		writer.append(",");
        		writer.append(numOfConversations[i][1]);
        		writer.append("\n");
        		
            }
            writer.flush();
            writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("done");
       }
	}




