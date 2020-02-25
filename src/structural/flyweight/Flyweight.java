package structural.flyweight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Facade Pattern: solves the problem of reducing memory when having lots of large objects that could be shared.
 *  This example is for an app similar to google map. Idea is to reuse icon objects (i.e. PointIcon/Flyweight) and they can be created via a factory class (PointIconFactory storing maybe on cache/Map).
 *  Before: Point
 *              x: int
 *              y: int
 *              type: PointType
 *              icon: byte[]
 *
 * Classes:
 *  Point
 *      x: int
 *      y: int
 *      pointIcon: PointIcon
 *
 *  PointIcon / Flyweight
 *      type: PointType
 *      icon: byte[]
 *
 *  PointIconFactory / FlyweightFactory
 *      icons: Map<PointType, PointIcon>
 *      getPointIcon(type): PointIcon
 *
 *  PointService
 *      iconFactory: PointIconFactory
 *      getPoints()
 *
 * Relationships:
 *  Point is compose with a PointIcon
 *  PointFactory is compose with multiple PointIcon
 *  PointService has dependency to PointIconFactory
 */
public class Flyweight {
    public static void main(String[] args) {
        PointService service = new PointService(new PointIconFactory());
        for(Point point : service.getPoints())
            point.draw();
    }
}

class PointService {
    private PointIconFactory iconFactory;
    public PointService(PointIconFactory iconFactory) {
        this.iconFactory = iconFactory;
    }
    public List<Point> getPoints() {
        return Arrays.asList(new Point(1, 2, iconFactory.getPointIcon(PointType.CAFE)));
    }
}

class PointIconFactory {
    private Map<PointType, PointIcon> icons = new HashMap<>();
    public PointIcon getPointIcon(PointType type) {
        if(!icons.containsKey(type)) icons.put(type, new PointIcon(type, null));
        return icons.get(type);
    }
}

class PointIcon {
    private final PointType type;
    private final byte[] icon;
    public PointIcon(PointType type, byte[] icon) {
        this.type = type;
        this.icon = icon;
    }
    public PointType getType() {
        return type;
    }
}

class Point {
    private int x;
    private int y;
    private PointIcon pointIcon;
    public Point(int x, int y, PointIcon pointIcon) {
        this.x = x;
        this.y = y;
        this.pointIcon = pointIcon;
    }
    public void draw() {
        System.out.printf("%s at (%d, %d)", pointIcon.getType(), x, y);
    }
}

enum PointType {
    HOSPITAL, CAFE, RESTAURANT
}
