package behavioural.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor Pattern: solves the problem of need to add new operations (new visitors) to all concrete objects (nodes), but note the need of stable number of concrete objects.
 *  In other words when in need of adding new operations for each concrete node we only have to
 *  add a new class implementing the Operation interface (Open-Close-Principle).
 *
 * Classes:
 *  interface Operation / Visitor
 *      apply(heading) / visit(visitorA)
 *      apply(anchor) / visit(visitorB)
 *
 *  HighlightOperation / VisitorA
 *      apply(heading) / visit(visitorA)
 *      apply(anchor) / visit(visitorB)
 *
 *  PlainTextOperation / VisitorB
 *      apply(heading) / visit(visitorA)
 *      apply(anchor) / visit(visitorB)
 *
 *  interface HtmlNode / Element
 *      execute(operation) / accept(visitor)
 *
 *  HeadingNode / ElementA
 *      execute(operation) / accept(visitor)
 *
 *  AnchorNode / ElementB
 *      execute(operation) / accept(visitor)
 *
 * Relationships:
 *  HighlightOperation and PlainTextOperation implements Operation.
 *  HtmlNode has dependency to Operation
 *  HeadingNode and AnchorNode implements HtmlNode execute(operation) and calls operation.apply(this)
 *  HtmlDocument has a list of HtmlNodes and execute each node based on the concrete operation.
 */
public class Visitor {
    public static void main(String[] args) {
        HtmlDocument document = new HtmlDocument();
        document.add(new HeadingNode());
        document.add(new AnchorNode());
        document.execute(new HighlightOperation());
        document.execute(new PlainTextOperation());
    }
}

interface Operation {
    void apply(HeadingNode heading);
    void apply(AnchorNode anchor);
}

class HighlightOperation implements Operation {
    @Override
    public void apply(HeadingNode heading) {
        System.out.println("highlight-heading");
    }
    @Override
    public void apply(AnchorNode anchor) {
        System.out.println("highlight-anchor");
    }
}

class PlainTextOperation implements Operation {
    @Override
    public void apply(HeadingNode heading) {
        System.out.println("text-heading");
    }
    @Override
    public void apply(AnchorNode anchor) {
        System.out.println("text-anchor");
    }
}

interface HtmlNode {
    void execute(Operation operation);
}

class HeadingNode implements HtmlNode {
    @Override
    public void execute(Operation operation) {
        operation.apply(this);
    }
}

class AnchorNode implements HtmlNode {
    @Override
    public void execute(Operation operation) {
        operation.apply(this);
    }
}

class HtmlDocument {
    private List<HtmlNode> nodes = new ArrayList<>();
    public void add(HtmlNode node) {
        nodes.add(node);
    }
    public void execute(Operation operation) {
        for(HtmlNode node : nodes)
            node.execute(operation);
    }
}