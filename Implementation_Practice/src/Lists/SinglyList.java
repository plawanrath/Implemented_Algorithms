package Lists;

/**
 * Created by Plawan on 5/3/16.
 */
public class SinglyList {

    public class Item {
        int val;
        Item next;

        Item(int value) {
            val = value;
            next = null;
        }
    }

    Item head,tail;

    SinglyList() {
        head = null;
        tail = null;
    }

    int add(int val) {
        int pos = 0;
        Item v = new Item(val);
        if(head == null && tail == null)
        {
            head = tail = v;
        }
        else {
            tail = head;
            while(tail.next != null) {
                tail = tail.next;
                pos++;
            }
            tail.next = v;
        }
        return pos;
    }

    void add(int val,int pos) {
        Item v = new Item(val);
        if(head == null && tail == null)
        {
            if(pos == 0)
                head = tail = v;
            else
                return;
        }
        else {
            tail = head;
            Item prev = head;
            int index = 0;
            while(index < pos && tail.next != null) {
                prev = tail;
                tail = tail.next;
                index++;
            }
            if(index == pos) {
                if(tail != null) {
                    if(tail.next == null)
                        tail.next = v;
                    else if(pos == 0) {
                        v.next = head;
                        head = v;
                    }
                    else
                    {
                        prev.next = v;
                        v.next = tail;
                    }
                }
            }
            else
                return;
        }
    }

    int removeItem(int val) {
        int res = -1;
        if(head == null && tail == null) {
            return res;
        }
        else {
            tail = head;
            Item prev = tail;
            if(head.val == val) {
                res = head.val;
                if(head.next == null)
                    head = null;
                else
                    head = head.next;
                return res;
            }
            while(tail != null) {
                if(tail.val == val) {
                    prev.next = tail.next;
                    return val;
                }
                else {
                    prev = tail;
                    tail = tail.next;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        SinglyList sl = new SinglyList();
        sl.add(2);
        sl.add(5);
        sl.add(8);
        sl.add(10);
        sl.add(12,0);
        sl.removeItem(8);
        sl.removeItem(12);
        System.out.println();
    }
}
