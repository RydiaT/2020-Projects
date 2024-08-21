import time
import random
import sys

sys.setrecursionlimit(10100)


class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

    def add_iter(self, data):
        current = self
        while current.next:
            current = current.next
        current.next = Node(data)

    def add_rec(self, data):
        if not self.next:
            self.next = Node(data)
        else:
            self.next.add_rec(data)

    def __str__(self):
        """Return a string representation of the linked list."""
        values = []
        current = self
        while current:
            values.append(str(current.data))
            current = current.next
        return ' -> '.join(values) + ' -> None'


class LinkedList:
    def __init__(self):
        self.size = 0
        self.root = Node(0)  # fauxroot
        self.last = self.root

    def append(self, data):
        self.size += 1
        # current=self.root
        # while current.next:
        #     current=current.next
        # current.next=Node(data)
        self.last.next = Node(data)
        self.last = self.last.next

    def __getitem__(self, index):
        if index >= self.size:
            raise IndexError("index out of bounds")
        current = self.root.next
        for i in range(index):
            current = current.next
        return current.data

    def __str__(self):
        current = self.root.next
        out = ""
        while current:
            out += f"{current.data}, "
            current = current.next
        return f"[{out[:-2]}]"


# start=time.time()
# root=Node(random.random())
# for i in range(10000):
#     root.add_rec(random.random())
# print(time.time()-start)
mylist = LinkedList()
start = time.time()
for i in range(1_000_000):
    mylist.append(i * i)
print(time.time() - start)
print(mylist[88])
if 9 in mylist:
    print("Nein.")
# get  mylist[23]
# brrr

# append
# clear
# index (error if not there)
# insert
# pop
# remove
# __getitem__
# __setitem__
# __str__
# __len__
# __contains__
# ['__add__', '__delitem__', '__getitem__', '__len__',
# '__setitem__', '__str__', 'append', 'clear',
# 'index', 'insert', 'pop', 'remove']
