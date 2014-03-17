package de.dfki.vsm.util.syn;

import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 *
 * @author Gregor Mehlmann
 */
public class SyntaxEditorKit extends DefaultEditorKit implements ViewFactory {

    // The Document Lexxer
    private final SyntaxDocLexxer mLexxer;

    public SyntaxEditorKit(final SyntaxDocLexxer lexxer) {
        mLexxer = lexxer;
    }

    @Override
    public ViewFactory getViewFactory() {
        return this;
    }

    @Override
    public View create(final Element element) {
        return new SyntaxEditorView(element);
    }

    @Override
    public Document createDefaultDocument() {
        return new SyntaxDocument(mLexxer);
    }

    @Override
    public String getContentType() {
        return "text/script";
    }

}
