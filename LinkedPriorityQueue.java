

public class LinkedPriorityQueue<T extends Comparable<T> & Updatable> implements PriorityQueueInterface<T> {
    private Node firstNode;
    private int numberEntries=0;
    public LinkedPriorityQueue(){
        firstNode=null;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry, null);
        if (isEmpty()) {
            firstNode = newNode;
        } else {
            int comparison;
            for (Node iterator = firstNode; iterator != null; iterator = iterator.getNextNode()) {
                Node iterator2 = iterator.getNextNode();
                comparison = newNode.getData().compareTo(iterator.getData()); //Check the comparison with the firstNode
                if (iterator2 == null) {//when there is only one item in the list
                    if (comparison < 0) {
                        newNode.setNextNode(iterator);
                        firstNode = newNode;
                        break;
                    } else if (comparison >= 0) {
                        firstNode.setNextNode(newNode);
                        break;
                    }
                } else if (comparison >= 0) {//The item does not belong at the beginning of the list
                    comparison = newNode.getData().compareTo(iterator2.getData());
                    if (comparison < 0) {
                        newNode.setNextNode(iterator.getNextNode());
                        iterator.setNextNode(newNode);
                        break;
                    } else if (iterator2.getNextNode() == null) { //The item belongs at the end of the list
                        iterator2.setNextNode(newNode);
                        break;
                    }
                } else { //The item belongs a the beginning of the list
                    newNode.setNextNode(iterator);
                    firstNode = newNode;
                    break;
                }
            }
        }
        numberEntries++;
    }
    @Override
    public T remove(){
        if(isEmpty()) {
            return null;
        }
        T removedData=firstNode.getData();
        firstNode=firstNode.getNextNode();
        numberEntries--;
        return removedData;
    }
    @Override
    public T peek(){
        if(isEmpty()) {
            return null;
        }
        return firstNode.getData();
    }
    @Override
    public boolean isEmpty(){
        if(firstNode==null){
            return true;
        }
        return false;
    }
    @Override
    public int getSize(){
        return numberEntries;
    }
    @Override
    public void clear(){
        firstNode=null;
    }
    public void updateValues(){
        if(!isEmpty()) {
            for (Node iterator = firstNode; iterator != null; iterator = iterator.getNextNode()) {
                iterator.getData().update();
            }
        }
    }
    private class Node
    {
        private T data;
        private Node next;

        private Node(T d, Node n)
        {
            next = n;
            data = d;
        }
        public void setNextNode(Node n)
        {
            next = n;
        }
        public T getData(){ return data; }
        public void setData(T d) { data = d; }
        public Node getNextNode() { return next; }
    }
}
