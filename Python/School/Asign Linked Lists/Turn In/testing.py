from main import LinkedList
def test_append():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.append(4)
    linked_list.append(5)
    print("Success" if str(linked_list) == "[1, 2, 3, 4, 5]" else "Failure")

def test_clear():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.clear()
    print("Success" if str(linked_list) == "[]" else "Failure")

def test_index():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.append(4)
    linked_list.append(5)
    print("Success" if linked_list.index(3) == 2 else "Failure")

def test_insert():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.insert(2, 10)
    print("Success" if str(linked_list) == "[1, 2, 10, 3]" else "Failure")

def test_pop():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    popped = linked_list.pop()
    print("Success" if popped == 3 and str(linked_list) == "[1, 2]" else "Failure")

def test_remove():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.remove(2)
    print("Success" if str(linked_list) == "[1, 3]" else "Failure")

def test_getitem():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    print("Success" if linked_list[1] == 2 else "Failure")

def test_setitem():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list[1] = 5
    print("Success" if str(linked_list) == "[1, 5, 3]" else "Failure")

def test_len():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    print("Success" if len(linked_list) == 3 else "Failure")

def test_contains():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    print("Success" if 2 in linked_list else "Failure")

def test_append_2():
    linked_list = LinkedList()
    linked_list.append(6)
    linked_list.append(7)
    linked_list.append(8)
    linked_list.append(9)
    linked_list.append(10)
    print("Success" if str(linked_list) == "[6, 7, 8, 9, 10]" else "Failure")

def test_clear_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.clear()
    print("Success" if str(linked_list) == "[]" else "Failure")

def test_index_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.append(4)
    linked_list.append(5)
    print("Success" if linked_list.index(5) == 4 else "Failure")

def test_insert_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.insert(0, 0)
    print("Success" if str(linked_list) == "[0, 1, 2, 3]" else "Failure")

def test_pop_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    popped = linked_list.pop(1)
    print("Success" if popped == 2 and str(linked_list) == "[1, 3]" else "Failure")

def test_remove_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.remove(1)
    print("Success" if str(linked_list) == "[2, 3]" else "Failure")

def test_getitem_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    print("Success" if linked_list[2] == 3 else "Failure")

def test_setitem_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list[2] = 5
    print("Success" if str(linked_list) == "[1, 2, 5]" else "Failure")

def test_len_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.append(4)
    print("Success" if len(linked_list) == 4 else "Failure")

def test_contains_2():
    linked_list = LinkedList()
    linked_list.append(1)
    linked_list.append(2)
    linked_list.append(3)
    linked_list.append(4)
    print("Success" if 5 not in linked_list else "Failure")

# Testing
test_append()
test_clear()
test_index()
test_insert()
test_pop()
test_remove()
test_getitem()
test_setitem()
test_len()
test_contains()

# Additional Testing
test_append_2()
test_clear_2()
test_index_2()
test_insert_2()
test_pop_2()
test_remove_2()
test_getitem_2()
test_setitem_2()
test_len_2()
test_contains_2()
