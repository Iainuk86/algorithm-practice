package org.iainuk.linkedlists;

public class LinkedListTestClient {

    public static void main(String[] args) {

//        SinglyLinkedList list = new SinglyLinkedList();
//        list.add(12);
//        list.add("Hello there");
//
//        System.out.println("\nItems on list ...");
//        for (Object o : list) {
//            System.out.println(o);
//        }
//
//        System.out.println(list.size());
//
//        System.out.println(list.pop());
//
//        list.insert(1, "Well i'll be damned");
//
//        System.out.println("Value at index 1 ... " + list.get(1));
//
//        System.out.println("\nIndex of value 12 ... " + list.indexOf(12));
//
//        System.out.println("List contains 'Should be false' ... " + list.contains("Should be false ..."));
//        System.out.println("List contains 'Hello there' ... " + list.contains("Hello there"));
//
//        list.change(0, 25);
//        System.out.println("\nChanged 12 to 25 ... ");
//
//        System.out.println("Contains 12 ... " + list.contains(12));
//        System.out.println("Contains 25 ... " + list.contains(25) + "\n");
//
//        System.out.println("Current items on list ... ");
//        for (Object o : list) {
//            System.out.println(o);
//        }
//
//        list.delete(0);
//        System.out.println("\nDeleted index 0 ...\n");
//        System.out.println("Current items on list ... ");
//
//        for (Object o : list) {
//            System.out.println(o);
//        }
//
//        list.delete("Hello there");
//        System.out.println("\nDeleted 'Hello there' ...");
//
//        System.out.println("\nList is now empty ... right? ... " + list.isEmpty());

        // ------------------------------------------------------------------

        DoublyLinkedList<String> dll = new DoublyLinkedList();
        dll.add("1");
        dll.add("2");
        dll.add("4");
        dll.add("4");
        dll.add("4");
        dll.add("3");
        dll.insertBefore("2", "200");
        dll.insertAfter("200", "567");


        for (String s : dll) {
            System.out.println(s);
        }

        System.out.println();

        dll.remove("4");

        for (String s : dll) {
            System.out.println(s);
        }

    }
}
