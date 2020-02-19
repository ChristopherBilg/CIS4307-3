package ch.ethz.inf.vs.a3.message;

import java.util.Comparator;

import ch.ethz.inf.vs.a3.clock.VectorClock;

/**
 * Message comparator class. Use with PriorityQueue.
 */
public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message lhs, Message rhs) {
        String m1 = lhs.getTimestampContents();
        String m2 = rhs.getTimestampContents();

        if (m1 == null || m2 == null)
            throw new NullPointerException("lhs or rhs is null");

        VectorClock c1 = new VectorClock();
        VectorClock c2 = new VectorClock();
        c1.setClockFromString(m1);
        c2.setClockFromString(m2);

//        if (c1.getClock() == c2.getClock())
//            return 0;
//        else if (c1.happenedBefore(c2))
//            return -1;
//        else
//            return 1;

        if (c1.happenedBefore(c2))
            return -1;
        else if (c2.happenedBefore(c1))
            return 1;
        else
            return 0;
    }

}
