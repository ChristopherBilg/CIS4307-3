package ch.ethz.inf.vs.a3.clock;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VectorClock implements Clock {

    private Map<Integer, Integer> vector;

    public VectorClock() {
        this.vector = new HashMap<Integer, Integer>();
    }

    @Override
    public void update(Clock other) {
        VectorClock clock = (VectorClock) other;
        for (int pid : this.vector.keySet())
            if (clock.getClock().containsKey(pid))
                if (this.vector.get(pid) < clock.getClock().get(pid))
                    this.vector.put(pid, clock.getClock().get(pid));

        for (int pid : clock.getClock().keySet())
            if (this.vector.containsKey(pid)) {
                if (this.vector.get(pid) < clock.getClock().get(pid))
                    this.vector.put(pid, clock.getClock().get(pid));
            } else {
                this.vector.put(pid, clock.getClock().get(pid));
            }
    }

    @Override
    public void setClock(Clock other) {
        VectorClock clock = (VectorClock) other;
        this.vector = clock.getClock();
    }

    public Map<Integer, Integer> getClock() {
        return this.vector;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append('{');

        for (int key : this.vector.keySet()) {
            str.append("\"");
            str.append(key);
            str.append("\"");
            str.append(":");
            str.append(this.vector.get(key));
            str.append(",");
        }

        if (!this.vector.isEmpty())
            str.deleteCharAt(str.length() - 1);

        str.append('}');

        return str.toString();
    }

    @Override
    public void tick(Integer pid) {
        if (this.vector.containsKey(pid))
            this.vector.put(pid, this.vector.get(pid) + 1);
    }

    @Override
    public boolean happenedBefore(Clock other) {
        VectorClock clock = (VectorClock) other;

        for (int key : this.vector.keySet()) {
            if (clock.getClock().containsKey(key))
                if (this.vector.get(key) <= clock.getClock().get(key))
                    continue;
                else
                    return false;
        }

        return true;
    }

    @Override
    public void setClockFromString(String clock) {
        if (clock == "{}") {
            this.vector = new HashMap<Integer, Integer>();
            return;
        }

        JSONObject json;
        try {
            json = new JSONObject(clock);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        Map<Integer, Integer> newVector = new HashMap<Integer, Integer>();
        Iterator<String> iter = json.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            int keyInt;
            int value;
            try {
                value = json.getInt(key);
            } catch (JSONException e) {
//                e.printStackTrace();
                return;
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                return;
            }
            try {
                keyInt = Integer.parseInt(key);
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                return;
            }
            newVector.put(keyInt, value);
        }

        this.vector = newVector;
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
