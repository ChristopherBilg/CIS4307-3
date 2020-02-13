package ch.ethz.inf.vs.a3.clock;

public class VectorClock implements Clock {
    @Override
    public void update(Clock other) {

    }

    @Override
    public void setClock(Clock other) {

    }

    @Override
    public void tick(Integer pid) {

    }

    @Override
    public boolean happenedBefore(Clock other) {
        return false;
    }

    @Override
    public void setClockFromString(String clock) {

    }

    public int getTime(int i) { return 0; }

    public void addProcess(int i, int testTime) {}
}
