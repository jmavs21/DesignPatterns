package behavioural.chainOfResponsibility;

public class Exercise {
    public static void main(String[] args) {
        DataReader reader = DataReaderFactory.getDataReaderChain();
        reader.read("data.xls");
        reader.read("data.numbers");
        reader.read("data.qbw");
        reader.read("data.jpg");
    }
}

class DataReaderFactory {
    public static DataReader getDataReaderChain() {
        var excelReader = new ExcelReader();
        var numbersReader = new NumbersReader();
        var quickBooksReader = new QuickBooksReader();
        quickBooksReader.setNext(numbersReader);
        numbersReader.setNext(excelReader);
        return quickBooksReader;
    }
}

abstract class DataReader {
    private DataReader next;
    public void setNext(DataReader next) {
        this.next = next;
    }
    public void read(String fileName) {
        if(fileName.endsWith(getExtension())) {
            this.doRead(fileName);
            return;
        }
        if(next != null) next.read(fileName);
        else throw new UnsupportedOperationException("File format not supported.");
    }
    protected abstract String getExtension();
    protected abstract void doRead(String fileName);
}

class ExcelReader extends DataReader {
    @Override
    protected String getExtension() {
        return ".xls";
    }
    @Override
    protected void doRead(String fileName) {
        System.out.println("Reading data from an Excel spreadsheet.");
    }
}

class NumbersReader extends DataReader {
    @Override
    protected String getExtension() {
        return ".numbers";
    }
    @Override
    protected void doRead(String fileName) {
        System.out.println("Reading data from a Numbers spreadsheet: ");
    }
}

class QuickBooksReader extends DataReader {
    @Override
    protected String getExtension() {
        return ".qbw";
    }
    @Override
    protected void doRead(String fileName) {
        System.out.println("Reading data from a QuickBooks file.");
    }
}
