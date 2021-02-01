package org.iainuk.queues;

public class QueueTestClient {

    public static void main(String[] args) {

        CircularLinkedListQueue<String> cllq = new CircularLinkedListQueue<>();

        cllq.enqueue("Hello there");
        cllq.enqueue("String number two");
        cllq.enqueue("I hope this works");

        System.out.println("Queue size: " + cllq.size() + "\n");

        System.out.println("Items on queue: ");
        for (String s : cllq) {
            System.out.println("\t" + s);
        }

        System.out.println("\n" + cllq.dequeue());

        System.out.println("\nQueue size: " + cllq.size());

        System.out.println("\nIs queue empty? " + cllq.isEmpty());
    }
}
