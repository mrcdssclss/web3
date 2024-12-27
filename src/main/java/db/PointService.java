package db;

import java.util.List;

public class PointService {

    //логика с точками для бд
    private PointDao pointsDao = new PointDao();

    public PointService() {
    }

    public void savePoint(PointModel point) {
        pointsDao.save(point);
    }

    public List<PointModel> findAllPoints() {
        return pointsDao.findAll();
    }
}
