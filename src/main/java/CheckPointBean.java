import db.PointDAO;
import db.PointModel;
import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ManagedBean(name="checkPointBean")
@SessionScoped
public class CheckPointBean implements Serializable {
    private PointDAO pointService = new PointDAO();
    private List points = pointService.findAll();
    boolean isHit = false;
    long startTime = System.nanoTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void check(PointBean pointBean){
        PointModel pointModel;
        if (isInsidePolygon(pointBean.getX(), pointBean.getY(), pointBean.getR()) ||
                isInsideRectangle(pointBean.getX(), pointBean.getY(), pointBean.getR()) ||
                isInsidePath(pointBean.getX(),  pointBean.getY(), pointBean.getR())) {
            pointModel = new PointModel(pointBean.getX(), pointBean.getY(), pointBean.getR(), true, System.nanoTime() - startTime, LocalDateTime.now().format(formatter));
            isHit = true;
        }
        else {
            pointModel = new PointModel(pointBean.getX(), pointBean.getY(), pointBean.getR(), false, System.nanoTime() - startTime, LocalDateTime.now().format(formatter));
            isHit = false;
        }
        PrimeFaces.current().executeScript("drawCircle(" + pointBean.getX() + "," + pointBean.getY()  + "," + pointModel.getIsHit() + ")");
        points.add(pointModel);
        pointService.save(pointModel);
    }

    public List getResults(){
        return points;
    }

    private static boolean isInsidePolygon(double x, double y, double r) {
        return x >= 0 && y >= 0 && x <= r && y <= r;
    }

    private static boolean isInsideRectangle(double x, double y, double r) {
        return x >= 0 && x <= r && y <= 0 && y >= 0.5 * x - r / 2 - 0.001;
    }

    private static boolean isInsidePath(double x, double y, double r) {
        double radius = r / 2;
        return x <= 0 && y <= 0 && (x * x + y * y <= radius * radius);
    }

    public boolean getIsHit() {
        return isHit;
    }
}
