package de.dfki.vsm.util.cpy;

import java.io.Serializable;

/**
 * @author Gregor Mehlmann
 */
public interface Copyable extends Serializable {

    public abstract Object getCopy();
}
