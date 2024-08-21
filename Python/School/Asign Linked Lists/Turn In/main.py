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

    def append(self,data):
        self.size+=1
        # current=self.root
        # while current.next:
        #     current=current.next
        # current.next=Node(data)
        self.last.next=Node(data)
        self.last=self.last.next

    def clear(self):
        self.root.next = None
        self.last = self.root
        self.size = 0

    def index(self, item):
        current = self.root.next
        index = 0
        while current:
            if current.data == item:
                return index
            current = current.next
            index += 1
        raise ValueError(f"{item} is not in list")

    def insert(self, index, data):
        if index < 0 or index > self.size:
            raise IndexError("index out of bounds")
        if index == self.size:
            self.append(data)
            return
        current = self.root
        for _ in range(index):
            current = current.next
        new_node = Node(data)
        new_node.next = current.next
        current.next = new_node
        self.size += 1

    def pop(self, index=None):
        if index is None:
            index = self.size - 1
        if index < 0 or index >= self.size:
            raise IndexError("index out of bounds")
        current = self.root
        for _ in range(index):
            current = current.next
        popped_node = current.next
        current.next = popped_node.next
        if index == self.size - 1:
            self.last = current
        self.size -= 1
        return popped_node.data

    def remove(self, item):
        current = self.root
        while current.next:
            if current.next.data == item:
                if current.next == self.last:
                    self.last = current
                current.next = current.next.next
                self.size -= 1
                return
            current = current.next
        raise ValueError(f"{item} is not in list")

    def __getitem__(self, index):
        if index >= self.size or index < 0:
            raise IndexError("index out of bounds")
        current = self.root.next
        for _ in range(index):
            current = current.next
        return current.data

    def __setitem__(self, index, data):
        if index >= self.size or index < 0:
            raise IndexError("index out of bounds")
        current = self.root.next
        for _ in range(index):
            current = current.next
        current.data = data

    def __str__(self):
        current = self.root.next
        out = ""
        while current:
            out += f"{current.data}, "
            current = current.next
        return f"[{out[:-2]}]"

    def __len__(self):
        return self.size

    def __contains__(self, item):
        current = self.root.next
        while current:
            if current.data == item:
                return True
            current = current.next
        return False
