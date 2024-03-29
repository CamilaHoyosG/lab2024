package co.com.hyunseda.infra;

import java.util.ArrayList;

public abstract class Subject {

    ArrayList<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * Agrega un observador
     *
     * @param obs
     */
    public void addObserver(Observer obs) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(obs);
    }

    /**
     * Notifica a todos los observadores que hubo un cambio en el modelo
     */
    public void notifyAllObserves() {
        for (Observer each : observers) {
            each.update(this);
        }
    }

}
