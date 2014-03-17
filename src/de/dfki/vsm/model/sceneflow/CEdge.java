package de.dfki.vsm.model.sceneflow;

import de.dfki.vsm.model.sceneflow.command.Command;
import de.dfki.vsm.model.sceneflow.command.expression.condition.logical.LogicalCond;
import de.dfki.vsm.model.sceneflow.graphics.edge.Graphics;
import de.dfki.vsm.util.tpl.TPLTuple;
import de.dfki.vsm.util.xml.XMLParseAction;
import de.dfki.vsm.util.xml.XMLParseError;
import de.dfki.vsm.util.xml.XMLWriteError;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import org.w3c.dom.Element;

/**
 * @author GregorMehlmann
 */
public class CEdge extends Edge {

    protected LogicalCond mCondition = null;

    public CEdge() {
    }

    public CEdge(
            String target,
            String source,
            Node targetNode,
            Node sourceNode,
            Graphics graphics,
            Vector<Command> cmdList,
            HashMap<TPLTuple<String, Node>, TPLTuple<String, Node>> altStartNodeMap,
            LogicalCond condition) {
        super(target, source, targetNode, sourceNode, graphics, cmdList, altStartNodeMap);
        mCondition = condition;
    }

    public LogicalCond getCondition() {
        return mCondition;
    }

    public void setCondition(LogicalCond value) {
        mCondition = value;
    }

    public Type getEdgeType() {
        return Type.CEdge;
    }

    public String getAbstractSyntax() {
        return null;
    }

    public String getConcreteSyntax() {
        return null;
    }

    public String getFormattedSyntax() {
        return null;
    }

    // TODO:
    public CEdge getCopy() {
        return new CEdge(
                mTarget,
                mSource,
                mTargetNode,
                mSourceNode,
                mGraphics.getCopy(),
                getCopyOfCmdList(),
                getCopyOfAltStartNodeMap(),
                mCondition.getCopy());
    }

    public void writeXML(de.dfki.vsm.util.ios.IndentWriter out) throws XMLWriteError {
        String start = "";
        Iterator it = mAltStartNodeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            TPLTuple<String, Node> startNodeData = (TPLTuple<String, Node>) pairs.getKey();
            TPLTuple<String, Node> altStartNodeData = (TPLTuple<String, Node>) pairs.getValue();
            start += startNodeData.getFirst() + "/" + altStartNodeData.getFirst() + ";";
        }

        out.println("<CEdge target=\"" + mTarget + "\" start=\"" + start + "\">").push();
        if (mGraphics != null) {
            mGraphics.writeXML(out);
        }
        if (mCondition != null) {
            mCondition.writeXML(out);
        }
        if (!mCmdList.isEmpty()) {
            out.println("<Commands>").push();
            for (int i = 0; i < mCmdList.size(); i++) {
                mCmdList.get(i).writeXML(out);
            }
            out.pop().println("</Commands>");
        }
        out.pop().println("</CEdge>");
    }

    public void parseXML(Element element) throws XMLParseError {
        mTarget = element.getAttribute("target");

        String[] altStartNodes = element.getAttribute("start").split(";");
        for (String idPair : altStartNodes) {
            if (!idPair.isEmpty()) {
                String[] ids = idPair.split("/");
                String startId = ids[0];
                String altStartId = ids[1];
                TPLTuple<String, Node> startPair = new TPLTuple<String, Node>(startId, null);
                TPLTuple<String, Node> altStartPair = new TPLTuple<String, Node>(altStartId, null);
                mAltStartNodeMap.put(startPair, altStartPair);
            }
        }

        XMLParseAction.processChildNodes(element, new XMLParseAction() {

            public void run(Element element) throws XMLParseError {
                java.lang.String tag = element.getTagName();
                if (tag.equals("Graphics")) {
                    mGraphics = new Graphics();
                    mGraphics.parseXML(element);
                } else if (tag.equals("Commands")) {
                    XMLParseAction.processChildNodes(element, new XMLParseAction() {

                        public void run(Element element) throws XMLParseError {
                            mCmdList.add(Command.parse(element));
                        }
                    });
                } else {
                    mCondition = LogicalCond.parse(element);
                }
            }
        });
    }
}
