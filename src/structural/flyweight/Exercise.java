package structural.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Exercise {
    public static void main(String[] args) {
        CellContextFactory contextFactory = new CellContextFactory();
        SpreadSheet sheet = new SpreadSheet(contextFactory);
        sheet.setContent(0, 0, "Hello");
        sheet.setContent(1, 0, "World");
        sheet.setFontFamily(0, 0, "Arial");
        sheet.render();
    }
}

class SpreadSheet {
    private final int MAX_ROWS = 3;
    private final int MAX_COLS = 3;
    private Cell[][] cells = new Cell[MAX_ROWS][MAX_COLS];
    private CellContextFactory contextFactory;
    public SpreadSheet(CellContextFactory contextFactory) {
        this.contextFactory = contextFactory;
        generateCells();
    }
    public void setContent(int row, int col, String content) {
        ensureCellExists(row, col);
        cells[row][col].setContent(content);
    }
    public void setFontFamily(int row, int col, String fontFamily) {
        ensureCellExists(row, col);
        var cell = cells[row][col];
        var currentContext = cell.getContext();
        var context = contextFactory.getContext(fontFamily, currentContext.getFontSize(), currentContext.isBold());
        cells[row][col].setContext(context);
    }
    private void ensureCellExists(int row, int col) {
        if (row < 0 || row >= MAX_ROWS) throw new IllegalArgumentException();
        if (col < 0 || col >= MAX_COLS) throw new IllegalArgumentException();
    }
    private void generateCells() {
        for (var row = 0; row < MAX_ROWS; row++)
            for (var col = 0; col < MAX_COLS; col++)
                cells[row][col] = new Cell(row, col, getDefaultContext());
    }
    private CellContext getDefaultContext() {
        return new CellContext("Times New Roman", 12, false);
    }
    public void render() {
        for (var row = 0; row < MAX_ROWS; row++)
            for (var col = 0; col < MAX_COLS; col++)
                cells[row][col].render();
    }
}

class CellContextFactory {
    private Map<Integer, CellContext> contexts = new HashMap<>();
    public CellContext getContext(String fontFamily, int fontSize, boolean isBold) {
        var hash = Objects.hash(fontFamily, fontSize, isBold);
        if (!contexts.containsKey(hash)) contexts.put(hash, new CellContext(fontFamily, fontSize, isBold));
        return contexts.get(hash);
    }
}

class CellContext {
    private final String fontFamily;
    private final int fontSize;
    private final boolean isBold;
    public CellContext(String fontFamily, int fontSize, boolean isBold) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.isBold = isBold;
    }
    public String getFontFamily() {
        return fontFamily;
    }
    public int getFontSize() {
        return fontSize;
    }
    public boolean isBold() {
        return isBold;
    }
    @Override
    public int hashCode() {
        return Objects.hash(fontFamily, fontSize, isBold);
    }
}

class Cell {
    private final int row;
    private final int column;
    private String content;
    private CellContext context;
    public Cell(int row, int column, CellContext context) {
        this.row = row;
        this.column = column;
        this.context = context;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public CellContext getContext() {
        return context;
    }
    public void setContext(CellContext context) {
        this.context = context;
    }
    public void render() {
        System.out.printf("(%d, %d): %s [%s]\n", row, column, content, context.getFontFamily());
    }
}