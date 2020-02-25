package structural.adapter;

/**
 * Adapter Pattern: solves the need of converting the interface of a class to a different form (adapt them).
 *  This example uses the applying filters to photos app, so goal is to make third party interface to match our own interface type.
 *
 * Classes:
 *  interface Filter / Target
 *      apply(image)
 *
 *  VividFilter
 *      apply(image)
 *
 *  CaramelFilter / Adapter
 *      render()
 *
 *  Caramel / Adaptee
 *      render()
 *
 *  ImageView / Client
 *      apply(filter)
 *
 * Relationships:
 *  VividFilter implements Filter
 *  CaramelFilter implements Filter
 *  CaramelFilter is compose with a Caramel
 *  ImageView has a dependency to Filter
 *
 *  So we want the third party class (Caramel) to be adapt to the Filter interface, so we
 *  create a new class (CaramelFilter/Adapter) implementing Filter and compose it with Caramel (Adaptee).
 */
public class Adapter {
    public static void main(String[] args) {
        ImageView imageView = new ImageView(new Image());
        // imageView.apply(new VividFilter());
        imageView.apply(new CaramelFilter(new Caramel()));
    }
}

class Image {}

interface Filter {
    void apply(Image image);
}

class VividFilter implements Filter {
    @Override
    public void apply(Image image) {
        System.out.println("Applying Vivid Filter");
    }
}

class ImageView {
    private Image image;
    public ImageView(Image image) {
        this.image = image;
    }
    public void apply(Filter filter) {
        filter.apply(image);
    }
}

class CaramelFilter implements Filter {
    private Caramel caramel;
    public CaramelFilter(Caramel caramel) {
        this.caramel = caramel;
    }
    @Override
    public void apply(Image image) {
        caramel.init();
        caramel.render(image);
    }
}

/** Third party package of filters */
class Caramel {
    public void init() {

    }
    public void render(Image image) {
        System.out.println("Applying Caramel Filter");
    }
}