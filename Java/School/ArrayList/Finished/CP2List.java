//DO NOT MODIFY THIS FILE
interface CP2List
{
	void add(int value);
	void add(int index, int value);
	void clear();
	boolean contains(int value);
	int get(int index);
	int indexOf(int value);
	boolean isEmpty();
	int pop(int index); //removes based on index...java calls this remove(int index)
	boolean remove(int value);
	int set(int index, int value);
	int size();
	CP2List subList(int fromIndex, int toIndex); //extra
	int[] toArray();
	
}
