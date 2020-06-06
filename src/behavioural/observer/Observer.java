package behavioural.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer Pattern: similar to publish/subscribe pattern, solves the problem of the need of listening to state changes.
 *  Push style: better when passing same values to all observers (values are pass to update method).
 *  Pull style: when different values for each observer (concrete observers communicate (is compose) with DataSource to get current values).
 *
 * Classes:
 *  Subject
 *      addObserver(obs) / attach(obs)
 *      removeObserver(obs) / detach(obs)
 *      notifyObservers() / notify()
 *
 *  interface Observer
 *      update()
 *
 *  DataSource / SubjectA
 *      value:int
 *      setValue(value)
 *
 *  SpreadSheet / ObserverA
 *      update()
 *
 *  Chart / ObserverB
 *      update()
 *
 * Relationships:
 *  DataSource extends a Subject (Publisher/Observable)
 *  Subject maintains a list of Observer
 *  SpreadSheet and Chart implements Observer
 *
 *  When a value changes by setValue(value) the notifyObserver() is called which calls the update() on each Observer.
 */
public class Observer {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.addObserver(new SpreadSheet(dataSource));
        dataSource.addObserver(new SpreadSheet(dataSource));
        dataSource.addObserver(new Chart(dataSource));
        dataSource.setValue(1);
    }
}

interface IObserver {
    void update();
}

class Subject {
    private List<IObserver> observers = new ArrayList<>();
    public void addObserver(IObserver observer) { observers.add(observer); }
    public void removeObserver(IObserver observer) { observers.remove(observer); }
    public void notifyObservers() { // Push style gets value
        for(IObserver observer : observers) {
            // observer.update(value); // Push style
            observer.update();
        }
    }
}

class DataSource extends Subject {
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
        // notifyObservers(value); // Push style
        notifyObservers();
    }
}

class SpreadSheet implements IObserver {
    private DataSource dataSource;
    public SpreadSheet(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void update() {
        System.out.println("SpreadSheet got updated: " + dataSource.getValue());
    }
}

class Chart implements IObserver {
    private DataSource dataSource;
    public Chart(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void update() {
        System.out.println("Chart got updated: " + dataSource.getValue());
    }
}

/* Push style
interface IObserver {
    void update(int value);
}

class SpreadSheet implements IObserver {
    @Override
    public void update(int value) {
        System.out.println("SpreadSheet got updated: " + value);
    }
}

class Chart implements IObserver {
    @Override
    public void update(int value) {
        System.out.println("Chart got updated: " + value);
    }
}
 */
