package de.dfki.vsm.model;

import de.dfki.vsm.util.cpy.Copyable;
import de.dfki.vsm.util.xml.XMLParseable;
import de.dfki.vsm.util.xml.XMLWriteable;

/**
 * @author Not me
 */
public interface ModelObject extends XMLParseable, XMLWriteable, Copyable {

    // Get a deep copy of the copyable
    @Override
    public abstract ModelObject getCopy();
}
