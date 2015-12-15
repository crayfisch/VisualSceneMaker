package de.dfki.vsm.model.sceneflow.language.command.expression;

//~--- non-JDK imports --------------------------------------------------------

import de.dfki.vsm.model.sceneflow.language.command.Expression;
import de.dfki.vsm.util.ios.IOSIndentWriter;
import de.dfki.vsm.util.xml.XMLParseAction;
import de.dfki.vsm.util.xml.XMLParseError;
import de.dfki.vsm.util.xml.XMLWriteError;
import java.util.ArrayList;

import org.w3c.dom.Element;

//~--- JDK imports ------------------------------------------------------------



/**
 * @author Not me
 */
public class ConstructExpression extends Expression {
    private String             mName;
    private ArrayList<Expression> mArgList;

    public ConstructExpression() {
        mName    = new String();
        mArgList = new ArrayList<Expression>();
    }

    public ConstructExpression(String name) {
        mName    = name;
        mArgList = new ArrayList<Expression>();
    }

    public ConstructExpression(String name, ArrayList<Expression> argList) {
        mName    = name;
        mArgList = argList;
    }

    public String getName() {
        return mName;
    }

    public void setName(String value) {
        mName = value;
    }

    public ArrayList<Expression> getArgList() {
        return mArgList;
    }

    public void setArgList(ArrayList<Expression> value) {
        mArgList = value;
    }

    public int getSizeOfArgList() {
        return mArgList.size();
    }

    public ArrayList<Expression> getCopyOfArgList() {
        ArrayList<Expression> copy = new ArrayList<Expression>();

        for (Expression exp : mArgList) {
            copy.add(exp.getCopy());
        }

        return copy;
    }

    public boolean addArg(Expression value) {
        return mArgList.add(value);
    }

    public Expression getArgAt(int index) {
        return mArgList.get(index);
    }

//    public ExpType getExpType() {
//        return ExpType.CONS;
//    }

    public String getAbstractSyntax() {
        String desc = "Constructor( " + mName + "( ";

        for (int i = 0; i < mArgList.size(); i++) {
            desc += mArgList.get(i).getAbstractSyntax();

            if (i != mArgList.size() - 1) {
                desc += " , ";
            }
        }

        return desc + " ) )";
    }

    public String getConcreteSyntax() {
        String desc = "new " + mName + " ( ";

        for (int i = 0; i < mArgList.size(); i++) {
            desc += mArgList.get(i).getConcreteSyntax();

            if (i != mArgList.size() - 1) {
                desc += " , ";
            }
        }

        return desc + " )";
    }

    public String getFormattedSyntax() {
        String desc = "#r#new " + "#b#" + mName + " ( ";

        for (int i = 0; i < mArgList.size(); i++) {
            desc += mArgList.get(i).getFormattedSyntax();

            if (i != mArgList.size() - 1) {
                desc += " , ";
            }
        }

        return desc + " ) ";
    }

    public ConstructExpression getCopy() {
        return new ConstructExpression(mName, getCopyOfArgList());
    }

    public void writeXML(IOSIndentWriter out) throws XMLWriteError {
        out.println("<Constructor name=\"" + mName + "\">").push();

        for (int i = 0; i < mArgList.size(); i++) {
            mArgList.get(i).writeXML(out);
        }

        out.pop().println("</Constructor>");
    }

    public void parseXML(Element element) throws XMLParseError {
        mName = element.getAttribute("name");
        XMLParseAction.processChildNodes(element, new XMLParseAction() {
            public void run(Element element) throws XMLParseError {
                Expression exp = Expression.parse(element);

                mArgList.add(exp);
            }
        });
    }
}