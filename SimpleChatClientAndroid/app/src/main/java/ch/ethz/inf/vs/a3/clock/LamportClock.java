package ch.ethz.inf.vs.a3.clock;

public class LamportClock implements Clock {
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

    public void setTime(int time) {}

    public long getTime() { return (long) 0.0; }
}
