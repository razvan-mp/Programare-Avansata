/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.interfaces;

import lab3.model.base.Unit;

public interface Storage {
    default int getStorage(Unit unit, int storage) {
        switch (unit) {
            case MB -> {
                return storage * 1024;
            }
            case KB -> {
                return storage * 1024 ^ 2;
            }
            case B -> {
                return storage * 1024 ^ 3;
            }
        }
        return storage;
    }
}
