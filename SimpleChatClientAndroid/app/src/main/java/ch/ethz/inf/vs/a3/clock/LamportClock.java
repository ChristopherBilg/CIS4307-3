package ch.ethz.inf.vs.a3.clock;

import androidx.annotation.NonNull;

public class LamportClock implements Clock {

    private int time;

    @Override
    public void update(Clock other) {
        LamportClock clock = (LamportClock) other;
        if (clock.getTime() > this.time)
            this.time = clock.getTime();
    }

    @Override
    public void setClock(Clock other) {
        LamportClock clock = (LamportClock) other;
        this.time = clock.getTime();
    }

    @Override
    public void tick(Integer pid) {
        this.time++;
    }

    @Override
    public boolean happenedBefore(Clock other) {
        LamportClock clock = (LamportClock) other;
        if (clock.getTime() <= this.time)
            return false;
        return true;
    }

    @Override
    public void setClockFromString(String clock) {
        boolean isNumber = true;
        for (int c : clock.toCharArray())
            if (c < 48 || c > 57)
                isNumber = false;

        if (isNumber && !clock.isEmpty())
            this.time = Integer.parseInt(clock);
    }

    @NonNull
    @Override
    public String toString() {
        return new Integer(this.time).toString();
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }
}
