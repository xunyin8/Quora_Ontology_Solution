'''Solution
   Author: Xun Yin
'''


class Node:
	def __init__(self):
		self.edges = {}
		self.query_dict = {}

	def query(self, topic):
		result = self.query_dict.get(topic)
		if result:
			return result
		else:
			return 0

	def inc_count(self, topic):
		if self.query_dict.get(topic):
			self.query_dict[topic] = self.query_dict.get(topic) + 1
		else:
			self.query_dict[topic] = 1

	def walk(self, char):
		if self.edges.get(char):
			return self.edges.get(char)
		else:
			new_node = Node()
			self.edges[char] = new_node
			return new_node

	def query_walk(self, char):
		return self.edges.get(char)


def main():
	N = int(raw_input())
	flat_tree = raw_input()
	hierarchy = generate_hierarchy(flat_tree)
	M = int(raw_input())
	root_node = load_questions(M, hierarchy)
	K = int(raw_input())
	result = query_questions(K, root_node)
	for e in result:
		print e

def generate_hierarchy(input_string):
	hierarchy = {}
	stack = []
	tree_list = input_string.split(" ")
	for i in range(len(tree_list)):
		if tree_list[i] == '(':
			stack.append(tree_list[i - 1])
		elif tree_list[i] == ')':
			stack.pop()
		else:
			if stack:
				hierarchy[tree_list[i]] = stack[len(stack) - 1]

	return hierarchy

def load_questions(size, hierarchy):
	root_node = Node()
	for i in range(size):
		node = root_node
		line = raw_input()
		(topic, question) = line.split(': ', 1)
		question_char = list(question)
		for char in question_char:
			node = node.walk(char)
			node.inc_count(topic)
			'''Update all parent's count
			'''
			parent = hierarchy.get(topic)
			while parent:
				node.inc_count(parent)
				parent = hierarchy.get(parent)
	return root_node

def query_questions(size, root_node):
	result = []
	for i in range(size):
		node = root_node
		line = raw_input()
		(topic, text) = line.split(' ', 1)
		text_char = list(text)
		not_found = False
		for char in text_char:
			node = node.query_walk(char)
			if not node:
				not_found = True
				break
		if not_found:
			result.append(0)
		else:
			result.append(node.query(topic))
	return result
if __name__=="__main__":
	main()
