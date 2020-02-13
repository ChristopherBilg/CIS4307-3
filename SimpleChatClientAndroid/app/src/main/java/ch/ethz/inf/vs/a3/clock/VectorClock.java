package ch.ethz.inf.vs.a3.clock;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class VectorClock implements Clock {

    private Map<Integer, Integer> vector;

    public VectorClock() {
        this.vector = new HashMap<Integer, Integer>();
    }

    @Override
    public void update(Clock other) {

    }

    @Override
    public void setClock(Clock other) {
        VectorClock clock = (VectorClock) other;
        this.vector = clock.getClock();
    }

    public Map<Integer, Integer> getClock() {
        return this.vector;
    }

    @Override
    public void tick(Integer pid) {
        if (this.vector.containsKey(pid))
            this.vector.put(pid, this.vector.get(pid) + 1);
    }

    @Override
    public boolean happenedBefore(Clock other) {
        return false;
    }

    @Override
    public void setClockFromString(String clock) {

    }

    public int getTime(int pid) {
        if (this.vector.containsKey(pid))
            return this.vector.get(pid);
        return 0;
    }

    public void addProcess(int pid, int testTime) {
        this.vector.put(pid, testTime);
    }
}
