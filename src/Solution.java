import java.io.*;
import java.util.HashMap;
import java.util.Stack;


public class Solution {
	
	public static void main(String[] args) throws Exception {
		
		File inputFile = new File("input.txt");
		HashMap<String, String> hierarchy;
		CharNode root = new CharNode();
		if( !inputFile.exists()) {
			throw new FileNotFoundException();
		} else {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			int topicSize = Integer.parseInt(br.readLine());
			String flatTree = br.readLine();
			hierarchy = generateHierarchy(flatTree, topicSize - 1);
			
			// Load in the questions.
			int questionSize = Integer.parseInt(br.readLine());
			for (int i = 0; i < questionSize; i++) {
				String line = br.readLine();
				String[] split = line.split(": ");
				String topic = split[0];
				char[] charArray = split[1].toCharArray();
				CharNode node = root;
				for (int j = 0; j < charArray.length; j++) {
					node = node.nextNode(new Character(charArray[j]));
					Integer numb = node.getResult(topic);
					if (numb == null) {
						node.setResult(topic, 1);
					} else {
						node.setResult(topic, numb + 1);
					}
					String parent = hierarchy.get(topic);
					
					// Update the question count of all parents
					while (parent != null) {
						numb = node.getResult(parent);
						if (numb == null) {
							node.setResult(parent, 1);
						} else {
							node.setResult(parent, numb + 1);
						}
						
						parent = hierarchy.get(parent);
					}
				}
			}
			
			// Perform the query.
			int querySize = Integer.parseInt(br.readLine());
			for (int i = 0; i < querySize; i++) {
				String line = br.readLine();
				int spaceIndex = line.indexOf(" ");
				String topic = line.substring(0, spaceIndex);
				char[] charArray = line.substring(spaceIndex + 1).toCharArray();
				CharNode node = root;
				Integer numb = null;
				for (int j = 0; j < charArray.length; j++) {
					node = node.queryNextNode(new Character(charArray[j]));
					if (node == null) {
						numb = 0;
						break;
					}
				}
				if (numb == null) {
					numb = node.getResult(topic);
				}
				
				System.out.println(numb);
			}
			
			br.close();
		}

	}
	
	public static HashMap<String, String> generateHierarchy(String inputTree, int size) {
		HashMap<String, String> result = new HashMap<String, String>(size);
		Stack<String> parentStack = new Stack<String>();
		String[] split = inputTree.split(" ");
		for (int i = 1; i < split.length; i++) {
			if (split[i].equals("("))
				parentStack.push(split[i - 1]);
			else if (split[i].equals(")"))
				parentStack.pop();
			else
				result.put(split[i], parentStack.peek());
		}
		return result;
	}

}
