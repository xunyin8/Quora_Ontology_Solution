# Quora_Ontology_Solution
Solution to the Quora ontology programming challenge

The details about the challenge can be found at http://www.quora.com/challenges

The key thing to note about this solution is that the query run time does not depend on the number of questions that are added. Instead, it depends on the character length of the query and access time of the hashmap of String -> Integer. 
Worst, worst case where access time of the hashmap is linear(very unlikely) will have a run time of O(N+n) where N is number of topic and n is number of characters in the query.  
