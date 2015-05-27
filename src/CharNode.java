import java.util.HashMap;

public class CharNode {
	private HashMap<Character, CharNode> edges;
	private HashMap<String, Integer> queryMap;
	
	public CharNode() {
		edges = new HashMap<Character, CharNode>();
		queryMap = new HashMap<String, Integer>();
	}
	
	public Integer getResult(String memberName) {
		return queryMap.get(memberName);
	}
	
	public void setResult(String memberName, Integer numb) {
		queryMap.put(memberName, numb);
	}
	
	public CharNode nextNode(Character nextChar) {
		if (edges.get(nextChar) == null) {
			CharNode temp = new CharNode();
			edges.put(nextChar, temp);
			return temp;
		} else {
			return edges.get(nextChar);
		}
	}
	
	public CharNode queryNextNode(Character nextChar) {
		return edges.get(nextChar);
	}
}
