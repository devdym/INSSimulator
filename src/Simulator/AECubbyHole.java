/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulator;

/**
 *
 * @author Dym
 */
class AECubbyHole {
    private String contents;
    private boolean available = false;

    public synchronized String get() {
        while (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notifyAll();
        return contents;
    }

    public synchronized void put(String value) {
        while (available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        available = true;
        notifyAll();
    }    
}
